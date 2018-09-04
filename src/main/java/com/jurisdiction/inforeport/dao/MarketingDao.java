package com.jurisdiction.inforeport.dao;

import com.jurisdiction.inforeport.entity.ActivityMarketing;
import com.jurisdiction.inforeport.entity.CityMarketing;
import com.jurisdiction.inforeport.entity.Marketing;

import java.util.List;
import java.util.Map;

import com.jurisdiction.inforeport.entity.TacMarketing;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author zwq
 * @email qq@qq.com
 * @date 2018-06-06 13:59:16
 */
@Mapper
public interface MarketingDao {

	Marketing get(Long id);
	
	List<Marketing> list(Map<String, Object> map);

	List<CityMarketing> listCity(Map<String, Object> map);

	List<TacMarketing> listTac(Map<String, Object> map);

	List<ActivityMarketing> listActivity(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Marketing marketing);
	
	int update(Marketing marketing);
	
	int remove(Long ID);
	
	int batchRemove(Long[] ids);
}
