package com.jurisdiction.inforeport.service;

import com.jurisdiction.inforeport.entity.ImeiInfo;

import java.util.List;
import java.util.Map;

/**
 * 从能力平台获取的IMEI表
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-17 11:33:36
 */
public interface ImeiInfoService {
	
	ImeiInfo get(Integer imeiId);
	
	List<ImeiInfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ImeiInfo imeiInfo);
	
	int update(ImeiInfo imeiInfo);
	
	int remove(Integer imeiId);
	
	int batchRemove(Integer[] imeiIds);
}
