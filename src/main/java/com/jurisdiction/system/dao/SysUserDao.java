package com.jurisdiction.system.dao;

import com.jurisdiction.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface SysUserDao {

	SysUser get(Long userId);
	
	List<SysUser> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysUser user);
	
	int update(SysUser user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();

}
