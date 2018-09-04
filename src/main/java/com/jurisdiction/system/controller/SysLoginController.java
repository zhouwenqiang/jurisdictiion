package com.jurisdiction.system.controller;


import com.jurisdiction.common.annotation.Log;
import com.jurisdiction.common.controller.BaseController;
import com.jurisdiction.common.entity.SysFile;
import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.common.service.SysFileService;
import com.jurisdiction.common.utils.MD5Utils;
import com.jurisdiction.common.utils.R;
import com.jurisdiction.common.utils.ShiroUtils;
import com.jurisdiction.system.entity.SysMenu;
import com.jurisdiction.system.service.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SysLoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SysMenuService menuService;
	@Autowired
	SysFileService fileService;
	@Log("login:view")
	@GetMapping({ "/", "" })
	String welcome(Model model) {
		return "redirect:/login";
	}

	@Log("login")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<SysTree<SysMenu>> menus = menuService.listMenuTree(getSysUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getSysUser().getName());
		SysFile sysFile = fileService.get(getSysUser().getPicId());
		if(sysFile!=null&&sysFile.getUrl()!=null){
			if(fileService.isExist(sysFile.getUrl())){
				model.addAttribute("picUrl",sysFile.getUrl());
			}else {
				model.addAttribute("picUrl","/img/zgyd_s.png");
			}
		}else {
			model.addAttribute("picUrl","/img/zgyd_s.png");
		}
		model.addAttribute("username", getSysUser().getUsername());
		return "index_v1";
	}
	@Log("login")
	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("login")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {

		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}
	@Log("login")
	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}
	@Log("login")
	@GetMapping("/main")
	String main() {
		return "main";
	}

}
