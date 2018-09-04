package com.jurisdiction.common.dao;

import com.jurisdiction.common.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * @author zwq
 */
@Mapper
public interface SysLogDao {

	SysLog get(Long id);
	
	List<SysLog> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysLog log);
	
	int update(SysLog log);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
