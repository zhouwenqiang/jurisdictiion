package com.jurisdiction.system.dao;

import com.jurisdiction.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * 
 * @author zwq
 */
@Mapper
public interface SysUserRoleDao {

	SysUserRole get(Long id);

	List<SysUserRole> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(SysUserRole userRole);

	int update(SysUserRole userRole);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<Long> listRoleId(Long userId);

	int removeByUserId(Long userId);

	int removeByRoleId(Long roleId);

	int batchSave(List<SysUserRole> list);

	int batchRemoveByUserId(Long[] ids);
}
