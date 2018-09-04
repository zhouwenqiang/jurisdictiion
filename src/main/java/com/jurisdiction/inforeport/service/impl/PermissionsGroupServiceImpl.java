package com.jurisdiction.inforeport.service.impl;

import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.common.utils.BuildTree;
import com.jurisdiction.inforeport.dao.DatapermissionsgroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jurisdiction.inforeport.dao.PermissionsGroupDao;
import com.jurisdiction.inforeport.entity.PermissionsGroup;
import com.jurisdiction.inforeport.service.PermissionsGroupService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PermissionsGroupServiceImpl implements PermissionsGroupService {
	@Autowired
	private PermissionsGroupDao permissionsGroupDao;
	@Autowired
	private DatapermissionsgroupDao datapermissionsgroupDao;

	@Override
	public PermissionsGroup get(Long groupId){
		return permissionsGroupDao.get(groupId);
	}
	
	@Override
	public List<PermissionsGroup> list(Map<String, Object> map){
		return permissionsGroupDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return permissionsGroupDao.count(map);
	}
	
	@Override
	public int save(PermissionsGroup permissionsGroup){
		return permissionsGroupDao.save(permissionsGroup);
	}
	
	@Override
	public int update(PermissionsGroup permissionsGroup){
		return permissionsGroupDao.update(permissionsGroup);
	}

	@Transactional
	@Override
	public int remove(Long groupId){
		datapermissionsgroupDao.removeByGroupId(groupId);
		return permissionsGroupDao.remove(groupId);
	}
	
	@Override
	public int batchRemove(Long[] groupIds){
		return permissionsGroupDao.batchRemove(groupIds);
	}
	@Override
	public SysTree<PermissionsGroup> getTree(Long id) {
		List<SysTree<PermissionsGroup>> sysTrees = new ArrayList<SysTree<PermissionsGroup>>();
		List<PermissionsGroup> menuDOs = permissionsGroupDao.listGroupByUserIdAll(id);
		for (PermissionsGroup permissionsGroup : menuDOs) {
			SysTree<PermissionsGroup> sysTree = new SysTree<PermissionsGroup>();
			sysTree.setId(permissionsGroup.getGroupId().toString());
			sysTree.setParentId(permissionsGroup.getParentId().toString());
			sysTree.setText(permissionsGroup.getName());
			sysTrees.add(sysTree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		SysTree<PermissionsGroup> t = BuildTree.build(sysTrees);
		return t;
	}

	@Override
	public SysTree<PermissionsGroup> getTree(Long uid,Long userId) {

		List<PermissionsGroup> menus = permissionsGroupDao.listGroupByUserIdAll(userId);
		List<Long> menuIds = datapermissionsgroupDao.listGroupByUserId(uid);
		List<Long> temp = menuIds;
		for (PermissionsGroup menu : menus) {
			if (temp.contains(menu.getParentId())) {
				menuIds.remove(menu.getParentId());
			}
		}
		List<SysTree<PermissionsGroup>> sysTrees = new ArrayList<SysTree<PermissionsGroup>>();
		List<PermissionsGroup> permissionsGrouplist = permissionsGroupDao.listGroupByUserIdAll(userId);
		for (PermissionsGroup termissionsGroup : permissionsGrouplist) {
			SysTree<PermissionsGroup> sysTree = new SysTree<PermissionsGroup>();
			sysTree.setId(termissionsGroup.getGroupId().toString());
			sysTree.setParentId(termissionsGroup.getParentId().toString());
			sysTree.setText(termissionsGroup.getName());
			Map<String, Object> state = new HashMap<>(16);
			Long menuId = termissionsGroup.getGroupId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			sysTree.setState(state);
			sysTrees.add(sysTree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		SysTree<PermissionsGroup> t = BuildTree.build(sysTrees);
		return t;
	}
}
