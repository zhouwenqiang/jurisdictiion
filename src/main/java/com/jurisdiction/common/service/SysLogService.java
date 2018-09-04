package com.jurisdiction.common.service;

import com.jurisdiction.common.entity.SysLog;
import com.jurisdiction.common.entity.SysPage;
import com.jurisdiction.common.utils.Query;
import org.springframework.stereotype.Service;

@Service
public interface SysLogService {
	void save(SysLog sysLog);
	SysPage<SysLog> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
