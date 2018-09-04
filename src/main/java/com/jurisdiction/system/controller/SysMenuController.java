package com.jurisdiction.system.controller;


import com.jurisdiction.common.annotation.Log;
import com.jurisdiction.common.config.Constant;
import com.jurisdiction.common.controller.BaseController;
import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.common.utils.R;
import com.jurisdiction.system.entity.SysMenu;
import com.jurisdiction.system.service.SysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 */
@RequestMapping("/sys/menu")
@Controller
public class SysMenuController extends BaseController {
	String prefix = "system/menu";
	@Autowired
	SysMenuService menuService;

	@Log("menu")
	@RequiresPermissions("sys:menu:menu")
	@GetMapping()
	String menu(Model model) {
		return prefix+"/menu";
	}

	@Log("menu")
	@RequiresPermissions("sys:menu:menu")
	@RequestMapping("/list")
	@ResponseBody
	List<SysMenu> list(@RequestParam Map<String, Object> params) {
		List<SysMenu> menus = menuService.list(params);
		return menus;
	}
	@Log("menu")
	@RequiresPermissions("sys:menu:add")
	@GetMapping("/add/{pId}")
	String add(Model model, @PathVariable("pId") Long pId) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		return prefix + "/add";
	}

	@Log("menu")
	@RequiresPermissions("sys:menu:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		SysMenu mdo = menuService.get(id);
		Long pId = mdo.getParentId();
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		model.addAttribute("menu", mdo);
		return prefix+"/edit";
	}


	@Log("menu")
	@RequiresPermissions("sys:menu:add")
	@PostMapping("/save")
	@ResponseBody
	R save(SysMenu menu) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (menuService.save(menu) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("menu")
	@RequiresPermissions("sys:menu:edit")
	@PostMapping("/update")
	@ResponseBody
	R update(SysMenu menu) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (menuService.update(menu) > 0) {
			return R.ok();
		} else {
			return R.error(1, "更新失败");
		}
	}

	@Log("menu")
	@RequiresPermissions("sys:menu:remove")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (menuService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}

	@Log("menu")
	@GetMapping("/tree")
	@ResponseBody
	SysTree<SysMenu> tree() {
		SysTree<SysMenu> tree = new SysTree<SysMenu>();
		Long userid=null;
		if(!"admin".equals(getSysUser().getUsername())){
			userid=getSysUserId();
		}
		tree = menuService.getTree(userid);
		return tree;
	}
	@Log("menu")
	@GetMapping("/tree/{roleId}")
	@ResponseBody
	SysTree<SysMenu> tree(@PathVariable("roleId") Long roleId) {
		Long userid=null;
		if(!"admin".equals(getSysUser().getUsername())){
			userid=getSysUserId();
		}
		SysTree<SysMenu> tree = new SysTree<SysMenu>();
		tree = menuService.getTree(roleId,userid);
		return tree;
	}
}
