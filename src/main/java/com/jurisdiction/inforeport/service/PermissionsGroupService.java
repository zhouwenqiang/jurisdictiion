package com.jurisdiction.inforeport.service;

import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.inforeport.entity.PermissionsGroup;

import java.util.List;
import java.util.Map;

/**
 * 数据权限组
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-15 14:33:08
 */
public interface PermissionsGroupService {

	PermissionsGroup get(Long groupId);
	
	List<PermissionsGroup> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PermissionsGroup permissionsGroup);
	
	int update(PermissionsGroup permissionsGroup);
	
	int remove(Long groupId);
	
	int batchRemove(Long[] groupIds);

	SysTree<PermissionsGroup> getTree(Long userId);

	SysTree<PermissionsGroup> getTree(Long userId,Long sysUserId);
}
