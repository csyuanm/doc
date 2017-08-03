package com.elead.ppm.project.consumer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.elead.platform.common.api.CommonResponse;
import com.elead.platform.common.api.ELPage;
import com.elead.platform.common.api.ELUser;
import com.elead.platform.common.utils.CodeUtils;
import com.elead.platform.common.utils.JsonMapper;
import com.elead.platform.common.web.BaseController;
import com.elead.platform.system.domain.entity.ELDict;
import com.elead.platform.system.domain.entity.ELPeople;
import com.elead.platform.system.domain.service.ELDictService;
import com.elead.platform.system.domain.service.ELPeopleService;
import com.elead.ppm.project.domain.dto.ProjectDto;
import com.elead.ppm.project.domain.entity.ELCommonCodeRegulation;
import com.elead.ppm.project.domain.entity.ELProjectBaseInfo;
import com.elead.ppm.project.domain.entity.ELProjectMyCare;
import com.elead.ppm.project.domain.service.ELProjectBaseInfoService;

@RestController
@Api(tags="项目信息")
@RequestMapping(value = "/project")
public class ProjectController extends BaseController {
	
	@Autowired
	private ELProjectBaseInfoService elProjectBaseInfoService;
	
	@Autowired
	private ELPeopleService elPeopleService ;
	
	@Autowired
	private ELDictService dictService;
	

	/**
	 * 保存项目基本信息并根据配置表和项目类型生成相应计划类型
	 * @param request
	 */
	@Valid
	@ApiOperation("保存项目基本信息并根据配置表和项目类型生成相应计划类型")
	@PostMapping(value = "/saveProjectBaseInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommonResponse saveProjectBaseInfo(HttpServletRequest request){
		//获取json字符串并解析
		String elProjectJsonString = request.getParameter("elProjectJsonString").toString();
		String elProjectMember = "" ;
		if(request.getParameter("elProjectMember")!=null){
			elProjectMember = request.getParameter("elProjectMember").toString();
		}
		String invitedMemberIds = "" ;
		if(request.getParameter("invitedMemberIds")!=null){
			invitedMemberIds =  request.getParameter("invitedMemberIds").toString();
		}
	//	ELUser<?> loginUser = super.getLoginUser();
		//if(loginUser == null){
		//	return CommonResponse.createCustomCommonResponse(HttpStatus.UNAUTHORIZED + "", "用户没有登陆");
		//}
		//反序列化
		ELProjectBaseInfo elProjectBaseInfo = JSON.parseObject(elProjectJsonString,ELProjectBaseInfo.class);		
		String[] elMemberId = elProjectMember.split(",");
	//	elProjectBaseInfo.setElUser(loginUser);
		
		if(StringUtils.isNotEmpty(elProjectBaseInfo.getPmName())){
			ELPeople elPeople = elPeopleService.getPeople(elProjectBaseInfo.getPmName());
			if(null != elPeople){
				elProjectBaseInfo.setPmId(elPeople.getId());
				elProjectBaseInfo.setPmName(elPeople.getUserName());
			}
		}
		/**
		 * 保存项目成员信息
		 */
		ELProjectBaseInfo baseInfo = null ;
		try {
			baseInfo = elProjectBaseInfoService.saveProjectBaseInfo(elMemberId,invitedMemberIds, elProjectBaseInfo);
        } catch (Exception e) {
        	e.printStackTrace();
			return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		}
		return CommonResponse.createCommonResponse(baseInfo);
	}

	
	/**
	 * 根据当前用户查询项目基本信息
	 * @param projectId
	 * @return ELProject
	 */
	@Valid
	@ApiOperation("根据当前用户查询项目基本信息")
	@GetMapping(value = "/getProjectList", produces = "application/json;charset=UTF-8")
	public String getProjectList(String code,int pageIndex ,int pageSize){
		return elProjectBaseInfoService.getProjectList(code,pageIndex,pageSize) ;
	}
	
	/**
	 * 根据项目ID查询项目基本信息
	 * @param projectId
	 * @return ELProject
	 */
	@Valid
	@ApiOperation(" 根据项目ID查询项目基本信息")
	@GetMapping(value = "/getProjectBaseInfo", produces = "application/json;charset=UTF-8")
	public String getProjectInfoByProjectId(@ApiParam(required=true,value="编码ID")@RequestParam(value="id")@NotNull(message=CodeUtils.EMPTY_CODE)String id){
		return elProjectBaseInfoService.getProjectBaseInfoByProjectId(id);
	}
	
	/**
	 * 更新项目信息
	 * @param ELProjectBaseInfo
	 */
	@Valid
	@ApiOperation("更新项目信息")
	@PostMapping(value = "/updateProjectBaseInfo", produces = "application/json;charset=UTF-8")
	public CommonResponse updateProjectBaseInfo(HttpServletRequest request){
		//获取json字符串并解析
		String elProjectBaseInfoJson = request.getParameter("elProjectJsonString");
		//反序列化
		ELProjectBaseInfo elProjectBaseInfo = JSON.parseObject(elProjectBaseInfoJson,ELProjectBaseInfo.class);
		//ELUser<?> loginUser = super.getLoginUser();
		//if(loginUser == null){
		//	return CommonResponse.createCustomCommonResponse(HttpStatus.UNAUTHORIZED + "", "用户没有登陆");
		//}
		String[] elMemberId = {};
		String elProjectMember = "" ;
		if(request.getParameter("elProjectMember")!=null){
			elProjectMember = request.getParameter("elProjectMember").toString();
			elMemberId = elProjectMember.split(",") ;
		}
		String invitedMemberIds = "" ;
		if(request.getParameter("invitedMemberIds")!=null){
			invitedMemberIds =  request.getParameter("invitedMemberIds").toString();
		}
	//	elProjectBaseInfo.setElUser(loginUser);
		
		if(StringUtils.isNotEmpty(elProjectBaseInfo.getPmName())){
			ELPeople elPeople = elPeopleService.getPeople(elProjectBaseInfo.getPmName());
			if(null != elPeople){
				elProjectBaseInfo.setPmId(elPeople.getId());
				elProjectBaseInfo.setPmName(elPeople.getUserName());
			}
		}
		//更新项目基本信息
		try {
			elProjectBaseInfoService.updateProjectBaseInfo(elMemberId,invitedMemberIds, elProjectBaseInfo);
        } catch (Exception e) {
        	e.printStackTrace();
			return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		}
		return CommonResponse.createCommonResponse(CommonResponse.createCommonResponse());
	} 
	
	
	/**
	 * @描述（获取成员）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年4月18日 上午10:28:17
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@ApiOperation("获取成员")
    @RequestMapping(value = "/getMemberByName")
	public JSONArray getMemberByName(String query) throws JSONException{
		JSONArray array = new JSONArray();
		ELPage<ELUser> elUserPage = elPeopleService.fuzzyFindUser(30, 1, query);
		List<ELUser> list = elUserPage.getRows();
		for(ELUser elUser : list){
			JSONObject json = new JSONObject() ;
			json.put("id", elUser.getId());
			json.put("name", elUser.getUserName() + " ("+elUser.getEmail()+")");
			array.add(json)		;
		}
		return array ;
		
	}
    
    /**
	 * @描述（通过项目名称获取项目）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年4月18日 上午10:28:17
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@ApiOperation("通过项目名称获取项目")
    @RequestMapping(value = "/getProjectByName")
	public JSONArray getProjectByName(String projectName,String userid) throws JSONException{
    	ProjectDto projectDto = new ProjectDto();
    	projectDto.setUserid(userid);
    	if(StringUtils.isNotEmpty(projectName)){
    		projectDto.setName("%"+projectName+"%");
    	}
    	ELUser<?> loginUser = super.getLoginUser();
		if(loginUser != null){
			projectDto.setUserid(loginUser.getId());
		}
    	return elProjectBaseInfoService.getProjectListByName(projectDto, 0, 15);
		
	}
    

	
	/**
	 * @描述（获取数据字典）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月5日 上午10:27:23
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@ApiOperation("获取数据字典")
	@RequestMapping(value = "/getBascDataByType", produces = "application/json;charset=UTF-8")
	public JSONArray getBascDataByType(String type,String attribute) throws JSONException{
		ELDict dict = new ELDict();
		dict.setType(type);
		dict.setAttribute(attribute);
		List<ELDict> list = dictService.findList(dict);
		JSONArray jsonArray = new JSONArray();
		for(ELDict dict2:list){
			JSONObject object = new JSONObject();
			object.put("id", dict2.getId());
			object.put("value", dict2.getValue());
			object.put("label", dict2.getLabel());
			object.put("description", dict2.getDescription());
			jsonArray.add(object);
		}
		return jsonArray;
	}
	
		
	/**
	 * @描述（邀请成员）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年4月18日 下午4:06:03
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@ApiOperation("邀请成员")
	@RequestMapping(value = "/invitedMember")
	public String invitedMember(String elProjectId,String elMemberId){
		return elProjectBaseInfoService.invitedMember(elMemberId,elProjectId);
	}
	
	/**
	 * 项目归档
	 * @author 蒙茂良
	 * 创建时间：2017年4月18日  下午4:09:36
	 * version 1.0
	 * @param projectId
	 * @return
	 */
	@ApiOperation("项目归档")
	@RequestMapping(value = "/updateProjectFile", produces = "application/json;charset=UTF-8")
	public String updateProjectFile(@ApiParam(required=true,value="项目ID")@RequestParam(value="projectId")@NotNull(message=CodeUtils.EMPTY_CODE)String projectId){
		try {
			elProjectBaseInfoService.updateProjectFile(projectId);
			return JsonMapper.toJsonString(CommonResponse.createCommonResponse("归档成功"));
		} catch (Exception e) {
			logger.error("归档出错", e);
			return JsonMapper.toJsonString(CommonResponse.createExceptionCommonResponse(e));
		}
	}
	
	/**
	 * 关闭项目
	 * @author 蒙茂良
	 * 创建时间：2017年4月28日  上午10:11:47
	 * version 1.0
	 * @param projectId 
	 * @param id
	 */
	@ApiOperation("关闭项目")
	@RequestMapping(value = "/updateProjectState", produces = "application/json;charset=UTF-8")
	public String updateProjectState(@ApiParam(required=true,value="项目ID")@RequestParam(value="id")@NotNull(message=CodeUtils.EMPTY_CODE)String id){
		try {
			elProjectBaseInfoService.updateProjectState(id);
			return JsonMapper.toJsonString(CommonResponse.createCommonResponse("关闭成功"));
		} catch (Exception e) {
			logger.error("关闭项目出错", e);
			return JsonMapper.toJsonString(CommonResponse.createExceptionCommonResponse(e));
		}
	}
	
	/**
	 * 收藏项目
	 * @author 蒙茂良
	 * 创建时间：2017年4月20日  下午4:07:47
	 * version 1.0
	 * @param myCareProject
	 * @return
	 */
	@Valid
	@ApiOperation("收藏项目")
	@RequestMapping(value ="/addMyCareProject",produces = "application/json;charset=UTF-8")
	public String careMyProject(@ApiParam(required=true,value="项目id")@RequestParam(value="projectId")@NotNull(message=CodeUtils.EMPTY_CODE)String projectId){		
			
				ELProjectMyCare myCare = new ELProjectMyCare();
				myCare.setProjectId(projectId);
				ELUser<?> loginUser = super.getLoginUser();
				if(loginUser != null){
					myCare.setUserId(loginUser.getId());
					myCare.setCreateBy(loginUser.getId());
					myCare.setUpdateBy(loginUser.getId());
				}
				//myCare.setProjectName(projectName);
				//myCare.setUserId(userId);  用户名称后台获取
				//后台返回string时，需要转化为接收格式
				return elProjectBaseInfoService.careProject(projectId,myCare);
				
		
	}
	
	/**
	 * @描述（导航栏查看项目-最近打开，个人项目，和我相关的项目）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月11日 下午10:56:03
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@RequestMapping(value = "/getMyAdministrationProject")
	@ApiOperation(value = "获取我最近打开，个人项目，和我相关的项目")
	public CommonResponse getMyAdministrationProject(@ApiParam(required=true,value="搜索的名称")@RequestParam(value="keywords")@NotNull(message=CodeUtils.EMPTY_CODE)String keywords){
		Map<String, Object> parameter = new HashMap<String, Object>();
		ELUser user = super.getLoginUser();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		if(null != user){
			parameter.put("userId", user.getId());
			parameter.put("keywords", keywords);
			list = elProjectBaseInfoService.getMyAdministrationProject(parameter);
		}
		return CommonResponse.createCommonResponse(list);
	}

	/**
	 * @描述（导航栏查看项目-收藏项目）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月11日 下午10:56:03
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@RequestMapping(value = "/getMyCareProject")
	@ApiOperation(value = "获取我关注的项目")
	public CommonResponse getMyCareProject(@ApiParam(required=true,value="搜索的名称")@RequestParam(value="keywords")@NotNull(message=CodeUtils.EMPTY_CODE)String keywords){
		Map<String, Object> parameter = new HashMap<String, Object>();
		ELUser user = super.getLoginUser();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		if(null != user){
			parameter.put("userId", user.getId());
			parameter.put("keywords", keywords);
			list = elProjectBaseInfoService.getMyCareProject(parameter);
		}
		return CommonResponse.createCommonResponse(list);
	}
	
	/**
	 * @描述（导航栏查看项目-收藏项目）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月11日 下午10:56:03
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@RequestMapping(value = "/getIsFileProject")
	@ApiOperation(value = "获取归档项目")
	public CommonResponse getIsFileProject(@ApiParam(required=true,value="搜索的名称")@RequestParam(value="keywords")@NotNull(message=CodeUtils.EMPTY_CODE)String keywords){
		Map<String, Object> parameter = new HashMap<String, Object>();
		ELUser user = super.getLoginUser();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		if(null != user){
			parameter.put("userId", user.getId());
			parameter.put("keywords", keywords);
			list = elProjectBaseInfoService.getIsFileProject(parameter);
		}
		return  CommonResponse.createCommonResponse(list);
	}
	
	/**
	 * @描述（导航栏查看项目-添加最近查看记录）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月11日 下午10:56:03
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	@RequestMapping(value = "/addProjectMyOpen")
	@ApiOperation(value = "导航栏查看项目-添加最近查看记录")
	public CommonResponse addProjectMyOpen(@ApiParam(required=true,value="项目ID")@RequestParam(value="projectId")@NotNull(message=CodeUtils.EMPTY_CODE)String projectId){
		Map<String, Object> parameter = new HashMap<String, Object>();
		ELUser user = super.getLoginUser();
		if(null != user){
			parameter.put("userId", user.getId());
			parameter.put("projectId", projectId);
			if(elProjectBaseInfoService.getProjectMyOpenCount(parameter) > 0){
				elProjectBaseInfoService.updateProjectMyOpen(parameter);
			}else{
				elProjectBaseInfoService.addProjectMyOpen(parameter);
			}
			elProjectBaseInfoService.delProjectMyOpen(user.getId());
		}
		return CommonResponse.createCommonResponse();
	}

	
	/**
	 * @描述（项目进度【项目进度=已完成任务/总任务数】）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月24日 下午5:48:51
	 * @return 返回值
	 */
	@RequestMapping(value = "/getProjectProgressById")
	@ApiOperation(value = "项目进度")
	public CommonResponse getProjectProgressById(@ApiParam(required=true,value="项目ID")@RequestParam(value="projectId")@NotNull(message=CodeUtils.EMPTY_CODE)String projectId){
		//通过项目id和获取已完成的任务总数
		int count = elProjectBaseInfoService.getFinishTaskByProjectIdCount(projectId);
		//通过项目id和获取所有任务总数
		int allCount = elProjectBaseInfoService.getAllTaskByProjectIdCount(projectId);
		JSONArray array = new JSONArray();
		JSONObject njsonObject = new  JSONObject();
		njsonObject.put("name", "已完成任务数");
		njsonObject.put("value", count);
		njsonObject.put("color", "#fedd74");
		String percentage = ELProjectBaseInfo.percnet(Double.valueOf(count), Double.valueOf(allCount));
		njsonObject.put("Percentage", percentage);//项目进度=已完成任务/总任务数
		array.add(njsonObject);
		
		JSONObject yjsonObject = new  JSONObject();
		yjsonObject.put("name", "未完成任务数");
		yjsonObject.put("value", allCount-count);
		yjsonObject.put("color", "#eee");
		array.add(yjsonObject);
		return CommonResponse.createCommonResponse(array);
	}
	
	
	/**
	 * @描述（延误率【延误率=延误任务数/已完成任务数】）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月24日 下午5:48:51
	 * @return 返回值
	 */
	@RequestMapping(value = "/getDelayTaskByProjectId")
	@ApiOperation(value = "延误率")
	public CommonResponse getDelayTaskByProjectId(@ApiParam(required=true,value="项目ID")@RequestParam(value="projectId")@NotNull(message=CodeUtils.EMPTY_CODE)String projectId){
		//通过项目id和获取已完成的任务总数
		int count = elProjectBaseInfoService.getFinishTaskByProjectIdCount(projectId);
		//进行中任务总数
		int doingCount = elProjectBaseInfoService.getDoingTaskByProjectIdCount(projectId);
		//通过项目id和获取所有延期任务总数
		int delayCount = elProjectBaseInfoService.getDelayTaskByProjectIdCount(projectId);
		JSONArray array = new JSONArray();
		JSONObject njsonObject = new  JSONObject();
		njsonObject.put("name", "进行中任务数");
		njsonObject.put("value", doingCount);
		njsonObject.put("color", "#fedd74");
		String percentage = ELProjectBaseInfo.percnet(Double.valueOf(delayCount), Double.valueOf(count));
		njsonObject.put("Percentage", percentage);//延误率=延误任务数/已完成任务数
		array.add(njsonObject);
		
		JSONObject yjsonObject = new  JSONObject();
		yjsonObject.put("name", "延误任务数");
		yjsonObject.put("value", delayCount);
		yjsonObject.put("color", "#eee");
		array.add(yjsonObject);
		return CommonResponse.createCommonResponse(array);
	}
	
	
	
}
