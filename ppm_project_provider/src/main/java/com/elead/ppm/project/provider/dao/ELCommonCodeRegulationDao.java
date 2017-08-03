package com.elead.ppm.project.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.elead.platform.common.service.dao.CrudDao;
import com.elead.ppm.project.domain.entity.ELCommonCodeRegulation;


/**
 * dao接口
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface ELCommonCodeRegulationDao extends CrudDao<ELCommonCodeRegulation>{
	
	/**
	 * 添加
	 * @param record  ELCommonCodeRegulation对象
	 * @return
	 */
	int insert(ELCommonCodeRegulation record);
	
	
	/**
	 * 修改
	 * @param record ELCommonCodeRegulation对象
	 * @return
	 */
	int updateByPrimaryKeySelective(ELCommonCodeRegulation record);
	/**
	 * 根据name进行查询
	 * @param name
	 * @return
	 */
	List<ELCommonCodeRegulation> getCommonCodeByName(String name);
	/**
	 * 根据name进行模糊查询
	 * @param name
	 * @return
	 */
	List<ELCommonCodeRegulation> findByName(
			@Param("name")String name, @Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize, @Param("sort")String sort,
			@Param("order")String order);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<ELCommonCodeRegulation> findAll(
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize,
			@Param("sort")String sort, @Param("order")String order);
	
	
	/**
	 * 查询总数
	 * @return
	 */
	int queryTotal(@Param("type")String type, @Param("value")String value);
	/**
	 * 根据id查询对象
	 * @return
	 */
	ELCommonCodeRegulation getCodeRegulationById(String id);
}
