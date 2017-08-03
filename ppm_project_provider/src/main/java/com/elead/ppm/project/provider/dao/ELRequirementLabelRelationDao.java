package com.elead.ppm.project.provider.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.elead.platform.common.service.dao.CrudDao;
import com.elead.ppm.project.domain.entity.ELRequirementLabelRelation;

/**
 * 标签Dao接口
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface ELRequirementLabelRelationDao extends CrudDao<ELRequirementLabelRelation>{
	
	/**
	 * 根据需求id查询标签对象；
	 * 需求-标签(一对多；同一类型标签只能选一个；标签最多5个)
	 * @param 需求id
	 * @return 标签对象集合
	 */
	List<ELRequirementLabelRelation> findLabelByRequirementId(String requirementId);
	
}
