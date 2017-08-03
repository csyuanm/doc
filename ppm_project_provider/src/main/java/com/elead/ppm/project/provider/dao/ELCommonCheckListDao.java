package com.elead.ppm.project.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.elead.platform.common.service.dao.CrudDao;
import com.elead.ppm.project.domain.entity.ELCommonCheckList;

/**
 * 检查项Dao接口
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface ELCommonCheckListDao extends CrudDao<ELCommonCheckList>{
	
	/**
	 * 根据需求id查询其对应的检查项;
	 * 需求-检查项(一对多)
	 * @param 需求id
	 * @return 检查项对象集合
	 */
	List<ELCommonCheckList> findCheckByRequirementId(String requirementId);
	
}
