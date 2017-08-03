package com.elead.ppm.project.provider.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.elead.platform.common.service.dao.CrudDao;
import com.elead.ppm.project.domain.entity.ELPlanStageBaseInfo;

/**
 * 计划类型表Dao
 * @author 封阳
 *
 */
@Mapper
@Repository
public interface ELPlanStageBaseInfoDao extends CrudDao<ELPlanStageBaseInfo> {
	
}
