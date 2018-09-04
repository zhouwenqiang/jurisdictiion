package com.jurisdiction.system.dao;

import com.jurisdiction.system.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * @author chglee
 * @email
 * @date 2017-10-03 09:45:09
 */
@Mapper
public interface SysMenuDao {

	SysMenu get(Long menuId);
	
	List<SysMenu> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysMenu menu);
	
	int update(SysMenu menu);
	
	int remove(Long menuId);
	
	int batchRemove(Long[] menuIds);
	
	List<SysMenu> listMenuByUserId(Long id);

	List<SysMenu> listMenuByUserIdAll(Long userId);

	List<String> listUserPerms(Long id);
}
