package com.jurisdiction.system.shiro;


import com.jurisdiction.common.config.SpringContextHolder;
import com.jurisdiction.common.utils.ShiroUtils;
import com.jurisdiction.system.dao.SysUserDao;
import com.jurisdiction.system.entity.SysUser;
import com.jurisdiction.system.service.SessionService;
import com.jurisdiction.system.service.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class UserRealm extends AuthorizingRealm {


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Long userId = ShiroUtils.getUserId();
		SysMenuService menuService = SpringContextHolder.getBean(SysMenuService.class);
		Set<String> perms = menuService.listPerms(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(perms);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		Map<String, Object> map = new HashMap<>(16);
		map.put("username", username);
		String password = new String((char[]) token.getCredentials());


		SysUserDao userMapper = SpringContextHolder.getBean(SysUserDao.class);
		// 查询用户信息
		SysUser user = userMapper.list(map).get(0);

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		SessionService sessionService = SpringContextHolder.getBean(SessionService.class);
		Session session =sessionService.getSessionByUserName(username);
		if(session !=null){
			session.setTimeout(0);
		}

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
