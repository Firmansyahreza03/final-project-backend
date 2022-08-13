package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.model.File;
import com.lawencon.community.pojo.PojoDeleteRes;
import com.lawencon.community.pojo.PojoInsertRes;
import com.lawencon.community.pojo.PojoInsertResData;
import com.lawencon.community.pojo.PojoUpdateRes;
import com.lawencon.community.pojo.PojoUpdateResData;
import com.lawencon.community.pojo.file.PojoDataFile;
import com.lawencon.community.pojo.file.PojoFindByIdFileRes;
import com.lawencon.community.pojo.file.PojoInsertFileReq;
import com.lawencon.community.pojo.file.PojoUpdateFileReq;
import com.lawencon.model.SearchQuery;
@Service
public class FileService extends BaseCoreService<File>{
	@Autowired
	private FileDao fileDao;

	private File inputFileData(File result, Boolean isActive, String name, String ext) throws Exception {

		result.setIsActive(isActive);

		result.setFileName(name);
		result.setFileExtension(ext);

		return result;
	}

	private PojoDataFile modelToRes(File data) throws Exception {
		PojoDataFile result = new PojoDataFile();
		
		result.setId(data.getId());
		result.setIsActive(data.getIsActive());
		result.setVersion(data.getVersion());

		result.setName(data.getFileName());
		result.setExtension(data.getFileExtension());
		
		return result;
	}
	
	public PojoFindByIdFileRes findById(String id) throws Exception {
		File data = fileDao.getById(id);
				
		PojoDataFile result = modelToRes(data);
		PojoFindByIdFileRes resultData = new PojoFindByIdFileRes();
		resultData.setData(result);
		
		return resultData;
	}
	
	public SearchQuery<PojoDataFile> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<File> fileList= fileDao.findAll(query, startPage, maxPage);
		List<PojoDataFile> resultList = new ArrayList<>();
		
		fileList.getData().forEach(d -> {
			PojoDataFile data;
			try {
				data = modelToRes(d);
				resultList.add(data);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		
		SearchQuery<PojoDataFile> result = new SearchQuery<PojoDataFile>();
		result.setData(resultList);
		result.setCount(fileList.getCount());
		return result;
	}

	public PojoInsertRes insert(PojoInsertFileReq data) throws Exception {
		try {
			PojoInsertRes insertRes = new PojoInsertRes();
			
			File reqData = inputFileData(new File(), true, data.getName(), data.getExtension());
			
			begin();
			File result = super.save(reqData);
			commit();
			PojoInsertResData resData = new PojoInsertResData();
			resData.setId(result.getId());
			
			insertRes.setData(resData);
			insertRes.setMessage("Successfully Adding File");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PojoUpdateRes update(PojoUpdateFileReq data) throws Exception {
		try {
			PojoUpdateRes updateRes = new PojoUpdateRes();
			File reqData = fileDao.getById(data.getId());

			reqData = inputFileData(reqData, data.getIsActive(), data.getName(), data.getExtension());
			begin();
			File result = fileDao.save(reqData);
			commit();
			PojoUpdateResData resData = new PojoUpdateResData();
			resData.setVersion(result.getVersion());
			
			updateRes.setData(resData);
			updateRes.setMessage("Successfully Editing File");
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
			boolean result = fileDao.deleteById(id);
			commit();
			PojoDeleteRes deleteRes = new PojoDeleteRes();
			if(result)
				deleteRes.setMessage("Successfully Delete File");
			else 
				deleteRes.setMessage("Failed Delete File");
			return deleteRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	

	public File getById(String id) throws Exception {
		return fileDao.getById(id);
	}
}
