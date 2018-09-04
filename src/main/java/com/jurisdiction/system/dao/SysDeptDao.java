package com.jurisdiction.system.dao;

import com.jurisdiction.system.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * @author zwq
 */
@Mapper
public interface SysDeptDao {

	SysDept get(Long deptId);
	
	List<SysDept> list(Map<String, Object> map);

	List<SysDept> listTree(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(SysDept dept);
	
	int update(SysDept dept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);
	
	Long[] listParentDept();
	
	int getDeptUserNumber(Long deptId);
}
