package com.jurisdiction.common.service.impl;

import com.jurisdiction.common.dao.SysLogDao;
import com.jurisdiction.common.entity.SysLog;
import com.jurisdiction.common.entity.SysPage;
import com.jurisdiction.common.service.SysLogService;
import com.jurisdiction.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	SysLogDao logMapper;

	@Async
	@Override
	public void save(SysLog logDO) {
		 logMapper.save(logDO);
	}

	@Override
	public SysPage<SysLog> queryList(Query query) {
		int total = logMapper.count(query);
		List<SysLog> logs = logMapper.list(query);
		SysPage<SysLog> page = new SysPage<>();
		page.setTotal(total);
		page.setRows(logs);
		return page;
	}

	@Override
	public int remove(Long id) {
		int count = logMapper.remove(id);
		return count;
	}

	@Override
	public int batchRemove(Long[] ids){
		return logMapper.batchRemove(ids);
	}
}
