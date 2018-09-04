package com.jurisdiction.system.service.impl;


import com.jurisdiction.system.dao.SysRoleDao;
import com.jurisdiction.system.dao.SysRoleMenuDao;
import com.jurisdiction.system.dao.SysUserDao;
import com.jurisdiction.system.dao.SysUserRoleDao;
import com.jurisdiction.system.entity.SysRole;
import com.jurisdiction.system.entity.SysRoleMenu;
import com.jurisdiction.system.entity.User;
import com.jurisdiction.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class SysRoleServiceImpl implements SysRoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    SysRoleDao roleMapper;
    @Autowired
    SysRoleMenuDao roleMenuMapper;
    @Autowired
    SysUserDao userMapper;
    @Autowired
    SysUserRoleDao userRoleMapper;

    @Override
    public List<SysRole> list() {
        List<SysRole> roles = roleMapper.list(new HashMap<>(16));
        return roles;
    }

    @Override
    public List<SysRole> list(Map<String, Object> map) {
        List<SysRole> roles = roleMapper.list(map);
        return roles;
    }
    @Override
    public List<SysRole> list(Long userId,Map<String, Object> map) {
        List<Long> rolesIds = userRoleMapper.listRoleId(userId);
        List<SysRole> roles = roleMapper.list(map);
        for (SysRole roleDO : roles) {
            roleDO.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(roleDO.getRoleId(), roleId)) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }
    @Transactional
    @Override
    public int save(SysRole role) {
        int count = roleMapper.save(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        List<SysRoleMenu> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            SysRoleMenu rmDo = new SysRoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return count;
    }

    @Transactional
    @Override
    public int remove(Long id) {
        int count = roleMapper.remove(id);
        userRoleMapper.removeByRoleId(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public SysRole get(Long id) {
        SysRole roleDO = roleMapper.get(id);
        return roleDO;
    }

    @Override
    public int update(SysRole role) {
        int r = roleMapper.update(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        roleMenuMapper.removeByRoleId(roleId);
        List<SysRoleMenu> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            SysRoleMenu rmDo = new SysRoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return r;
    }

    @Override
    public int batchremove(Long[] ids) {
        int r = roleMapper.batchRemove(ids);
        return r;
    }

}
