package com.jurisdiction.inforeport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jurisdiction.inforeport.dao.ImeiInfoDao;
import com.jurisdiction.inforeport.entity.ImeiInfo;
import com.jurisdiction.inforeport.service.ImeiInfoService;



@Service
public class ImeiInfoServiceImpl implements ImeiInfoService {
	@Autowired
	private ImeiInfoDao imeiInfoDao;
	
	@Override
	public ImeiInfo get(Integer imeiId){
		return imeiInfoDao.get(imeiId);
	}
	
	@Override
	public List<ImeiInfo> list(Map<String, Object> map){
		return imeiInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return imeiInfoDao.count(map);
	}
	
	@Override
	public int save(ImeiInfo imeiInfo){
		return imeiInfoDao.save(imeiInfo);
	}
	
	@Override
	public int update(ImeiInfo imeiInfo){
		return imeiInfoDao.update(imeiInfo);
	}
	
	@Override
	public int remove(Integer imeiId){
		return imeiInfoDao.remove(imeiId);
	}
	
	@Override
	public int batchRemove(Integer[] imeiIds){
		return imeiInfoDao.batchRemove(imeiIds);
	}
	
}
