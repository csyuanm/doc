package com.elead.ppm.project.domain.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.elead.ppm.project.domain.dto.ProjectDto;
import com.elead.ppm.project.domain.entity.ELProjectBaseInfo;
import com.elead.ppm.project.domain.entity.ELProjectMyCare;

/**
 * @author zhangwei
 * @title: 项目服务接口
 * @date 2017/03/23
 */
public interface ELProjectBaseInfoService {
	
	/**
	 * 保存项目基本信息,需要返回ID，用来生成计划类型
	 * @param elProject
	 * @param invitedMemberIds 邀请成员ids
	 * @return id
	 */
	ELProjectBaseInfo saveProjectBaseInfo(String[] elMemberId,String invitedMemberIds, ELProjectBaseInfo elProjectBaseInfo);
	
	/**
	 * 根据项目名称查询单条项目基本信息
	 * @param projectId
	 * @return ELProject
	 */
	String getProjectBaseInfoByProjectId(String projectId);
	/**
	 * 更新项目信息并实时显示到页面上
	 * @param elProjectBaseInfo
	 */
	void updateProjectBaseInfo(String[] elMemberId,String invitedMemberIds,ELProjectBaseInfo elProjectBaseInfo);
	
	/**
	 * 项目归档
	 * @author 蒙茂良
	 * 创建时间：2017年4月18日  下午4:06:25
	 * version 1.0
	 * @param projectId
	 */
	void updateProjectFile(String projectId);
	
	/**
	 * 项目关闭
	 * @author 蒙茂良
	 * 创建时间：2017年4月18日  下午4:06:57
	 * version 1.0
	 * @param projectId
	 */
	void updateProjectState(String id);
	
	
	/**
	 * 收藏项目
	 * @author 蒙茂良
	 * 创建时间：2017年4月18日  下午6:03:35
	 * version 1.0
	 * @param myCare
	 */
	String careProject(String projectId, ELProjectMyCare myCare);
	
	
	/**
	 * @描述（邀请成员）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年4月18日 下午3:59:15
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	String invitedMember(String elMemberId, String elProjectId);
	
	/**
	 * 
	 * @描述（通过项目名称获取所有项目，项目列表）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月4日 下午12:19:14
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	String getProjectList(String name,int pageIndex,int pageSize);
	
	
	/**
	 * @描述（项目切换搜索）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月4日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	JSONArray getProjectListByName(ProjectDto projectDto,int pageIndex,int pageSize);
	
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
	
	/**
	 * @描述（查询最近打开的项目数据量）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月16日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	int getProjectMyOpenCount(Map<String, Object> parameter);
	
	/**
	 * @描述（修改最近打开的项目数据）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月16日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	void updateProjectMyOpen(Map<String, Object> parameter);
	
	/**
	 * @描述（删除最近打开的项目数据，保留3个）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月16日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	void delProjectMyOpen(String userId);
	
	/**
	 * @描述（通过项目id和获取已完成的任务总数）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月24日 下午5:39:56
	 */
	int getFinishTaskByProjectIdCount(String projectId);
	
	/**
	 * @描述（通过项目id和获取进行中的任务总数）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月24日 下午5:39:56
	 */
	int getDoingTaskByProjectIdCount(String projectId);
	
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
	int getDelayTaskByProjectIdCount(String projectId);
	
}
