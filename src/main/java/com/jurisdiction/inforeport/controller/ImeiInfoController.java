package com.jurisdiction.inforeport.controller;

import java.util.HashMap;
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

import com.jurisdiction.inforeport.entity.ImeiInfo;
import com.jurisdiction.inforeport.service.ImeiInfoService;
import com.jurisdiction.common.utils.PageUtils;
import com.jurisdiction.common.utils.Query;
import com.jurisdiction.common.utils.R;

/**
 * 从能力平台获取的IMEI表
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-17 11:33:36
 */
 
@Controller
@RequestMapping("/inforeport/imeiInfo")
public class ImeiInfoController extends BaseController {
	@Autowired
	private ImeiInfoService imeiInfoService;

	@Log("imeiInfo")
	@GetMapping()
	@RequiresPermissions("inforeport:imeiInfo:imeiInfo")
	String ImeiInfo(){
	    return "inforeport/imeiInfo/imeiInfo";
	}

	@Log("imeiInfo")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("inforeport:imeiInfo:imeiInfo")
	public List<ImeiInfo> list(@RequestParam Map<String, Object> params){
		if(!"admin".equals(getSysUsername()) ){
			params.put("user_id",getSysUserId());
		}
		List<ImeiInfo> imeiInfoList = imeiInfoService.list(params);
		return imeiInfoList;
	}

	@Log("imeiInfo")
	@GetMapping("/add")
	@RequiresPermissions("inforeport:imeiInfo:add")
	String add(){
	    return "inforeport/imeiInfo/add";
	}

	@Log("imeiInfo")
	@GetMapping("/edit/{imeiId}")
	@RequiresPermissions("inforeport:imeiInfo:edit")
	String edit(@PathVariable("imeiId") Integer imeiId,Model model){
		ImeiInfo imeiInfo = imeiInfoService.get(imeiId);
		model.addAttribute("imeiInfo", imeiInfo);
	    return "inforeport/imeiInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@Log("imeiInfo")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("inforeport:imeiInfo:add")
	public R save( ImeiInfo imeiInfo){
		if(imeiInfoService.save(imeiInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@Log("imeiInfo")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("inforeport:imeiInfo:edit")
	public R update( ImeiInfo imeiInfo){
		imeiInfoService.update(imeiInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Log("imeiInfo")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("inforeport:imeiInfo:remove")
	public R remove( Integer imeiId){
		if(imeiInfoService.remove(imeiId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@Log("imeiInfo")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("inforeport:imeiInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] imeiIds){
		imeiInfoService.batchRemove(imeiIds);
		return R.ok();
	}
	
}
