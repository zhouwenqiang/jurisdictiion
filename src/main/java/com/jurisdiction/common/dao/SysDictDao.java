package com.jurisdiction.common.dao;

import com.jurisdiction.common.entity.SysDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author zwq
 */
@Mapper
public interface SysDictDao {

	SysDict get(Long id);

	List<SysDict> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(SysDict sysDict);

	int update(SysDict sysDict);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<SysDict> listType();
}
