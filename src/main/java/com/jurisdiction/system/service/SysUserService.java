package com.jurisdiction.system.service;

import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.system.entity.SysDept;
import com.jurisdiction.system.entity.SysUser;
import com.jurisdiction.system.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface SysUserService {
	SysUser get(Long id);

	List<SysUser> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(SysUser user);

	int update(SysUser user);

	int remove(Long userId);

	int batchremove(Long[] userIds);

	boolean exit(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(User user, SysUser sysUser) throws Exception;
	int adminResetPwd(User user) throws Exception;
	SysTree<SysDept> getTree();

	/**
	 * 更新个人信息
	 * @param sysUser
	 * @return
	 */
	int updatePersonal(SysUser sysUser);

	/**
	 * 更新个人图片
	 * @param file 图片
	 * @param avatar_data 裁剪信息
	 * @param userId 用户ID
	 * @throws Exception
	 */
    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;
}
