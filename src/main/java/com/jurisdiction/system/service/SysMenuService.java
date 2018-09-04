package com.jurisdiction.system.service;

import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.system.entity.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface SysMenuService {
	SysTree<SysMenu> getSysMenuTree(Long id);

	List<SysTree<SysMenu>> listMenuTree(Long id);

	SysTree<SysMenu> getTree(Long userId);

	SysTree<SysMenu> getTree(Long roleId,Long userId);

	List<SysMenu> list(Map<String, Object> params);

	int remove(Long id);

	int save(SysMenu menu);

	int update(SysMenu menu);

	SysMenu get(Long id);

	Set<String> listPerms(Long userId);
}
