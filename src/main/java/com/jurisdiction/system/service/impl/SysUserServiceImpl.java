package com.jurisdiction.system.service.impl;


import com.jurisdiction.common.config.BootdoConfig;
import com.jurisdiction.common.entity.SysFile;
import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.common.service.SysFileService;
import com.jurisdiction.common.utils.*;
import com.jurisdiction.inforeport.dao.DatapermissionsgroupDao;
import com.jurisdiction.inforeport.entity.Datapermissionsgroup;
import com.jurisdiction.system.dao.SysDeptDao;
import com.jurisdiction.system.dao.SysUserDao;
import com.jurisdiction.system.dao.SysUserRoleDao;
import com.jurisdiction.system.entity.SysDept;
import com.jurisdiction.system.entity.SysUser;
import com.jurisdiction.system.entity.SysUserRole;
import com.jurisdiction.system.entity.User;
import com.jurisdiction.system.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

@Transactional
@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	SysUserDao userMapper;
	@Autowired
	SysUserRoleDao userRoleMapper;
	@Autowired
	SysDeptDao deptMapper;
	@Autowired
	private SysFileService sysSysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private DatapermissionsgroupDao datapermissionsgroupDao;

	private static final Logger logger = LoggerFactory.getLogger(SysUserService.class);

	@Override
	public SysUser get(Long id) {
		List<Long> roleIds = userRoleMapper.listRoleId(id);
		SysUser user = userMapper.get(id);
		user.setDeptName(deptMapper.get(user.getDeptId()).getName());
		user.setRoleIds(roleIds);
		return user;
	}

	@Override
	public List<SysUser> list(Map<String, Object> map) {
		return userMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return userMapper.count(map);
	}

	@Transactional
	@Override
	public int save(SysUser user) {
		int count = userMapper.save(user);
		Long userId = user.getUserId();
		List<Long> roles = user.getRoleIds();
		userRoleMapper.removeByUserId(userId);
		List<SysUserRole> list = new ArrayList<>();
		for (Long roleId : roles) {
			SysUserRole ur = new SysUserRole();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}
		datapermissionsgroupDao.removeByUserId(userId);
		String ids[]=null;
		if(null!=user.getPermissionsGroupIds()){
			 ids=user.getPermissionsGroupIds().split("_");
			List<Datapermissionsgroup> dpglist= new ArrayList<>();
			for (String groupId:ids[0].split(",")){
				Datapermissionsgroup dpg= new Datapermissionsgroup();
				dpg.setCreateDate(new Date());
				dpg.setUserId(userId);
				dpg.setGroupId(Long.valueOf(groupId));
				dpg.setSelectedState(0);
				dpglist.add(dpg);
			}
			if(ids.length>1){
				for (String groupId:ids[1].split(",")){
					Datapermissionsgroup dpg= new Datapermissionsgroup();
					dpg.setCreateDate(new Date());
					dpg.setUserId(userId);
					dpg.setGroupId(Long.valueOf(groupId));
					dpg.setSelectedState(1);
					dpglist.add(dpg);
				}
			}
            if (dpglist.size()>0){
                datapermissionsgroupDao.batchSave(dpglist);
            }
		}


		return count;
	}

	@Override
	public int update(SysUser user) {
		int r = userMapper.update(user);
		Long userId = user.getUserId();
		List<Long> roles = user.getRoleIds();
		userRoleMapper.removeByUserId(userId);
		List<SysUserRole> list = new ArrayList<>();
		for (Long roleId : roles) {
			SysUserRole ur = new SysUserRole();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}
		datapermissionsgroupDao.removeByUserId(userId);
		String ids[]=null;
		if(null!=user.getPermissionsGroupIds()){
			ids=user.getPermissionsGroupIds().split("_");
			List<Datapermissionsgroup> dpglist= new ArrayList<>();
			for (String groupId:ids[0].split(",")){
				Datapermissionsgroup dpg= new Datapermissionsgroup();
				dpg.setCreateDate(new Date());
				dpg.setUserId(userId);
				dpg.setGroupId(Long.valueOf(groupId));
				dpg.setSelectedState(0);
				dpglist.add(dpg);
			}
			if(ids.length>1) {
                for (String groupId : ids[1].split(",")) {
                    Datapermissionsgroup dpg = new Datapermissionsgroup();
                    dpg.setCreateDate(new Date());
                    dpg.setUserId(userId);
                    dpg.setGroupId(Long.valueOf(groupId));
                    dpg.setSelectedState(1);
                    dpglist.add(dpg);
                }
            }
            if (dpglist.size() > 0) {
                datapermissionsgroupDao.batchSave(dpglist);
            }
		}
		return r;
	}
	@Transactional
	@Override
	public int remove(Long userId) {
		datapermissionsgroupDao.removeByUserId(userId);
		userRoleMapper.removeByUserId(userId);
		return userMapper.remove(userId);
	}

	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = userMapper.list(params).size() > 0;
		return exit;
	}

	@Override
	public Set<String> listRoles(Long userId) {
		return null;
	}

	@Override
	public int resetPwd(User userVO,SysUser userDO) throws Exception {
		if(Objects.equals(userVO.getSysUser().getUserId(),userDO.getUserId())){
			if(Objects.equals(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdOld()),userDO.getPassword())){
				userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(),userVO.getPwdNew()));
				return userMapper.update(userDO);
			}else{
				throw new Exception("输入的旧密码有误！");
			}
		}else{
			throw new Exception("你修改的不是你登录的账号！");
		}
	}
	@Override
	public int adminResetPwd(User userVO) throws Exception {
		SysUser userDO =get(userVO.getSysUser().getUserId());
		if("admin".equals(userDO.getUsername())){
			throw new Exception("超级管理员的账号不允许直接重置！");
		}
		userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
		return userMapper.update(userDO);


	}

	@Transactional
	@Override
	public int batchremove(Long[] userIds) {
		int count = userMapper.batchRemove(userIds);
		userRoleMapper.batchRemoveByUserId(userIds);
		return count;
	}

	@Override
	public SysTree<SysDept> getTree() {
		List<SysTree<SysDept>> sysTrees = new ArrayList<SysTree<SysDept>>();
		List<SysDept> depts = deptMapper.list(new HashMap<String, Object>(16));
		Long[] pDepts = deptMapper.listParentDept();
		Long[] uDepts = userMapper.listAllDept();
		Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
		for (SysDept dept : depts) {
			if (!ArrayUtils.contains(allDepts, dept.getDeptId())) {
				continue;
			}
			SysTree<SysDept> sysTree = new SysTree<SysDept>();
			sysTree.setId(dept.getDeptId().toString());
			sysTree.setParentId(dept.getParentId().toString());
			sysTree.setText(dept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			state.put("mType", "dept");
			sysTree.setState(state);
			sysTrees.add(sysTree);
		}
		List<SysUser> users = userMapper.list(new HashMap<String, Object>(16));
		for (SysUser user : users) {
			SysTree<SysDept> sysTree = new SysTree<SysDept>();
			sysTree.setId(user.getUserId().toString());
			sysTree.setParentId(user.getDeptId().toString());
			sysTree.setText(user.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			state.put("mType", "user");
			sysTree.setState(state);
			sysTrees.add(sysTree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		SysTree<SysDept> t = BuildTree.build(sysTrees);
		return t;
	}

	@Override
	public int updatePersonal(SysUser userDO) {
		return userMapper.update(userDO);
	}

    @Override
    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		SysFile sysSysFile = new SysFile(FileType.fileType(fileName), "/files/" + fileName, new Date());
		//获取图片后缀
		String prefix = fileName.substring((fileName.lastIndexOf(".")+1));
		String[] str=avatar_data.split(",");
		//获取截取的x坐标
		int x = (int)Math.floor(Double.parseDouble(str[0].split(":")[1]));
		//获取截取的y坐标
		int y = (int)Math.floor(Double.parseDouble(str[1].split(":")[1]));
		//获取截取的高度
		int h = (int)Math.floor(Double.parseDouble(str[2].split(":")[1]));
		//获取截取的宽度
		int w = (int)Math.floor(Double.parseDouble(str[3].split(":")[1]));
		//获取旋转的角度
		int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
		try {
			BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
			BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			boolean flag = ImageIO.write(rotateImage, prefix, out);
			//转换后存入数据库
			byte[] b = out.toByteArray();
			FileUtil.uploadFile(b, bootdoConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			throw  new Exception("图片裁剪错误！！");
		}
		Map<String, Object> result = new HashMap<>();
		if(sysSysFileService.save(sysSysFile)>0){
			SysUser userDO = new SysUser();
			userDO.setUserId(userId);
			userDO.setPicId(sysSysFile.getId());
			if(userMapper.update(userDO)>0){
				result.put("url", sysSysFile.getUrl());
			}
		}
		return result;
    }

}
