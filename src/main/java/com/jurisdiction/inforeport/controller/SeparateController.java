package com.jurisdiction.inforeport.controller;

import java.util.List;
import java.util.Map;

import com.jurisdiction.common.controller.BaseController;
import com.jurisdiction.inforeport.entity.CitySeparate;
import com.jurisdiction.inforeport.entity.DetailsSeparate;
import com.jurisdiction.inforeport.entity.RegionSeparate;
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

import com.jurisdiction.inforeport.entity.Separate;
import com.jurisdiction.inforeport.service.SeparateService;
import com.jurisdiction.common.utils.PageUtils;
import com.jurisdiction.common.utils.Query;
import com.jurisdiction.common.utils.R;

/**
 * 
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-28 15:35:49
 */
 
@Controller
@RequestMapping("/inforeport/separate")
public class SeparateController extends BaseController {
	@Autowired
	private SeparateService separateService;
	
	@GetMapping()
	@RequiresPermissions("inforeport:separate:tabseparate")
	String Separate(){
	    return "inforeport/separate/tabseparate";
	}
	
	@ResponseBody
	@GetMapping("/listcity")
	@RequiresPermissions("inforeport:separate:tabseparate")
	public List<CitySeparate>  listCity(@RequestParam Map<String, Object> params){
		//查询列表数据
		if(!"admin".equals(getSysUsername()) ){
			params.put("user_id",getSysUserId());
		}
		List<CitySeparate> citySeparatesList = separateService.listCity(params);
		return citySeparatesList;
	}
	@ResponseBody
	@GetMapping("/listregion")
	@RequiresPermissions("inforeport:separate:tabseparate")
	public List<RegionSeparate>  listRegion(@RequestParam Map<String, Object> params){
		//查询列表数据
		if(!"admin".equals(getSysUsername()) ){
			params.put("user_id",getSysUserId());
		}
		List<RegionSeparate> listRegion = separateService.listRegion(params);
		return listRegion;
	}
	@ResponseBody
	@GetMapping("/listdetails")
	@RequiresPermissions("inforeport:separate:tabseparate")
	public List<DetailsSeparate>  listDetails(@RequestParam Map<String, Object> params){
		//查询列表数据
		if(!"admin".equals(getSysUsername()) ){
			params.put("user_id",getSysUserId());
		}
		List<DetailsSeparate> listRegion = separateService.listDetails(params);
		return listRegion;
	}

	@GetMapping("/add")
	@RequiresPermissions("inforeport:separate:add")
	String add(){
	    return "inforeport/separate/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("inforeport:separate:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		Separate separate = separateService.get(id);
		model.addAttribute("separate", separate);
	    return "inforeport/separate/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("inforeport:separate:add")
	public R save( Separate separate){
		if(separateService.save(separate)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("inforeport:separate:edit")
	public R update( Separate separate){
		separateService.update(separate);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("inforeport:separate:remove")
	public R remove( Integer id){
		if(separateService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("inforeport:separate:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		separateService.batchRemove(ids);
		return R.ok();
	}

	/**
	*根据ID取得下级目录
	 * */
	@PostMapping( "/groupSelectData")
	@ResponseBody
	public List groupSelectData(Integer pid){
		List list=separateService.groupSelectData(pid);
		return list;
	}

}
