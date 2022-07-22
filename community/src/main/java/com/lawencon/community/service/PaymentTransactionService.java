package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.PaymentTransactionDao;
import com.lawencon.community.model.File;
import com.lawencon.community.model.PaymentTransaction;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.paymentTransaction.PojoDataPaymentTransaction;
import com.lawencon.community.pojo.paymentTransaction.PojoFindByIdPaymentTransactionRes;
import com.lawencon.community.pojo.paymentTransaction.PojoInsertPaymentTransactionReq;
import com.lawencon.community.pojo.paymentTransaction.PojoUpdatePaymentTransactionReq;
import com.lawencon.model.SearchQuery;

@Service
public class PaymentTransactionService extends BaseCoreService<PaymentTransaction> {
	@Autowired
	private PaymentTransactionDao paymentDao;
	@Autowired
	private FileDao fileDao;

	private PaymentTransaction inputPaymentTransactionData(PaymentTransaction result, Boolean isActive, 
			Boolean isAcc, String fileName, String fileExt) throws Exception {
		result.setIsActive(isActive);
		
		result.setIsAcc(isAcc);
		if(fileName != null) {
			File fkFile = new File();
			
			fkFile.setFileName(fileName);
			fkFile.setFileExtension(fileExt);
			
			result.setFile(fkFile);
		}
		
		return result;
	}

	private PojoDataPaymentTransaction modelToRes(PaymentTransaction data) throws Exception {
		PojoDataPaymentTransaction result = new PojoDataPaymentTransaction();
		
		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());
		result.setIsAcc(data.getIsAcc());
	
		if(data.getFile()!=null) {
			File fkFile = fileDao.getById(data.getFile().getId());
			result.setFileId(fkFile.getId());
			result.setFileName(fkFile.getFileName());
			result.setFileExt(fkFile.getFileExtension());
		}

		return result;
	}

	public PojoFindByIdPaymentTransactionRes findById(String id) throws Exception {
		PaymentTransaction data = paymentDao.getById(id);

		PojoDataPaymentTransaction result = modelToRes(data);
		PojoFindByIdPaymentTransactionRes resultData = new PojoFindByIdPaymentTransactionRes();
		resultData.setData(result);

		return resultData;
	}

	public SearchQuery<PojoDataPaymentTransaction> getAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<PaymentTransaction> getAllPaymentTransaction = paymentDao.findAll(query, startPage, maxPage);
		List<PojoDataPaymentTransaction> resultList = new ArrayList<>();

		getAllPaymentTransaction.getData().forEach(d -> {
			PojoDataPaymentTransaction data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		SearchQuery<PojoDataPaymentTransaction> result = new SearchQuery<PojoDataPaymentTransaction>();
		result.setData(resultList);
		result.setCount(getAllPaymentTransaction.getCount());
		return result;
	}

	@Transactional(rollbackOn = Exception.class)
	public PojoInsertRes insert(PojoInsertPaymentTransactionReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();

			PaymentTransaction reqData = inputPaymentTransactionData(new PaymentTransaction(), true, data.getIsAcc(),
					data.getFileName(), data.getFileExt());

			begin();
			PaymentTransaction result = super.save(reqData);
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Payment");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public PojoUpdateRes update(PojoUpdatePaymentTransactionReq data) throws Exception {
		try {
			PojoUpdateRes updateRes = new PojoUpdateRes();
			PaymentTransaction reqData = paymentDao.getById(data.getId());

			reqData = inputPaymentTransactionData(reqData, reqData.getIsActive(), data.getIsAcc(),
					data.getFileName(), data.getFileExt());
			
			begin();
			PaymentTransaction result = paymentDao.save(reqData);
			commit();
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());

			updateRes.setData(resData);
			updateRes.setMessage("Successfully Editing Payment");
			return updateRes;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public PojoDeleteRes deleteById(String id) throws Exception {
		try {
			begin();
			boolean result = paymentDao.deleteById(id);
			commit();
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if (result)
				deleteRes.setMessage("Successfully Delete PaymentTransaction");
			else
				deleteRes.setMessage("Failed Delete PaymentTransaction");
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}