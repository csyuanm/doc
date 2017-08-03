/**
 * 
 */
package com.elead.ppm.project.provider.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.elead.platform.common.service.dao.CrudDao;
import com.elead.ppm.project.domain.entity.ELProjectBaseInfo;
import com.elead.ppm.project.domain.entity.ELProjectMyCare;

/**
 * @author mml
 * @version 创建时间：2017年4月18日  下午2:37:20
 */
@Mapper
@Repository
public interface ELProjectMyCareDao extends CrudDao<ELProjectBaseInfo>{
	
	
	int collectProject(ELProjectMyCare myCare);
	
	int checkRepeat(String projectId);
		
	
	
}
