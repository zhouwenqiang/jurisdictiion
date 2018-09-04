package com.jurisdiction.common.dao;

import com.jurisdiction.common.entity.SysTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zwq
 */
@Mapper
public interface SysTaskDao {

	SysTask get(Long id);
	
	List<SysTask> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysTask sysTask);
	
	int update(SysTask sysTask);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
