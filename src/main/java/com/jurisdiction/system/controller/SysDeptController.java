package com.jurisdiction.system.controller;


import com.jurisdiction.common.annotation.Log;
import com.jurisdiction.common.config.Constant;
import com.jurisdiction.common.controller.BaseController;
import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.common.utils.R;
import com.jurisdiction.system.entity.SysDept;
import com.jurisdiction.system.service.SysDeptService;
import io.swagger.annotations.ApiOperation;
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
 * dept管理
 */

@Controller
@RequestMapping("/system/sysDept")
public class SysDeptController extends BaseController {
	private String prefix = "system/dept";
	@Autowired
	private SysDeptService sysDeptService;

	@GetMapping()
	@Log("dept")
	@RequiresPermissions("system:sysDept:sysDept")
	String dept() {
		return prefix + "/dept";
	}

	@ApiOperation(value="获取部门列表", notes="")
	@ResponseBody
	@Log("dept")
	@GetMapping("/list")
	@RequiresPermissions("system:sysDept:sysDept")
	public List<SysDept> list() {
		Map<String, Object> query = new HashMap<>(16);
		if(!"admin".equals(getSysUsername())){
			query.put("deptId",getSysUser().getDeptId());
		}
		List<SysDept> sysDeptList = sysDeptService.list(query);
		return sysDeptList;
	}

	@Log("dept")
	@GetMapping("/add/{pId}")
	@RequiresPermissions(value = {"system:sysDept:add","system:sysDept:addfollowing"},logical = Logical.OR)
	String add(@PathVariable("pId") Long pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "总dept");
		} else {
			model.addAttribute("pName", sysDeptService.get(pId).getName());
		}
		return  prefix + "/add";
	}

	@Log("dept")
	@GetMapping("/edit/{deptId}")
	@RequiresPermissions("system:sysDept:edit")
	String edit(@PathVariable("deptId") Long deptId, Model model) {
		SysDept sysDept = sysDeptService.get(deptId);
		model.addAttribute("sysDept", sysDept);
		if(Constant.DEPT_ROOT_ID.equals(sysDept.getParentId())) {
			model.addAttribute("parentDeptName", "无");
		}else {
			SysDept parDept = sysDeptService.get(sysDept.getParentId());
			model.addAttribute("parentDeptName", parDept.getName());
		}
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@Log("dept")
	@RequiresPermissions(value = {"system:sysDept:add","system:sysDept:addfollowing"},logical = Logical.OR)
	public R save(SysDept sysDept) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (sysDeptService.save(sysDept) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@Log("dept")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:sysDept:edit")
	public R update(SysDept sysDept) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (sysDeptService.update(sysDept) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("dept")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:remove")
	public R remove(Long deptId) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", deptId);
		if(sysDeptService.count(map)>0) {
			return R.error(1, "包含下级dept,不允许修改");
		}
		if(sysDeptService.checkDeptHasUser(deptId)) {
			if (sysDeptService.remove(deptId) > 0) {
				return R.ok();
			}
		}else {
			return R.error(1, "dept包含用户,不允许修改");
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("dept")
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] deptIds) {
		if (Constant.DEMO_ACCOUNT.equals(getSysUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		sysDeptService.batchRemove(deptIds);
		return R.ok();
	}
	@Log("dept")
	@GetMapping("/tree")
	@ResponseBody
	public SysTree<SysDept> tree() {
		Map<String,Object> pamenMap= new HashMap<>();
		if(!"admin".equals(getSysUsername())){
			pamenMap.put("deptId",getSysUser().getDeptId());
		}
		pamenMap.put("delFlag",1);
		SysTree<SysDept> tree = new SysTree<SysDept>();
		tree = sysDeptService.getTree(pamenMap);
		return tree;
	}
	@Log("dept")
	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/deptTree";
	}

}
