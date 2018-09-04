package com.jurisdiction.inforeport.service.impl;

import com.jurisdiction.inforeport.entity.ActivityMarketing;
import com.jurisdiction.inforeport.entity.CityMarketing;
import com.jurisdiction.inforeport.entity.TacMarketing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jurisdiction.inforeport.dao.MarketingDao;
import com.jurisdiction.inforeport.entity.Marketing;
import com.jurisdiction.inforeport.service.MarketingService;



@Service
public class MarketingServiceImpl implements MarketingService {
	@Autowired
	private MarketingDao marketingDao;
	
	@Override
	public Marketing get(Long id){
		return marketingDao.get(id);
	}
	
	@Override
	public List<Marketing> list(Map<String, Object> map){
		return marketingDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return marketingDao.count(map);
	}
	
	@Override
	public int save(Marketing marketing){
		return marketingDao.save(marketing);
	}
	
	@Override
	public int update(Marketing marketing){
		return marketingDao.update(marketing);
	}
	
	@Override
	public int remove(Long id){
		return marketingDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return marketingDao.batchRemove(ids);
	}

	@Override
	public List<CityMarketing> listCity(Map<String, Object> map) {
		return marketingDao.listCity(map);
	}

	@Override
	public List<TacMarketing> listTac(Map<String, Object> map) {
		return marketingDao.listTac(map);
	}

	@Override
	public List<ActivityMarketing> listActivity(Map<String, Object> map) {
		return marketingDao.listActivity(map);
	}

}
