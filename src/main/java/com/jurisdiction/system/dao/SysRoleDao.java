package com.jurisdiction.system.dao;

import com.jurisdiction.system.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * @author chglee
 * @email
 * @date 2017-10-02 20:24:47
 */
@Mapper
public interface SysRoleDao {

	SysRole get(Long roleId);
	
	List<SysRole> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysRole role);
	
	int update(SysRole role);
	
	int remove(Long roleId);
	
	int batchRemove(Long[] roleIds);
}
