package com.jurisdiction.inforeport.service.impl;

import com.jurisdiction.inforeport.entity.CitySeparate;
import com.jurisdiction.inforeport.entity.DetailsSeparate;
import com.jurisdiction.inforeport.entity.RegionSeparate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jurisdiction.inforeport.dao.SeparateDao;
import com.jurisdiction.inforeport.entity.Separate;
import com.jurisdiction.inforeport.service.SeparateService;



@Service
public class SeparateServiceImpl implements SeparateService {
	@Autowired
	private SeparateDao separateDao;
	
	@Override
	public Separate get(Integer id){
		return separateDao.get(id);
	}
	
	@Override
	public List<Separate> list(Map<String, Object> map){
		return separateDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return separateDao.count(map);
	}
	
	@Override
	public int save(Separate separate){
		return separateDao.save(separate);
	}
	
	@Override
	public int update(Separate separate){
		return separateDao.update(separate);
	}
	
	@Override
	public int remove(Integer id){
		return separateDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return separateDao.batchRemove(ids);
	}

	@Override
	public List<CitySeparate> listCity(Map<String, Object> map) {
		return separateDao.listCity(map);
	}
	@Override
	public List<RegionSeparate> listRegion(Map<String, Object> map) {
		return separateDao.listRegion(map);
	}

	@Override
	public List<DetailsSeparate> listDetails(Map<String, Object> map) {
		return separateDao.listDetails(map);
	}

	@Override
	public List<CitySeparate> groupSelectData(Integer pid) {
		return separateDao.groupSelectData(pid);
	}

}
