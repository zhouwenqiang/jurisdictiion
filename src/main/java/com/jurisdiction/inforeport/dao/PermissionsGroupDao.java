package com.jurisdiction.inforeport.dao;

import com.jurisdiction.inforeport.entity.PermissionsGroup;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 数据权限组
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-15 14:33:08
 */
@Mapper
public interface PermissionsGroupDao {

	PermissionsGroup get(Long groupId);
	
	List<PermissionsGroup> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PermissionsGroup permissionsGroup);
	
	int update(PermissionsGroup permissionsGroup);
	
	int remove(Long group_id);
	
	int batchRemove(Long[] groupIds);

	List<PermissionsGroup> listGroupByUserIdAll(Long id);

}
