package com.jurisdiction.common.controller;

import com.jurisdiction.common.utils.ShiroUtils;
import com.jurisdiction.system.entity.SysUser;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	public SysUser getSysUser() {
		return ShiroUtils.getUser();
	}

	public Long getSysUserId() {
		return getSysUser().getUserId();
	}

	public String getSysUsername() {
		return getSysUser().getUsername();
	}
}