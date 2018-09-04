package com.jurisdiction.inforeport.controller;

import java.util.List;
import java.util.Map;

import com.jurisdiction.common.annotation.Log;
import com.jurisdiction.common.controller.BaseController;
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

import com.jurisdiction.inforeport.entity.Datapermissionsgroup;
import com.jurisdiction.inforeport.service.DatapermissionsgroupService;
import com.jurisdiction.common.utils.PageUtils;
import com.jurisdiction.common.utils.Query;
import com.jurisdiction.common.utils.R;

/**
 * 用户与数据权限组
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-17 11:22:43
 */
 
@Controller
@RequestMapping("/inforeport/datapermissionsgroup")
public class DatapermissionsgroupController extends BaseController {
	@Autowired
	private DatapermissionsgroupService datapermissionsgroupService;

	@Log("userdatapermissionsgroup")
	@GetMapping()
	@RequiresPermissions("inforeport:datapermissionsgroup:datapermissionsgroup")
	String Datapermissionsgroup(){
	    return "inforeport/datapermissionsgroup/datapermissionsgroup";
	}

	@Log("userdatapermissionsgroup")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("inforeport:datapermissionsgroup:datapermissionsgroup")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<Datapermissionsgroup> datapermissionsgroupList = datapermissionsgroupService.list(query);
		int total = datapermissionsgroupService.count(query);
		PageUtils pageUtils = new PageUtils(datapermissionsgroupList, total);
		return pageUtils;
	}
	@Log("userdatapermissionsgroup")
	@GetMapping("/add")
	@RequiresPermissions("inforeport:datapermissionsgroup:add")
	String add(){
	    return "inforeport/datapermissionsgroup/add";
	}

	@Log("userdatapermissionsgroup")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("inforeport:datapermissionsgroup:edit")
	String edit(@PathVariable("id") Long id,Model model){
		Datapermissionsgroup datapermissionsgroup = datapermissionsgroupService.get(id);
		model.addAttribute("datapermissionsgroup", datapermissionsgroup);
	    return "inforeport/datapermissionsgroup/edit";
	}
	
	/**
	 * 保存
	 */
	@Log("userdatapermissionsgroup")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("inforeport:datapermissionsgroup:add")
	public R save( Datapermissionsgroup datapermissionsgroup){
		if(datapermissionsgroupService.save(datapermissionsgroup)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@Log("userdatapermissionsgroup")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("inforeport:datapermissionsgroup:edit")
	public R update( Datapermissionsgroup datapermissionsgroup){
		datapermissionsgroupService.update(datapermissionsgroup);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Log("userdatapermissionsgroup")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("inforeport:datapermissionsgroup:remove")
	public R remove( Long id){
		if(datapermissionsgroupService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@Log("userdatapermissionsgroup")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("inforeport:datapermissionsgroup:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		datapermissionsgroupService.batchRemove(ids);
		return R.ok();
	}
	
}
