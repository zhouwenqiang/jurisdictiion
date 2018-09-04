package com.jurisdiction.system.controller;


import com.jurisdiction.common.annotation.Log;
import com.jurisdiction.common.config.Constant;
import com.jurisdiction.common.controller.BaseController;
import com.jurisdiction.common.utils.DateUtils;
import com.jurisdiction.common.utils.R;
import com.jurisdiction.system.entity.SysRole;
import com.jurisdiction.system.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/sys/role")
@Controller
public class SysRoleController extends BaseController {
	String prefix = "system/role";
	@Autowired
	SysRoleService roleService;

	@Log("role")
	@RequiresPermissions("sys:role:role")
	@GetMapping()
	String role() {
		return prefix + "/role";
	}

	@Log("role")
	@RequiresPermissions("sys:role:role")
	@GetMapping("/list")
	@ResponseBody()
	List<SysRole> list() {
		Map<String, Object> map= new HashMap<>();
		if(!"admin".equals(getSysUser().getUsername())){
			map.put("deptId",getSysUser().getDeptId());
		}
		List<SysRole> roles = roleService.list(map);
		return roles;
	}
	@Log("role")
	@RequiresPermissions("sys:role:add")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@Log("role")
	@RequiresPermissions("sys:role:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		SysRole roleDO = roleService.get(id);
		model.addAttribute("role", roleDO);
		return prefix + "/edit";
	}
	@Log("role")
	@RequiresPermissions("sys:role:add")
	@PostMapping("/save")
	@ResponseBody()
	R save(SysRole role) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		role.setUserIdCreate(getSysUserId());
		role.setGmtCreate(DateUtils.getTimestamp());
		role.setGmtModified(DateUtils.getTimestamp());
		if (roleService.save(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}
	@Log("role")
	@RequiresPermissions("sys:role:edit")
	@PostMapping("/update")
	@ResponseBody()
	R update(SysRole role) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.update(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}
	@Log("role")
	@RequiresPermissions("sys:role:remove")
	@PostMapping("/remove")
	@ResponseBody()
	R save(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}
	@Log("role")
	@RequiresPermissions("sys:role:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = roleService.batchremove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
}
