package com.elead.ppm.project.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.elead.platform.common.service.dao.CrudDao;
import com.elead.ppm.project.domain.entity.ELCommonAttachment;

/**
 * 附件dao接口
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface ELCommonAttachmentDao extends CrudDao<ELCommonAttachment>{
	
	/**
	 * 根据类型和需求id获取附件列表
	 * @param ownerType
	 * @param ownerId
	 * @return
	 */
	List<ELCommonAttachment> getAttachmentsByTypeAndOwnerId
		(@Param("ownerType")String ownerType, @Param("ownerId")String ownerId);

}
