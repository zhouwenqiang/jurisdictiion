package com.jurisdiction.system.service;

import com.jurisdiction.system.entity.SysUser;
import com.jurisdiction.system.entity.SysUserOnline;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface SessionService {
	List<SysUserOnline> list();

	List<SysUser> listOnlineUser();

	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);

	Session getSessionByUserName(String name);
}
