package com.jurisdiction.common.service.impl;

import com.jurisdiction.common.config.BootdoConfig;
import com.jurisdiction.common.dao.SysFileDao;
import com.jurisdiction.common.entity.SysFile;
import com.jurisdiction.common.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


@Service
public class SysSysFileServiceImpl implements SysFileService {
	@Autowired
	private SysFileDao sysFileMapper;

	@Autowired
	private BootdoConfig bootdoConfig;
	@Override
	public SysFile get(Long id){
		return sysFileMapper.get(id);
	}
	
	@Override
	public List<SysFile> list(Map<String, Object> map){
		return sysFileMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sysFileMapper.count(map);
	}
	
	@Override
	public int save(SysFile sysSysFile){
		return sysFileMapper.save(sysSysFile);
	}
	
	@Override
	public int update(SysFile sysSysFile){
		return sysFileMapper.update(sysSysFile);
	}
	
	@Override
	public int remove(Long id){
		return sysFileMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return sysFileMapper.batchRemove(ids);
	}

    @Override
    public Boolean isExist(String url) {
		Boolean isExist = false;
		if (!StringUtils.isEmpty(url)) {
			String filePath = url.replace("/files/", "");
			filePath = bootdoConfig.getUploadPath() + filePath;
			java.io.File file = new java.io.File(filePath);
			if (file.exists()) {
				isExist = true;
			}
		}
		return isExist;
	}
	}
