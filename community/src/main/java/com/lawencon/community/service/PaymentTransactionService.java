package com.lawencon.community.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.ConstantPrice;
import com.lawencon.community.constant.RoleType;
import com.lawencon.community.constant.TransactionType;
import com.lawencon.community.dao.BalanceDao;
import com.lawencon.community.dao.CommunityDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.PaymentTransactionDao;
import com.lawencon.community.dao.SubscriptionStatusDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Balance;
import com.lawencon.community.model.Community;
import com.lawencon.community.model.File;
import com.lawencon.community.model.PaymentTransaction;
import com.lawencon.community.model.SubscriptionStatus;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.paymentTransaction.PojoDataPaymentTransaction;
import com.lawencon.community.pojo.paymentTransaction.PojoFindByIdPaymentTransactionRes;
import com.lawencon.community.pojo.paymentTransaction.PojoInsertPaymentTransactionReq;
import com.lawencon.community.pojo.paymentTransaction.PojoUpdatePaymentTransactionReq;
import com.lawencon.community.pojo.paymentTransaction.PojoValidPaymentTransactionReq;
import com.lawencon.model.SearchQuery;
import com.lawencon.security.PrincipalServiceImpl;

@Service
public class PaymentTransactionService extends BaseCoreService<PaymentTransaction> {
	@Autowired
	private PaymentTransactionDao paymentDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private CodeService codeService;
	@Autowired
	private SubscriptionStatusDao statusDao;
	@Autowired
	private PrincipalServiceImpl principalServiceImpl;
	@Autowired
	private BalanceDao balanceDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CommunityDao communityDao;

	private PaymentTransaction inputPaymentTransactionData( PaymentTransaction result, Boolean isActive, Boolean isAcc,  String desc, BigDecimal price, String fileName, String fileExt) throws Exception {
		
		result.setIsActive(isActive);
		
		result.setIsAcc(isAcc);
		result.setDesc(desc);
		result.setPrice(price);
		
		result.setIsActive(isActive);
		if(fileName != null) {
			File fkFile = new File();
			
			fkFile.setFileName(fileName);
			fkFile.setFileExtension(fileExt);
			fkFile.setCreatedBy(principalServiceImpl.getAuthPrincipal());
			fileDao.save(fkFile);
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
		result.setCode(data.getCode());
		result.setDesc(data.getDesc());
		result.setPrice(data.getPrice());
	
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
				throw new RuntimeException(e);
			}
		});

		SearchQuery<PojoDataPaymentTransaction> result = new SearchQuery<PojoDataPaymentTransaction>();
		result.setData(resultList);
		result.setCount(getAllPaymentTransaction.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertPaymentTransactionReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();

			PaymentTransaction reqData = inputPaymentTransactionData(
					new PaymentTransaction(), true, false, data.getDesc(), data.getPrice(),
					data.getFileName(), data.getFileExt());
			reqData.setCode(codeService.generateRandomCodeAll().getCode());
			reqData.setType(data.getType());
			begin();
			PaymentTransaction result = save(reqData);
			
			if(TransactionType.SUBSCRIPTION.name().equals(data.getType())) {
				SubscriptionStatus status = statusDao.findByUserId(principalServiceImpl.getAuthPrincipal());
				status.setPayment(result);
				status.setUpdatedBy(principalServiceImpl.getAuthPrincipal());
				statusDao.save(status);
			} 
			
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());

			insertRes.setData(resData);
			insertRes.setMessage("Please Wait Admin Confirm Your Payment");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoUpdateRes update(PojoUpdatePaymentTransactionReq data) throws Exception {
		try {
			PojoUpdateRes updateRes = new PojoUpdateRes();
			PaymentTransaction reqData = paymentDao.getById(data.getId());

			reqData = inputPaymentTransactionData(reqData, reqData.getIsActive(), data.getIsAcc(), data.getDesc(), data.getPrice(),
			data.getFileName(), data.getFileExt());
			
			begin();
			PaymentTransaction result = paymentDao.save(reqData);
			
			if(TransactionType.SUBSCRIPTION.name().equals(result.getType())) {
				SubscriptionStatus status = statusDao.findByUserId(principalServiceImpl.getAuthPrincipal());
				status.setIsSubscriber(true);
				statusDao.save(status);
			}
			
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
	

	public PojoUpdateRes validationPayment(PojoValidPaymentTransactionReq data) throws Exception {
		try {
			PojoUpdateRes updateRes = new PojoUpdateRes();
			PaymentTransaction reqData = paymentDao.getById(data.getId());

			reqData.setIsAcc(true);
			
			begin();
			PaymentTransaction result = paymentDao.save(reqData);
			
			if(TransactionType.SUBSCRIPTION.name().equals(result.getType())) {
				SubscriptionStatus status = statusDao.findByUserId(result.getCreatedBy());
				status.setIsSubscriber(true);
				status.setExpiredAt(LocalDateTime.now().plusMonths(1));
				statusDao.save(status);
				
				User system = userDao.findByRoleCode(RoleType.SYSTEM.name());
				Balance balance = balanceDao.findbyUserId(system.getId());
				BigDecimal balanceResult = balance.getBalance().add(new BigDecimal(ConstantPrice.BASIC.getPrice()));
				balance.setBalance(balanceResult);
				balanceDao.save(balance);
			} else if(TransactionType.COMMUNITY.name().equals(result.getType())){
				PaymentTransaction payment = paymentDao.getById(data.getId());
				Community comm = communityDao.getByName(payment.getDesc());
				Balance balance = balanceDao.findbyUserId(comm.getCreatedBy());
				BigDecimal balanceResult = balance.getBalance().add(comm.getCommunityPrice().multiply(new BigDecimal(0.9d)));
				balance.setBalance(balanceResult);
				balanceDao.save(balance);
				
				User system = userDao.findByRoleCode(RoleType.SYSTEM.name());
				Balance balanceSystem = balanceDao.findbyUserId(system.getId());
				BigDecimal balanceSystemResult = balanceSystem.getBalance().add(comm.getCommunityPrice().multiply(new BigDecimal(0.1d)));
				balanceSystem.setBalance(balanceSystemResult);
				balanceDao.save(balanceSystem);
			}
			
			commit();
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());

			updateRes.setData(resData);
			updateRes.setMessage("Aprovall succes");
			return updateRes;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
