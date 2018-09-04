package com.jurisdiction.inforeport.controller;

import java.util.List;
import java.util.Map;

import com.jurisdiction.common.controller.BaseController;
import com.jurisdiction.inforeport.entity.ActivityMarketing;
import com.jurisdiction.inforeport.entity.CityMarketing;
import com.jurisdiction.inforeport.entity.TacMarketing;
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

import com.jurisdiction.inforeport.entity.Marketing;
import com.jurisdiction.inforeport.service.MarketingService;
import com.jurisdiction.common.utils.PageUtils;
import com.jurisdiction.common.utils.Query;
import com.jurisdiction.common.utils.R;

/**
 * 
 * 
 * @author zwq
 * @email qq@qq.com
 * @date 2018-06-06 13:59:16
 */
 
@Controller
@RequestMapping("/inforeport/marketing")
public class MarketingController extends BaseController {
	@Autowired
	private MarketingService marketingService;
	
	@GetMapping()
	@RequiresPermissions("inforeport:marketing:tabmarketing")
	String Marketing(){
	    return "inforeport/marketing/tabmarketing";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("inforeport:marketing:marketing")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<Marketing> marketingList = marketingService.list(query);
		int total = marketingService.count(query);
		PageUtils pageUtils = new PageUtils(marketingList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listcity")
	@RequiresPermissions("inforeport:marketing:tabmarketing")
	public List<CityMarketing>  listCity(@RequestParam Map<String, Object> params){
		//查询列表数据
		if(!"admin".equals(getSysUsername()) ){
			params.put("user_id",getSysUserId());
		}
		List<CityMarketing> listCity = marketingService.listCity(params);
		return listCity;
	}
	@ResponseBody
	@GetMapping("/listtac")
	@RequiresPermissions("inforeport:marketing:tabmarketing")
	public List<TacMarketing>  listTac(@RequestParam Map<String, Object> params){
		//查询列表数据
		if(!"admin".equals(getSysUsername()) ){
			params.put("user_id",getSysUserId());
		}
		List<TacMarketing> listTac = marketingService.listTac(params);
		return listTac;
	}

	@ResponseBody
	@GetMapping("/listactivity")
	@RequiresPermissions("inforeport:marketing:tabmarketing")
	public List<ActivityMarketing>  listActivity(@RequestParam Map<String, Object> params){
		//查询列表数据
		if(!"admin".equals(getSysUsername()) ){
			params.put("user_id",getSysUserId());
		}
		List<ActivityMarketing> listActivity = marketingService.listActivity(params);
		return listActivity;
	}
	@GetMapping("/add")
	@RequiresPermissions("inforeport:marketing:add")
	String add(){
	    return "inforeport/marketing/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("inforeport:marketing:edit")
	String edit(@PathVariable("id") Long id,Model model){
		Marketing marketing = marketingService.get(id);
		model.addAttribute("marketing", marketing);
	    return "inforeport/marketing/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("inforeport:marketing:add")
	public R save( Marketing marketing){
		if(marketingService.save(marketing)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("inforeport:marketing:edit")
	public R update( Marketing marketing){
		marketingService.update(marketing);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("inforeport:marketing:remove")
	public R remove( Long id){
		if(marketingService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("inforeport:marketing:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		marketingService.batchRemove(ids);
		return R.ok();
	}
	
}
