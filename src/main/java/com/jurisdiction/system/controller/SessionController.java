package com.jurisdiction.system.controller;


import com.jurisdiction.common.annotation.Log;
import com.jurisdiction.common.utils.R;
import com.jurisdiction.system.entity.SysUserOnline;
import com.jurisdiction.system.service.SessionService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;

@RequestMapping("/sys/online")
@Controller
public class SessionController {
	@Autowired
	SessionService sessionService;

	@Log("session")
	@GetMapping()
	public String online() {
		return "system/online/online";
	}
	@Log("session")
	@ResponseBody
	@RequestMapping("/list")
	public List<SysUserOnline> list() {
		return sessionService.list();
	}

	@Log("session")
	@ResponseBody
	@RequestMapping("/forceLogout/{sessionId}")
	public R forceLogout(@PathVariable("sessionId") String sessionId, RedirectAttributes redirectAttributes) {
		try {
			sessionService.forceLogout(sessionId);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}

	}
	@Log("session")
	@ResponseBody
	@RequestMapping("/sessionList")
	public Collection<Session> sessionList() {
		return sessionService.sessionList();
	}


}
