package com.jurisdiction.system.dao;

import com.jurisdiction.system.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 * @author chglee
 * @email
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface SysRoleMenuDao {

	SysRoleMenu get(Long id);
	
	List<SysRoleMenu> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysRoleMenu roleMenu);
	
	int update(SysRoleMenu roleMenu);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	List<Long> listMenuIdByRoleId(Long roleId);
	
	int removeByRoleId(Long roleId);

	int removeByMenuId(Long menuId);
	
	int batchSave(List<SysRoleMenu> list);
}
