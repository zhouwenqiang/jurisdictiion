package com.jurisdiction.common.service.impl;

import com.jurisdiction.common.dao.SysDictDao;
import com.jurisdiction.common.entity.SysDict;
import com.jurisdiction.common.service.SysDictService;
import com.jurisdiction.common.utils.StringUtils;
import com.jurisdiction.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class SysDictServiceImpl implements SysDictService {
    @Autowired
    private SysDictDao sysDictDao;

    @Override
    public SysDict get(Long id) {
        return sysDictDao.get(id);
    }

    @Override
    public List<SysDict> list(Map<String, Object> map) {
        return sysDictDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return sysDictDao.count(map);
    }

    @Override
    public int save(SysDict dict) {
        return sysDictDao.save(dict);
    }

    @Override
    public int update(SysDict dict) {
        return sysDictDao.update(dict);
    }

    @Override
    public int remove(Long id) {
        return sysDictDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return sysDictDao.batchRemove(ids);
    }

    @Override

    public List<SysDict> listType() {
        return sysDictDao.listType();
    }

    @Override
    public String getName(String type, String value) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("type", type);
        param.put("value", value);
        String rString = sysDictDao.list(param).get(0).getName();
        return rString;
    }

    @Override
    public List<SysDict> getHobbyList(SysUser sysUser) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "hobby");
        List<SysDict> hobbyList = sysDictDao.list(param);

        if (StringUtils.isNotEmpty(sysUser.getHobby())) {
            String userHobbys[] = sysUser.getHobby().split(";");
            for (String userHobby : userHobbys) {
                for (SysDict hobby : hobbyList) {
                    if (!Objects.equals(userHobby, hobby.getId().toString())) {
                        continue;
                    }
                    hobby.setRemarks("true");
                    break;
                }
            }
        }

        return hobbyList;
    }

    @Override
    public List<SysDict> getSexList() {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "sex");
        return sysDictDao.list(param);
    }

    @Override
    public List<SysDict> listByType(String type) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", type);
        return sysDictDao.list(param);
    }

}
