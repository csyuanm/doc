package com.elead.ppm.project.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.elead.platform.common.service.dao.CrudDao;
import com.elead.ppm.project.domain.entity.ELCommonAttachment;
import com.elead.ppm.project.domain.entity.ELCommonComment;

/**
 * 评论实体类
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface ELCommonCommentDao extends CrudDao<ELCommonComment>{
	
	/**
	 * 根据类型和需求id获取评论列表
	 * @param ownerType
	 * @param ownerId
	 * @return
	 */
	List<ELCommonComment> getCommentsByTypeAndOwnerId
		(@Param("ownerType")String ownerType, @Param("ownerId")String ownerId);

}
