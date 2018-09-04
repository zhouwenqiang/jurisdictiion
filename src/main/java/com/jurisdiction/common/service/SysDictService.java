package com.jurisdiction.common.service;

import com.jurisdiction.common.entity.SysDict;
import com.jurisdiction.system.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author zwq
 */
public interface SysDictService {
	
	SysDict get(Long id);
	
	List<SysDict> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysDict dict);
	
	int update(SysDict dict);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<SysDict> listType();
	
	String getName(String type, String value);

	/**
	 * 获取爱好列表
	 * @return
     * @param userDO
	 */
	List<SysDict> getHobbyList(SysUser userDO);

	/**
	 * 获取性别列表
 	 * @return
	 */
	List<SysDict> getSexList();

	/**
	 * 根据type获取数据
	 * @param map
	 * @return
	 */
	List<SysDict> listByType(String type);

}
