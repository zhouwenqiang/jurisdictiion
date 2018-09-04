package com.jurisdiction.system.service;

import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.system.entity.SysDept;
import com.jurisdiction.system.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 */
public interface SysDeptService {
	
	SysDept get(Long deptId);
	
	List<SysDept> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysDept sysDept);
	
	int update(SysDept sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	SysTree<SysDept> getTree(Map<String,Object> map);
	
	boolean checkDeptHasUser(Long deptId);
	 void getNode(Long pid,List<SysTree<SysDept>> list);
}
