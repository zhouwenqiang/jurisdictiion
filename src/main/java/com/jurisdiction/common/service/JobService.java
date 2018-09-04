package com.jurisdiction.common.service;

import com.jurisdiction.common.entity.SysTask;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zwq
 */
public interface JobService {
	
	SysTask get(Long id);
	
	List<SysTask> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysTask taskScheduleJob);
	
	int update(SysTask taskScheduleJob);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	void initSchedule() throws SchedulerException;

	void changeStatus(Long jobId, String cmd) throws SchedulerException;

	void updateCron(Long jobId) throws SchedulerException;
}
