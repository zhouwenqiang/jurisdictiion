package com.jurisdiction.common.service;

import com.jurisdiction.common.entity.SysFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/7.
 */
public interface SysFileService {

    SysFile get(Long id);

    List<SysFile> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SysFile sysSysFile);

    int update(SysFile sysSysFile);

    int remove(Long id);

    int batchRemove(Long[] ids);

    /**
     * 判断一个文件是否存在
     * @param url FileDO中存的路径
     * @return
     */
    Boolean isExist(String url);
}
