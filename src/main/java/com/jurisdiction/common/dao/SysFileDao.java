package com.jurisdiction.common.dao;

import com.jurisdiction.common.entity.SysFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * @author zwq
 */
@Mapper
public interface SysFileDao {

	SysFile get(Long id);
	
	List<SysFile> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysFile sysFile);
	
	int update(SysFile sysFile);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
