package com.elead.ppm.project.provider.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.elead.platform.common.service.dao.CrudDao;
import com.elead.ppm.project.domain.entity.ELTaskBaseInfo;

/**
 * 项目任务Dao接口
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface ELTaskBaseInfoDao extends CrudDao<ELTaskBaseInfo>{
	
	/**
	 * 根据需求id查询任务对象列表;
	 * 需求-任务(一对多关系)
	 * @param 需求id
	 * @return 任务对象集合
	 */
	public List<ELTaskBaseInfo> findTaskByRequirementId(Map<String, Object> map);
	
}
