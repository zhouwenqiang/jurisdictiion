package com.jurisdiction.inforeport.service;

import com.jurisdiction.inforeport.entity.Datapermissionsgroup;

import java.util.List;
import java.util.Map;

/**
 * 用户与数据权限组
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-17 11:22:43
 */
public interface DatapermissionsgroupService {
	
	Datapermissionsgroup get(Long id);
	
	List<Datapermissionsgroup> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Datapermissionsgroup datapermissionsgroup);
	
	int update(Datapermissionsgroup datapermissionsgroup);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
