package com.jurisdiction.system.controller;


import com.jurisdiction.common.annotation.Log;
import com.jurisdiction.common.config.Constant;
import com.jurisdiction.common.controller.BaseController;
import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.common.service.SysDictService;
import com.jurisdiction.common.utils.MD5Utils;
import com.jurisdiction.common.utils.PageUtils;
import com.jurisdiction.common.utils.Query;
import com.jurisdiction.common.utils.R;
import com.jurisdiction.system.entity.SysDept;
import com.jurisdiction.system.entity.SysRole;
import com.jurisdiction.system.entity.SysUser;
import com.jurisdiction.system.entity.User;
import com.jurisdiction.system.service.SysRoleService;
import com.jurisdiction.system.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/sys/user")
@Controller
public class SysUserController extends BaseController {
	private String prefix="system/user"  ;
	@Autowired
	SysUserService userService;
	@Autowired
	SysRoleService roleService;
	@Autowired
	SysDictService dictService;
	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	@Log("user")
	String user(Model model) {
		return prefix + "/user";
	}
	@Log("user")
	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		if(!"admin".equals(getSysUsername()) && params.get("deptId").equals("")){
			params.put("deptId",getSysUser().getDeptId());
		}
		Query query = new Query(params);
		List<SysUser> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@Log("user")
	@RequiresPermissions("sys:user:add")
	@GetMapping("/add")
	String add(Model model) {
		Map<String, Object> map= new HashMap<>();
		if(!"admin".equals(getSysUser().getUsername())){
			map.put("deptId",getSysUser().getDeptId());
		}
		List<SysRole> roles = roleService.list(map);
		model.addAttribute("roles", roles);
		return prefix + "/add";
	}

	@Log("user")
	@RequiresPermissions("sys:user:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		Map<String, Object> map= new HashMap<>();
		SysUser userDO = userService.get(id);
		model.addAttribute("user", userDO);
		if(!"admin".equals(getSysUser().getUsername())){
			map.put("deptId",userDO.getDeptId());
		}
		List<SysRole> roles = roleService.list(id,map);
		model.addAttribute("roles", roles);
		return prefix+"/edit";
	}
	@Log("user")
	@RequiresPermissions("sys:user:add")
	@PostMapping("/save")
	@ResponseBody
	R save(SysUser user) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		user.setUserIdCreate(getSysUserId());
		user.setGmtCreate(new Date());
		user.setGmtModified(new Date());
		if (userService.save(user) > 0) {
			return R.ok();
		}
		return R.error();
	}
	@Log("user")
	@RequiresPermissions("sys:user:edit")
	@PostMapping("/update")
	@ResponseBody
	R update(SysUser user) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		user.setUserIdCreate(getSysUserId());
		user.setGmtModified(new Date());
		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@Log("user")
	@RequiresPermissions("sys:user:edit")
	@PostMapping("/updatePeronal")
	@ResponseBody
	R updatePeronal(SysUser user) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		user.setUserIdCreate(getSysUserId());
		user.setGmtModified(new Date());
		if (userService.updatePersonal(user) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@Log("user")
	@RequiresPermissions("sys:user:remove")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}
	@Log("user")
	@RequiresPermissions("sys:user:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
	@Log("user")
	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params);
	}

	@Log("user")
	@RequiresPermissions("sys:user:resetPwd")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {

		SysUser userDO = new SysUser();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/reset_pwd";
	}

	@Log("user")
	@PostMapping("/resetPwd")
	@ResponseBody
	R resetPwd(User user) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.resetPwd(user,getSysUser());
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}
	@Log("user")
	@RequiresPermissions("sys:user:resetPwd")
	@PostMapping("/adminResetPwd")
	@ResponseBody
	R adminResetPwd(User user) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.adminResetPwd(user);
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}
	@Log("user")
	@GetMapping("/tree")
	@ResponseBody
	public SysTree<SysDept> tree() {
		SysTree<SysDept> tree = new SysTree<SysDept>();
		tree = userService.getTree();
		return tree;
	}
	@Log("user")
	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}

	@GetMapping("/personal")
	@Log("user")
	String personal(Model model) {
		SysUser userDO  = userService.get(getSysUserId());
		model.addAttribute("user",userDO);
		model.addAttribute("hobbyList",dictService.getHobbyList(userDO));
		model.addAttribute("sexList",dictService.getSexList());
		return prefix + "/personal";
	}
	@ResponseBody
	@Log("user")
	@PostMapping("/uploadImg")
	R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		if ("test".equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> result = new HashMap<>();
		try {
			result = userService.updatePersonalImg(file, avatar_data, getSysUserId());
		} catch (Exception e) {
			return R.error("更新图像失败！");
		}
		if(result!=null && result.size()>0){
			return R.ok(result);
		}else {
			return R.error("更新图像失败！");
		}
	}
}
