package com.jurisdiction.system.service;

import com.jurisdiction.system.entity.SysRole;
import com.jurisdiction.system.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SysRoleService {

	SysRole get(Long id);

	List<SysRole> list();

	List<SysRole> list(Map<String, Object> map);

	int save(SysRole role);

	int update(SysRole role);

	int remove(Long id);

	List<SysRole> list(Long userId,Map<String, Object> map);

	int batchremove(Long[] ids);
}
