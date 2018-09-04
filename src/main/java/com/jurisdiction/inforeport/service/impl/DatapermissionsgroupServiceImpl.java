package com.jurisdiction.inforeport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jurisdiction.inforeport.dao.DatapermissionsgroupDao;
import com.jurisdiction.inforeport.entity.Datapermissionsgroup;
import com.jurisdiction.inforeport.service.DatapermissionsgroupService;



@Service
public class DatapermissionsgroupServiceImpl implements DatapermissionsgroupService {
	@Autowired
	private DatapermissionsgroupDao datapermissionsgroupDao;
	
	@Override
	public Datapermissionsgroup get(Long id){
		return datapermissionsgroupDao.get(id);
	}
	
	@Override
	public List<Datapermissionsgroup> list(Map<String, Object> map){
		return datapermissionsgroupDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return datapermissionsgroupDao.count(map);
	}
	
	@Override
	public int save(Datapermissionsgroup datapermissionsgroup){
		return datapermissionsgroupDao.save(datapermissionsgroup);
	}
	
	@Override
	public int update(Datapermissionsgroup datapermissionsgroup){
		return datapermissionsgroupDao.update(datapermissionsgroup);
	}
	
	@Override
	public int remove(Long id){
		return datapermissionsgroupDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return datapermissionsgroupDao.batchRemove(ids);
	}
	
}
