package com.jurisdiction.oa.dao;

import com.jurisdiction.oa.domain.NotifyDO;
import com.jurisdiction.oa.domain.NotifyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 通知通告
 * 
 * @author chglee
 * @email
 * @date 2017-10-05 17:11:16
 */
@Mapper
public interface NotifyDao {

	NotifyDO get(Long id);

	List<NotifyDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(NotifyDO notify);

	int update(NotifyDO notify);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<NotifyDO> listByIds(Long[] ids);

	int countDTO(Map<String, Object> map);

	List<NotifyDTO> listDTO(Map<String, Object> map);
}
