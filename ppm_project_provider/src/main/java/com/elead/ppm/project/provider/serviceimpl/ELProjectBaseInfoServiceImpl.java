/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.elead.ppm.project.provider.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.elead.platform.common.api.CommonResponse;
import com.elead.platform.common.service.service.CrudService;
import com.elead.platform.common.utils.JsonMapper;
import com.elead.platform.system.domain.entity.ELDict;
import com.elead.platform.system.domain.entity.ELPeople;
import com.elead.platform.system.domain.service.ELDictService;
import com.elead.platform.system.domain.service.ELPeopleService;
import com.elead.ppm.project.domain.dto.ProjectDto;
import com.elead.ppm.project.domain.entity.ELProjectBaseInfo;
import com.elead.ppm.project.domain.entity.ELProjectMemberBaseInfo;
import com.elead.ppm.project.domain.entity.ELProjectMyCare;
import com.elead.ppm.project.domain.service.ELPlanStageBaseInfoService;
import com.elead.ppm.project.domain.service.ELProjectBaseInfoService;
import com.elead.ppm.project.domain.service.ELProjectMemberBaseInfoService;
import com.elead.ppm.project.provider.dao.ELProjectBaseInfoDao;
import com.elead.ppm.project.provider.dao.ELProjectMyCareDao;
import com.elead.ppm.project.provider.util.MailUtil;


/**
 * 项目service
 */
@Service(value = "projectBaseInfoService")
public class ELProjectBaseInfoServiceImpl extends CrudService <ELProjectBaseInfoDao, ELProjectBaseInfo> 
		implements ELProjectBaseInfoService {
	@Autowired
	ELProjectBaseInfoDao elProjectDao;
	@Autowired
	ELProjectMemberBaseInfoService elProjectMemberBaseInfoService;
	@Autowired
	ELPlanStageBaseInfoService elPlanStageBaseInfoService;
	@Autowired
	ELProjectMyCareDao myProjectDao;
	@Autowired
	private ELPeopleService elPeopleService ;
	@Autowired
	private ELDictService dictService;
	
	/**
	 * 保存项目基本信息
	 * @param elMemberId[] ELProjectBaseInfo
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
	public ELProjectBaseInfo saveProjectBaseInfo(String[] elMemberId,String invitedMemberIds,ELProjectBaseInfo elProjectBaseInfo) {
		String id = new String();
		elProjectBaseInfo.setIsNewRecord(true);
		elProjectBaseInfo.preInsert();
		elProjectDao.insert(elProjectBaseInfo);
		id = elProjectBaseInfo.getId();
		elPlanStageBaseInfoService.saveProjectStageBaseInfo(elProjectBaseInfo);
		elProjectMemberBaseInfoService.saveProjectMemberInfo(elMemberId, elProjectBaseInfo);
		
		String projectName = elProjectBaseInfo.getProjectName() ;
		String pmName = elProjectBaseInfo.getPmName() ;
		//邀请成员发邮件
		String[] elMemberIds = invitedMemberIds.split(",");
		for(String mebsId : elMemberIds){
			if("null".equals(mebsId)){
				continue ;
			}
			ELPeople elPeople = elPeopleService.getPeople(mebsId);
			String email = elProjectBaseInfo.getElUser().getEmail();
			String username = elProjectBaseInfo.getElUser().getUserName() ;
			String url = "<a href='http://www.ppmview.com/elead-mgmt-project/frame/project/invitedMember.html?elProjectId="+id+"&elMemberId="+mebsId+"'>跳转链接</a>" ;
			MailUtil.sendEmail(""+pmName+"邀请您加入【"+projectName+"】项目", ""+username+"（"+email+"）邀请您加入【"+projectName+"】项目，点击加入"+url+"", new String[]{elPeople.getEmail()});
		}
		return elProjectBaseInfo ;
	}
	/**
	 * 根据项目ID查询单条项目基本信息
	 * @param projectId
	 * @return ELProjectBaseInfo
	 */
	@Transactional(readOnly=true)
	@Override
	public String getProjectBaseInfoByProjectId(String projectId) {
		if(null != projectId && !"".equals(projectId)){
			ELProjectBaseInfo elProjectBaseInfo = elProjectDao.get(projectId);
			ELDict dict = dictService.get(elProjectBaseInfo.getProjectClass());
			elProjectBaseInfo.setProjectClass(dict.getLabel());
			//查询成功则返回一个对象并置为success
			return JsonMapper.toJsonString(CommonResponse.createCommonResponse(elProjectBaseInfo));
		}
		logger.error("项目ID为null,程序出错");
		return JsonMapper.toJsonString(CommonResponse.createNotExistCommonResponse(projectId));
	}
	/**
	 * 更新项目信息
	 * @param ELProjectBaseInfo
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
	public void updateProjectBaseInfo(String[] elMemberId,String invitedMemberIds,ELProjectBaseInfo elProjectBaseInfo) {
		elProjectBaseInfo.preUpdate();
		elProjectDao.update(elProjectBaseInfo);
		
		//elPlanStageBaseInfoService.savePlanStageBaseInfo(elProjectBaseInfo.getId());
		elProjectMemberBaseInfoService.updateProjectMemberInfo(elMemberId, elProjectBaseInfo);
		
		String projectName = elProjectBaseInfo.getProjectName() ;
		String pmName = elProjectBaseInfo.getPmName() ;
		//邀请成员发邮件
		String[] elMemberIds = invitedMemberIds.split(",");
		for(String mebsId : elMemberIds){
			if(mebsId.isEmpty()){
				continue ;
			}
			ELPeople elPeople = elPeopleService.getPeople(mebsId);
			String username = elProjectBaseInfo.getElUser().getUserName() ;
			String email = elProjectBaseInfo.getElUser().getEmail();
			String url = "<a href='http://www.ppmview.com/elead-mgmt-project/frame/project/invitedMember.html?elProjectId="+elProjectBaseInfo.getId()+"&elMemberId="+mebsId+"'>跳转链接</a>" ;
			MailUtil.sendEmail(""+pmName+"邀请您加入【"+projectName+"】项目", ""+username+"（"+email+"）邀请您加入【"+projectName+"】项目，点击加入"+url+"", new String[]{elPeople.getEmail()});
		}
	}
	
	/**
	 * 项目归档
	 */
	@Transactional(readOnly=false)
	@Override
	public void updateProjectFile(String projectId) {
		
		elProjectDao.updateProjectFile(projectId);

	}
	
	/**
	 * 收藏项目
	 */
	@Transactional(readOnly=false)
	@Override
	public String careProject(String projectId, ELProjectMyCare myCare) {
		
		int count = myProjectDao.checkRepeat(projectId);
		try{
			if(count == 0){
				ELProjectBaseInfo baseInfo = super.get(projectId);
				String name = baseInfo.getProjectName();
				//ELProjectMyCare myCare = new ELProjectMyCare();
				UUID uuid = UUID.randomUUID();
				myCare.setId(uuid.toString().replace("-", ""));
				myCare.setProjectName(name);
				//myCare.setProjectId(projectId);
				myProjectDao.collectProject(myCare);
				return JsonMapper.toJsonString(CommonResponse.createCommonResponse("收藏成功"));
				
			}else{
				return JsonMapper.toJsonString(CommonResponse.createCommonResponse("你已经收藏该项目!"));
			}
		}catch(Exception e){
			logger.error("收藏出错", e);
			return JsonMapper.toJsonString(CommonResponse.createExceptionCommonResponse(e));
		}
	}
	/**
	 * 关闭项目
	 */
	@Transactional(readOnly=false)
	@Override
	public void updateProjectState(String id) {
		
		
		elProjectDao.updateProjectState(id);
		//关闭项目后需要实现通知成员功能
		ELProjectBaseInfo baseInfo = super.get(id);
		
		String projectName = baseInfo.getProjectName();
		
		
		String[] members = elProjectMemberBaseInfoService.getProjectMemberNameByProjectId(id);
		for (String member : members) {
			if(member.isEmpty()){
				continue ;
			}
			ELPeople elPeople = new ELPeople();
			elPeople.setName(member);
			//String url = "<a href='http://www.ppmview.com/elead-mgmt-project/frame/project/invitedMember.html?elProjectId="+id+"&elMemberId="+"'>跳转链接</a>" ;
			System.out.println("你好"+member+"你所在的"+projectName+"已经关闭！");
		//	MailUtil.sendEmail("你好"+member, "你所在的"+projectName, new String[]{elPeople.getEmail()});
	}
}
	
	/**
	 * 邀请成员
	 */
	@Override
    public String invitedMember(String elMemberId, String projectId) {
		ELProjectBaseInfo baseInfo = elProjectDao.get(projectId);
		ELProjectMemberBaseInfo elProjectMemberBaseInfo =  elProjectMemberBaseInfoService.getProjectMemberById(elMemberId, projectId);
		if(elProjectMemberBaseInfo == null){
			elProjectMemberBaseInfoService.saveProjectMemberInfo(new String[]{elMemberId}, baseInfo);
		}else{
			return JsonMapper.toJsonString(CommonResponse.createWrongParamCommonResponse("已经存在改项目中，无需加入!"));
		}
		return JsonMapper.toJsonString(CommonResponse.createCommonResponse(""));
		
    }
	
	
	/**
	 * 查找项目通过当前用户
	 */
	@Override
    public String getProjectList(String code,int pageIndex,int pageSize) {
		String userid = "" ;
		int startIndex = (pageIndex-1) * pageSize;
		ProjectDto dto = new ProjectDto();
		dto.setProjectCode(code);
		List<ProjectDto> projectDtos = elProjectDao.getProjectList(dto,startIndex,pageSize);
		JSONObject jsonObject = new JSONObject() ;
		jsonObject.put("list", projectDtos);
		jsonObject.put("code", 0);
		jsonObject.put("count", elProjectDao.queryProjectTotal(userid));
	    return jsonObject.toJSONString();
    }
	
	@Override
    public JSONArray getProjectListByName(ProjectDto projectDto, int pageIndex, int pageSize) {
		List<ProjectDto> projectDtos = elProjectDao.getProjectListByName(projectDto,0,15);
		JSONArray array = new JSONArray();
		for(ProjectDto projectDto2 : projectDtos){
			JSONObject json = new JSONObject() ;
			json.put("id", projectDto2.getId());
			json.put("name", projectDto2.getName());
			array.add(json)		;
		}
	    return array;
    }
	
	/**
	 * @描述（导航栏查看项目-最近打开，个人项目，和我相关的项目）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月11日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	public List<Map<String, String>> getMyAdministrationProject(Map<String, Object> parameter){
		return elProjectDao.getMyAdministrationProject(parameter);
	}
	
	/**
	 * @描述（导航栏查看项目-收藏项目）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月11日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	public List<Map<String, String>> getMyCareProject(Map<String, Object> parameter){
		return elProjectDao.getMyCareProject(parameter);
	}
	
	/**
	 * @描述（导航栏查看项目-收藏项目）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月11日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	public List<Map<String, String>> getIsFileProject(Map<String, Object> parameter){
		return elProjectDao.getIsFileProject(parameter);
	}
	
	/**
	 * @描述（添加最近打开的项目数据）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月16日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@Transactional(readOnly=false)
	@Override
	public void addProjectMyOpen(Map<String, Object> parameter){
		parameter.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
		elProjectDao.addProjectMyOpen(parameter);
	}
	
	/**
	 * @描述（查询最近打开的项目数据量）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月16日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@Transactional(readOnly=false)
	@Override
	public int getProjectMyOpenCount(Map<String, Object> parameter){
		return elProjectDao.getProjectMyOpenCount(parameter);
	}
	
	/**
	 * @描述（修改最近打开的项目数据）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月16日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@Transactional(readOnly=false)
	@Override
	public void updateProjectMyOpen(Map<String, Object> parameter){
		 elProjectDao.updateProjectMyOpen(parameter);
	}
	
	/**
	 * @描述（删除最近打开的项目数据，保留3个）
	 * @author yanghuayong
	 * @version 1.0
	 * @date 2017年5月16日 下午12:19:00
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@Transactional(readOnly=false)
	@Override
	public void delProjectMyOpen(String userId){
		elProjectDao.delProjectMyOpen(userId);
	}
	
	/**
	 * 通过项目id和获取已完成的任务总数
	 */
	@Override
    public int getFinishTaskByProjectIdCount(String projectId) {
	    return elProjectDao.getFinishTaskByProjectIdCount(projectId);
    }
	
	/**
	 * 通过项目id和获取所有任务总数
	 */
	@Override
    public int getAllTaskByProjectIdCount(String projectId) {
	    return elProjectDao.getAllTaskByProjectIdCount(projectId);
    }
	
	/**
	 * 通过项目id和获取所有延期任务总数
	 */
	@Override
    public int getDelayTaskByProjectIdCount(String projectId) {
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    return elProjectDao.getDelayTaskByProjectIdCount(projectId, time.format(nowTime));
    }
	
	/**
	 * 通过项目id和获取进行中的任务总数
	 */
	@Override
    public int getDoingTaskByProjectIdCount(String projectId) {
	    return elProjectDao.getDoingTaskByProjectIdCount(projectId);
    }
	
}