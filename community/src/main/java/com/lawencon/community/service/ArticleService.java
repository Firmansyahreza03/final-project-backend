package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ArticleDao;
import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Article;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.article.PojoDataArticle;
import com.lawencon.community.pojo.article.PojoFindByIdArticleRes;
import com.lawencon.community.pojo.article.PojoInsertArticleReq;
import com.lawencon.community.pojo.article.PojoUpdateArticleReq;
import com.lawencon.model.SearchQuery;
@Service
public class ArticleService extends BaseCoreService{
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private IndustryDao industryDao;

	public Article inputArticleData(Article result, Boolean isActive, String title, String content,
			String idUser, String idIndustry) {
		User fkUser = userDao.getById(idUser);
		Industry fkIndustry = industryDao.getById(idIndustry);
	
		result.setIsActive(isActive);
		
		result.setArticleTitle(title);
		result.setArticleContent(content);
		
		result.setUser(fkUser);
		result.setIndustry(fkIndustry);

		return result;
	}

	private PojoDataArticle modelToRes(Article data) {
		PojoDataArticle result = new PojoDataArticle();

		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());
		
		
		return result;
	}
	
	public PojoFindByIdArticleRes findById(String id) throws Exception {
		Article data = articleDao.getById(id);
				
		PojoDataArticle result = modelToRes(data);
		PojoFindByIdArticleRes resultData = new PojoFindByIdArticleRes();
		resultData.setData(result);
		
		return resultData;
	}
	
	public SearchQuery<PojoDataArticle> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Article> getAllArticle= articleDao.findAll(query, startPage, maxPage);
		List<PojoDataArticle> resultList = new ArrayList<>();
		
		getAllArticle.getData().forEach(d -> {
			PojoDataArticle data = modelToRes(d);
			
			resultList.add(data);
			
		});
		SearchQuery<PojoDataArticle> result = new SearchQuery<PojoDataArticle>();
		result.setData(resultList);
		result.setCount(getAllArticle.getCount());
		return result;
	}

	@Transactional(rollbackOn = Exception.class)
	public PojoInsertRes insert(PojoInsertArticleReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();
			
			Article reqData = inputArticleData(new Article(), data.getName(), data.getCode(), true);
			reqData.setCreatedBy("1");
			
			begin();
			Article result = articleDao.save(reqData);
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());
			
			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding Article");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public PojoUpdateRes update(PojoUpdateArticleReq data) throws Exception {
		try {
			PojoUpdateRes updateRes = new PojoUpdateRes();
			Article reqData = articleDao.getById(data.getId());
			
			reqData = inputArticleData(reqData, data.getName(), reqData.getArticleCode(), data.getIsActive());

			begin();
			Article result = articleDao.save(reqData);
			commit();
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());
			
			updateRes.setData(resData);
			updateRes.setMessage("Successfully Editing Article");
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
			boolean result = articleDao.deleteById(id);
			commit();
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if(result)
				deleteRes.setMessage("Successfully Delete Article");
			else 
				deleteRes.setMessage("Failed Delete Article");
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}