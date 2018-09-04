package com.jurisdiction.system.service.impl;


import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.common.utils.BuildTree;
import com.jurisdiction.system.dao.SysMenuDao;
import com.jurisdiction.system.dao.SysRoleMenuDao;
import com.jurisdiction.system.entity.SysMenu;
import com.jurisdiction.system.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SuppressWarnings("AlibabaRemoveCommentedCode")
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	SysMenuDao menuMapper;
	@Autowired
	SysRoleMenuDao roleMenuMapper;

	/**
	 * @param
	 * @return 树形菜单
	 */
	@Cacheable
	@Override
	public SysTree<SysMenu> getSysMenuTree(Long id) {
		List<SysTree<SysMenu>> sysTrees = new ArrayList<SysTree<SysMenu>>();
		List<SysMenu> menuDOs = menuMapper.listMenuByUserId(id);
		for (SysMenu sysSysMenu : menuDOs) {
			SysTree<SysMenu> sysTree = new SysTree<SysMenu>();
			sysTree.setId(sysSysMenu.getMenuId().toString());
			sysTree.setParentId(sysSysMenu.getParentId().toString());
			sysTree.setText(sysSysMenu.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysSysMenu.getUrl());
			attributes.put("icon", sysSysMenu.getIcon());
			sysTree.setAttributes(attributes);
			sysTrees.add(sysTree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		SysTree<SysMenu> t = BuildTree.build(sysTrees);
		return t;
	}

	@Override
	public List<SysMenu> list(Map<String, Object> params) {
		List<SysMenu> menus = menuMapper.list(params);
		return menus;
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int remove(Long id) {
		int result = menuMapper.remove(id);
		roleMenuMapper.removeByMenuId(id);
		return result;
	}
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int save(SysMenu menu) {
		int r = menuMapper.save(menu);
		return r;
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int update(SysMenu menu) {
		int r = menuMapper.update(menu);
		return r;
	}

	@Override
	public SysMenu get(Long id) {
		SysMenu menuDO = menuMapper.get(id);
		return menuDO;
	}

	@Override
	public SysTree<SysMenu> getTree(Long id) {
		List<SysTree<SysMenu>> sysTrees = new ArrayList<SysTree<SysMenu>>();
		List<SysMenu> menuDOs = menuMapper.listMenuByUserIdAll(id);
		for (SysMenu sysSysMenu : menuDOs) {
			SysTree<SysMenu> sysTree = new SysTree<SysMenu>();
			sysTree.setId(sysSysMenu.getMenuId().toString());
			sysTree.setParentId(sysSysMenu.getParentId().toString());
			sysTree.setText(sysSysMenu.getName());
			sysTrees.add(sysTree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		SysTree<SysMenu> t = BuildTree.build(sysTrees);
		return t;
	}

	@Override
	public SysTree<SysMenu> getTree(Long roleId,Long userId) {
		// 根据roleId查询权限
		List<SysMenu> menus = menuMapper.listMenuByUserIdAll(userId);
		List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(roleId);
		List<Long> temp = menuIds;
		for (SysMenu menu : menus) {
			if (temp.contains(menu.getParentId())) {
				menuIds.remove(menu.getParentId());
			}
		}
		List<SysTree<SysMenu>> sysTrees = new ArrayList<SysTree<SysMenu>>();
		List<SysMenu> menuDOs = menuMapper.listMenuByUserIdAll(userId);
		for (SysMenu sysSysMenu : menuDOs) {
			SysTree<SysMenu> sysTree = new SysTree<SysMenu>();
			sysTree.setId(sysSysMenu.getMenuId().toString());
			sysTree.setParentId(sysSysMenu.getParentId().toString());
			sysTree.setText(sysSysMenu.getName());
			Map<String, Object> state = new HashMap<>(16);
			Long menuId = sysSysMenu.getMenuId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			sysTree.setState(state);
			sysTrees.add(sysTree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		SysTree<SysMenu> t = BuildTree.build(sysTrees);
		return t;
	}

	@Override
	public Set<String> listPerms(Long userId) {
		List<String> perms = menuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	@Override
	public List<SysTree<SysMenu>> listMenuTree(Long id) {
		List<SysTree<SysMenu>> sysTrees = new ArrayList<SysTree<SysMenu>>();
		List<SysMenu> menuDOs = menuMapper.listMenuByUserId(id);
		for (SysMenu sysSysMenu : menuDOs) {
			SysTree<SysMenu> sysTree = new SysTree<SysMenu>();
			sysTree.setId(sysSysMenu.getMenuId().toString());
			sysTree.setParentId(sysSysMenu.getParentId().toString());
			sysTree.setText(sysSysMenu.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysSysMenu.getUrl());
			attributes.put("icon", sysSysMenu.getIcon());
			sysTree.setAttributes(attributes);
			sysTrees.add(sysTree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<SysTree<SysMenu>> list = BuildTree.buildList(sysTrees, "0");
		return list;
	}

}
