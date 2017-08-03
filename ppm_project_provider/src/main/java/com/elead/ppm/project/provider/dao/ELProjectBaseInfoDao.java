/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.elead.ppm.project.provider.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.elead.platform.common.service.dao.CrudDao;
import com.elead.ppm.project.domain.dto.ProjectDto;
import com.elead.ppm.project.domain.entity.ELProjectBaseInfo;

/**
 * 项目表DAO接口
 * @author wangxz
 */

@Mapper
@Repository
public interface ELProjectBaseInfoDao extends CrudDao<ELProjectBaseInfo> {
	/**
	 * 通过项目id关闭项目
	 * @author 蒙茂良
	 * 创建时间：2017年5月17日  下午4:41:09
	 * version 1.0
	 * @param projectId
	 * @return
	 */
	int updateProjectState(String projectId);
	
	/**
	 * 通过项目id归档项目
	 * @author 蒙茂良
	 * 创建时间：2017年5月17日  下午4:41:19
	 * version 1.0
	 * @param projectId
	 * @return
	 */
	int updateProjectFile(String projectId);
	
	/**
	 * 根据项目id查找项目名字
	 * @author 蒙茂良
	 * 创建时间：2017年5月25日  下午2:12:43
	 * version 1.0
	 * @param projectId
	 * @return
	 */
	String getNameByProjectId(String projectId);
	
	/**
	 * @描述（根据条件查找项目）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年4月27日 下午5:43:17
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值 projectDto
	 */
	List<ProjectDto> getProjectListByName(@Param("projectDto")ProjectDto projectDto,@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);
	
	/**
	 * @描述（根据条件查找项目）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年4月27日 下午5:43:17
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值 projectDto
	 */
	List<ProjectDto> getProjectList(@Param("projectDto")ProjectDto projectDto,@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);
	
	/**
	 * @描述（根据用户查找项目总数）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年4月27日 下午5:42:59
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	int queryProjectTotal(@Param("userid")String userid);
	
	/**
	 * @描述（导航栏查看项目）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月11日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	List<Map<String, String>> getMyAdministrationProject(Map<String, Object> parameter);
	
	/**
	 * @描述（导航栏查看项目-收藏项目）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月11日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
     List<Map<String, String>> getMyCareProject(Map<String, Object> parameter);
     
 	/**
 	 * @描述（导航栏查看项目-收藏项目）
 	 * @author yanghuayong
 	 * @version 1.0
 	 * @date 2017年5月11日 下午12:19:00
 	 * @parameter （参数及其意义）
 	 * @throws 异常类及抛出条件
 	 * @return 返回值
 	 */
 	List<Map<String, String>> getIsFileProject(Map<String, Object> parameter);
 	
	/**
	 * @描述（添加最近打开的项目数据）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月16日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	void addProjectMyOpen(Map<String, Object> parameter);
	
	
	int getProjectMyOpenCount(Map<String, Object> parameter);
	
	void updateProjectMyOpen(Map<String, Object> parameter);
	
	void delProjectMyOpen(String userId);
	
	
	/**
	 * @描述（通过项目id和获取已完成的任务总数）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月24日 下午5:39:56
	 */
	int getFinishTaskByProjectIdCount(String projectId);
	
	/**
	 * @描述（通过项目id和获取所有任务总数）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月24日 下午5:39:56
	 */
	int getAllTaskByProjectIdCount(String projectId);
	
	/**
	 * @描述（通过项目id和获取所有延期任务总数）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月24日 下午5:39:56
	 */
	int getDelayTaskByProjectIdCount(@Param("projectId")String projectId,@Param("currentDate")String currentDate);
	
	/**
	 * @描述（通过项目id和获取进行中的任务总数）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月24日 下午5:39:56
	 */
	int getDoingTaskByProjectIdCount(String projectId);
}