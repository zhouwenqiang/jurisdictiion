package com.jurisdiction.inforeport.dao;

import com.jurisdiction.inforeport.entity.CitySeparate;
import com.jurisdiction.inforeport.entity.DetailsSeparate;
import com.jurisdiction.inforeport.entity.RegionSeparate;
import com.jurisdiction.inforeport.entity.Separate;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-28 15:35:49
 */
@Mapper
public interface SeparateDao {

	Separate get(Integer id);
	
	List<Separate> list(Map<String, Object> map);

	List<CitySeparate> listCity(Map<String, Object> map);

	List<RegionSeparate> listRegion(Map<String, Object> map);

	List<DetailsSeparate> listDetails(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(Separate separate);
	
	int update(Separate separate);
	
	int remove(Integer ID);
	
	int batchRemove(Integer[] ids);

	List <CitySeparate> groupSelectData(Integer pid);
}
