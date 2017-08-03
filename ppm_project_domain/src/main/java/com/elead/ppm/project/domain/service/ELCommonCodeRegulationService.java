package com.elead.ppm.project.domain.service;

import java.util.List;
import java.util.Map;

import com.elead.ppm.project.domain.entity.ELCommonCodeRegulation;


/**
 * service接口
 * @author Administrator
 *
 */
public interface ELCommonCodeRegulationService {
	
	/**
	 * 添加
	 * @param record
	 * @return
	 */
	String insertCommonCodeRegulation(ELCommonCodeRegulation record);
	/**
	 * 删除
	 * @param record
	 * @return
	 */
	int deleteCodeRegulation(String id);
	/**
	 * 修改
	 * @param record
	 * @return
	 */
	String updateByPrimaryKeySelective(ELCommonCodeRegulation record);
	
	/**
	 * 根据name查询
	 * @param name
	 * @return
	 */
	List<ELCommonCodeRegulation> getCommonCodeByName(String name);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	String GetCommonCodeRegulationById(String id);
	/**
	 * 搜索
	 * @param id
	 * @return
	 */
	Map<String, Object> search(
			String name, int pageNum, int pageSize, String sort, String order);
	
	
	/**
	 * 加载列表
	 * @return
	 */
	Map<String, Object> loadList(
			int pageNum, int pageSize, String sort, String order);
	
	
	/**
	 * 工具类---用来生成与列表字段一致的结果集
	 * @param list
	 * @return
	 */
	public List<Map<String, Object>> getDataUtil(List<ELCommonCodeRegulation> list);


}
