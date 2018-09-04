package com.jurisdiction.inforeport.dao;

import com.jurisdiction.inforeport.entity.Datapermissionsgroup;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与数据权限组
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-17 11:22:43
 */
@Mapper
public interface DatapermissionsgroupDao {

	Datapermissionsgroup get(Long id);
	
	List<Datapermissionsgroup> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Datapermissionsgroup datapermissionsgroup);

	int batchSave(List<Datapermissionsgroup> list);

	int update(Datapermissionsgroup datapermissionsgroup);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int removeByUserId(Long userId);

	int removeByGroupId(Long groupId);

	List<Long> listGroupByUserId(Long userId);
}
