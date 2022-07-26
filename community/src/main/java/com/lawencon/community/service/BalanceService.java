package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.BalanceDao;
import com.lawencon.community.model.Balance;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.balance.PojoDataBalance;
import com.lawencon.community.pojo.balance.PojoFindByIdBalanceRes;
import com.lawencon.community.pojo.balance.PojoInsertBalanceReq;
import com.lawencon.community.pojo.balance.PojoUpdateBalanceReq;
import com.lawencon.model.SearchQuery;

@Service
public class BalanceService extends BaseCoreService<Balance> {
	@Autowired
	private BalanceDao balanceDao;

	private Balance inputBalanceData(Balance result, Boolean isActive, Long balance) throws Exception {
		result.setIsActive(isActive);

		result.setBalance(balance);

		return result;
	}

	private PojoDataBalance modelToRes(Balance data) throws Exception {
		PojoDataBalance result = new PojoDataBalance();

		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());

		result.setBalance(data.getBalance());

		return result;
	}

	public PojoFindByIdBalanceRes findById(String id) throws Exception {
		Balance data = balanceDao.getById(id);

		PojoDataBalance result = modelToRes(data);
		PojoFindByIdBalanceRes resultData = new PojoFindByIdBalanceRes();
		resultData.setData(result);

		return resultData;
	}

	public SearchQuery<PojoDataBalance> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Balance> getAllBalance = balanceDao.findAll(query, startPage, maxPage);
		List<PojoDataBalance> resultList = new ArrayList<>();

		getAllBalance.getData().forEach(d -> {
			PojoDataBalance data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});

		SearchQuery<PojoDataBalance> result = new SearchQuery<PojoDataBalance>();
		result.setData(resultList);
		result.setCount(getAllBalance.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertBalanceReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();

			Balance reqData = inputBalanceData(new Balance(), true, data.getBalance());

			begin();
			Balance result = super.save(reqData);
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Balance");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoUpdateRes update(PojoUpdateBalanceReq data) throws Exception {
		try {
			PojoUpdateRes updateRes = new PojoUpdateRes();
			Balance reqData = balanceDao.getById(data.getId());

			reqData = inputBalanceData(reqData, data.getIsActive(), data.getBalance());
			reqData.setVersion(data.getVersion());
			begin();
			Balance result = balanceDao.save(reqData);
			commit();
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());

			updateRes.setData(resData);
			updateRes.setMessage("Successfully Editing Balance");
			return updateRes;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

	}

	public PojoDeleteRes deleteById(String id) throws Exception {
		try {
			begin();
			boolean result = balanceDao.deleteById(id);
			commit();
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if (result)
				deleteRes.setMessage("Successfully Delete Balance");
			else
				deleteRes.setMessage("Failed Delete Balance");
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoFindByIdBalanceRes findByIdUser(String id) throws Exception {
		Balance data = balanceDao.findbyUserId(id);

		PojoDataBalance result = modelToRes(data);
		PojoFindByIdBalanceRes resultData = new PojoFindByIdBalanceRes();
		resultData.setData(result);

		return resultData;
	}
}
