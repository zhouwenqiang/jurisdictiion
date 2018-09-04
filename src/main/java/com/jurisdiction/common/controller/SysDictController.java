package com.jurisdiction.common.controller;


import com.jurisdiction.common.annotation.Log;
import com.jurisdiction.common.config.Constant;
import com.jurisdiction.common.entity.SysDict;
import com.jurisdiction.common.service.SysDictService;
import com.jurisdiction.common.utils.PageUtils;
import com.jurisdiction.common.utils.Query;
import com.jurisdiction.common.utils.R;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典表
 * @author chglee
 */

@Controller
@RequestMapping("/common/dict")
public class SysDictController extends BaseController {
	@Autowired
	private SysDictService dictService;

	@Log("dict")
	@GetMapping()
	@RequiresPermissions("common:dict:dict")
	String dict() {
		return "common/dict/dict";
	}

	@Log("dict")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:dict:dict")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<SysDict> dictList = dictService.list(query);
		int total = dictService.count(query);
		PageUtils pageUtils = new PageUtils(dictList, total);
		return pageUtils;
	}

	@Log("dict")
	@GetMapping("/add")
	@RequiresPermissions(value = {"common:dict:add","common:dict:addfollowing"},logical = Logical.OR)
	String add() {
		return "common/dict/add";
	}


	@Log("dict")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:dict:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		SysDict dict = dictService.get(id);
		model.addAttribute("dict", dict);
		return "common/dict/edit";
	}

	/**
	 * 保存
	 */
	@Log("dict")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions(value = {"common:dict:add","common:dict:addfollowing"},logical = Logical.OR)
	public R save(SysDict dict) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (dictService.save(dict) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@Log("dict")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:dict:edit")
	public R update(SysDict dict) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		dictService.update(dict);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@Log("dict")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("common:dict:remove")
	public R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (dictService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("dict")
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:dict:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		dictService.batchRemove(ids);
		return R.ok();
	}

	@Log("dict")
	@GetMapping("/type")
	@ResponseBody
	public List<SysDict> listType() {
		return dictService.listType();
	};

	@Log("dict")
	// 类别已经指定增加
	@GetMapping("/add/{type}/{description}")
	@RequiresPermissions(value = {"common:dict:add","common:dict:addfollowing"},logical = Logical.OR)
	String addD(Model model, @PathVariable("type") String type, @PathVariable("description") String description) {
		model.addAttribute("type", type);
		model.addAttribute("description", description);
		return "common/dict/add";
	}

	@Log("dict")
	@ResponseBody
	@GetMapping("/list/{type}")
	public List<SysDict> listByType(@PathVariable("type") String type) {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", type);
		List<SysDict> dictList = dictService.list(map);
		return dictList;
	}
}
