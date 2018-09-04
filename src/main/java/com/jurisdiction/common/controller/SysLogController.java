package com.jurisdiction.common.controller;

import com.jurisdiction.common.annotation.Log;
import com.jurisdiction.common.entity.SysLog;
import com.jurisdiction.common.entity.SysPage;
import com.jurisdiction.common.service.SysLogService;
import com.jurisdiction.common.utils.Query;
import com.jurisdiction.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/common/log")
@Controller
public class SysLogController {
	@Autowired
	SysLogService logService;
	String prefix = "common/log";

	@Log("log")
	@GetMapping()
	String log() {
		return prefix + "/log";
	}

	@Log("log")
	@ResponseBody
	@GetMapping("/list")
	SysPage<SysLog> list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		SysPage<SysLog> page = logService.queryList(query);
		return page;
	}

	@Log("log")
	@ResponseBody
	@PostMapping("/remove")
	R remove(Long id) {
		if (logService.remove(id)>0) {
			return R.ok();
		}
		return R.error();
	}

	@Log("log")
	@ResponseBody
	@PostMapping("/batchRemove")
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		int r = logService.batchRemove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
}
