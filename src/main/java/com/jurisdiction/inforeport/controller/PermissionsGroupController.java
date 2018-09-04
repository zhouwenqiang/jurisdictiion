package com.jurisdiction.inforeport.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jurisdiction.common.annotation.Log;
import com.jurisdiction.common.config.Constant;
import com.jurisdiction.common.controller.BaseController;
import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.common.utils.PageUtils;
import com.jurisdiction.common.utils.Query;
import com.jurisdiction.common.utils.R;
import com.jurisdiction.inforeport.entity.PermissionsGroup;
import com.jurisdiction.system.entity.SysMenu;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jurisdiction.inforeport.service.PermissionsGroupService;


/**
 * 数据权限组
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-15 14:33:08
 */
 
@Controller
@RequestMapping("/inforeport/permissionsGroup")
public class PermissionsGroupController extends BaseController {
	@Autowired
	private PermissionsGroupService permissionsGroupService;

	@Log("permissionsGroup")
	@GetMapping()
	@RequiresPermissions("inforeport:permissionsGroup:permissionsGroup")
	String PermissionsGroup(){
	    return "inforeport/permissionsGroup/permissionsGroup";
	}

	@Log("permissionsGroup")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("inforeport:permissionsGroup:permissionsGroup")
	public List<PermissionsGroup> list(@RequestParam Map<String, Object> params){
		Map<String, Object> query = new HashMap<>(16);
		query.put("sort","order_num");
		query.put("order","asc");
		if(!"admin".equals(getSysUsername())){
			query.put("userId",getSysUserId());
		}
		List<PermissionsGroup> permissionsGroupList = permissionsGroupService.list(query);
		return permissionsGroupList;
	}
	@Log("permissionsGroup")
	@GetMapping("/add/{pId}")
	@RequiresPermissions(value = {"inforeport:permissionsGroup:add","inforeport:permissionsGroup:addfollowing"},logical = Logical.OR)
	String add(@PathVariable("pId") Long pId, Model model){
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "顶级");
		} else {
			model.addAttribute("pName", permissionsGroupService.get(pId).getName());
		}
	    return "inforeport/permissionsGroup/add";
	}

	@Log("permissionsGroup")
	@GetMapping("/edit/{groupId}")
	@RequiresPermissions("inforeport:permissionsGroup:edit")
	String edit(@PathVariable("groupId") Long groupId,Model model){
		PermissionsGroup permissionsGroup = permissionsGroupService.get(groupId);
		model.addAttribute("permissionsGroup", permissionsGroup);
		if(Constant.DEPT_ROOT_ID.equals(permissionsGroup.getParentId())) {
			model.addAttribute("parentDeptName", "顶级");
		}else {
			PermissionsGroup parDept = permissionsGroupService.get(permissionsGroup.getParentId());
			model.addAttribute("parentDeptName", parDept.getName());
		}
	    return "inforeport/permissionsGroup/edit";
	}
	
	/**
	 * 保存
	 */
	@Log("permissionsGroup")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions(value = {"inforeport:permissionsGroup:add","inforeport:permissionsGroup:addfollowing"},logical = Logical.OR)
	public R save( PermissionsGroup permissionsGroup){
		permissionsGroup.setCreateDate( new Date());
		permissionsGroup.setDelFlag(1);
		permissionsGroup.setUpdateDate(new Date());
		if(permissionsGroupService.save(permissionsGroup)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@Log("permissionsGroup")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("inforeport:permissionsGroup:edit")
	public R update( PermissionsGroup permissionsGroup){
		permissionsGroup.setUpdateDate(new Date());
		permissionsGroupService.update(permissionsGroup);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Log("permissionsGroup")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("inforeport:permissionsGroup:remove")
	public R remove( Long groupId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", groupId);
		if(permissionsGroupService.count(map)>0) {
			return R.error(1, "包含下级部门,不允许修改");
		}
		if(permissionsGroupService.remove(groupId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@Log("permissionsGroup")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("inforeport:permissionsGroup:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] groupIds){
		permissionsGroupService.batchRemove(groupIds);
		return R.ok();
	}

	@Log("permissionsGroup")
	@GetMapping("/tree")
	@ResponseBody
	SysTree<PermissionsGroup> tree() {
		SysTree<PermissionsGroup> tree = new SysTree<PermissionsGroup>();
		Long userid=null;
		if(!"admin".equals(getSysUser().getUsername())){
			userid=getSysUserId();
		}
		tree = permissionsGroupService.getTree(userid);
		return tree;
	}
	@Log("permissionsGroup")
	@GetMapping("/tree/{userId}")
	@ResponseBody
	SysTree<PermissionsGroup> tree(@PathVariable("userId") Long userId) {
		Long sysUserId=null;
		if(!"admin".equals(getSysUser().getUsername())){
			sysUserId=getSysUserId();
		}
		SysTree<PermissionsGroup> tree = new SysTree<PermissionsGroup>();
		tree = permissionsGroupService.getTree(userId,sysUserId);
		return tree;
	}
	
}
