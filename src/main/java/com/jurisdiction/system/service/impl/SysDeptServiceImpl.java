package com.jurisdiction.system.service.impl;

import com.jurisdiction.common.entity.SysTree;
import com.jurisdiction.common.utils.BuildTree;
import com.jurisdiction.system.dao.SysDeptDao;
import com.jurisdiction.system.entity.SysDept;
import com.jurisdiction.system.entity.SysUser;
import com.jurisdiction.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SysDeptServiceImpl implements SysDeptService {
	@Autowired
	private SysDeptDao sysDeptMapper;

	@Override
	public SysDept get(Long deptId){
		return sysDeptMapper.get(deptId);
	}

	@Override
	public List<SysDept> list(Map<String, Object> map){
		return sysDeptMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return sysDeptMapper.count(map);
	}

	@Override
	public int save(SysDept sysDept){
		return sysDeptMapper.save(sysDept);
	}

	@Override
	public int update(SysDept sysDept){
		return sysDeptMapper.update(sysDept);
	}

	@Override
	public int remove(Long deptId){
		return sysDeptMapper.remove(deptId);
	}

	@Override
	public int batchRemove(Long[] deptIds){
		return sysDeptMapper.batchRemove(deptIds);
	}

	@Override
	public SysTree<SysDept> getTree(Map<String,Object> map) {
		String pid= "0";
		List<SysTree<SysDept>> sysTrees = new ArrayList<SysTree<SysDept>>();
		List<SysDept> sysDepts = sysDeptMapper.listTree(map);
		for (int i = 0; i < sysDepts.size(); i++) {
			SysDept sysDept =sysDepts.get(i);
			if(map.containsKey("deptId")){
                pid=sysDept.getParentId()+"";
                getNode(sysDept.getDeptId(),sysTrees);
            }
			SysTree<SysDept> sysTree = new SysTree<SysDept>();
			sysTree.setId(sysDept.getDeptId().toString());
			sysTree.setParentId(sysDept.getParentId().toString());
			sysTree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			sysTree.setState(state);
			sysTrees.add(sysTree);
		}

		// 默认顶级菜单为０，根据数据库实际情况调整
		SysTree<SysDept> t = BuildTree.build(sysTrees,pid);
		return t;
	}
	public void getNode(Long pid,List<SysTree<SysDept>> sysTrees){
		Map<String,Object> pamenMap= new HashMap<>();
		pamenMap.put("parentId",pid);
		List<SysDept> sysDepts = sysDeptMapper.list(pamenMap);
		if(sysDepts !=null && sysDepts.size()!=0){
			for(SysDept sysDept : sysDepts){
				//System.out.print(sysDept.getName());
				SysTree<SysDept> sysTree = new SysTree<SysDept>();
				sysTree.setId(sysDept.getDeptId().toString());
				sysTree.setParentId(sysDept.getParentId().toString());
				sysTree.setText(sysDept.getName());
				Map<String, Object> state = new HashMap<>(16);
				state.put("opened", true);
				sysTree.setState(state);
				sysTrees.add(sysTree);
				getNode(sysDept.getDeptId(),sysTrees); //根据当前id查询子
			}
		}
	}
	@Override
	public boolean checkDeptHasUser(Long deptId) {
		// TODO Auto-generated method stub
		//查询部门以及此部门的下级部门
		int result = sysDeptMapper.getDeptUserNumber(deptId);
		return result==0?true:false;
	}

}
