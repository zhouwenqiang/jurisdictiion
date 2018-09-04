package com.jurisdiction.inforeport.service;

import com.jurisdiction.inforeport.entity.CitySeparate;
import com.jurisdiction.inforeport.entity.DetailsSeparate;
import com.jurisdiction.inforeport.entity.RegionSeparate;
import com.jurisdiction.inforeport.entity.Separate;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-28 15:35:49
 */
public interface SeparateService {
	
	Separate get(Integer id);
	
	List<Separate> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Separate separate);
	
	int update(Separate separate);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<CitySeparate> listCity(Map<String, Object> map);

	List<RegionSeparate> listRegion(Map<String, Object> map);

	List<DetailsSeparate> listDetails(Map<String, Object> map);

	List <CitySeparate> groupSelectData(Integer pid);
}
