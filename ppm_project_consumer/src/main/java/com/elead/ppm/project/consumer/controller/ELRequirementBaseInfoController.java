package com.elead.ppm.project.consumer.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.elead.platform.common.api.CommonResponse;
import com.elead.platform.common.api.ELUser;
import com.elead.platform.common.web.BaseController;
import com.elead.platform.system.domain.entity.ELDict;
import com.elead.platform.system.domain.service.ELDictService;
import com.elead.platform.system.domain.service.ELPeopleService;
import com.elead.ppm.project.domain.entity.CommonCheckItem;
import com.elead.ppm.project.domain.entity.ELRequirementBaseInfo;
import com.elead.ppm.project.domain.entity.Pager;
import com.elead.ppm.project.domain.service.ELRequirementBaseInfoService;
import com.elead.ppm.project.domain.utils.CommonUtils;
import com.elead.ppm.task.domain.entity.ELTaskBaseInfo;
import com.elead.ppm.task.domain.service.ELTaskBaseInfoService;
import com.github.pagehelper.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags="项目需求")
@RequestMapping("/project/requirement")
public class ELRequirementBaseInfoController extends BaseController{
	
	@Autowired
	ELRequirementBaseInfoService elRequirementBaseInfoService;
	
	@Autowired
	private ELPeopleService elPeopleService;
	
	@Autowired
	private ELDictService dictService;
	
	@Autowired
	private ELTaskBaseInfoService elTaskBaseInfoService;
	
	/**
	 * 加载需求列表接口
	 * @param response
	 * @param projectId 项目id
	 * @param page pageNum
	 * @param rows pageSize
	 * @param sort 排序字段
	 * @param order 排序方式
	 * @userId 当前登录用户的ID(与自己相关的需求凸显)
	 * @return
	 */
	@ApiOperation("获取需求列表数据")
	@RequestMapping("/getRequirementInfo")
	public Map<String,Object> getRequirementInfo(
			HttpServletResponse response,String projectId,int page,int rows, String sort, String order,
			String userId){
		
		 //解决跨域访问需求
		 response.addHeader("Access-Control-Allow-Origin", "*");
		 
		 Map<String,Object> map = elRequirementBaseInfoService.getRequirementInfo(projectId, page, rows, sort, order, userId);
		 return map;
	}
	
	
	/**
	 * 筛选、搜索需求接口(需求状态和优先级)
	 * @param response
	 * @param projectId 项目id
	 * @param page pageNum
	 * @param rows pageSize
	 * @param searchType 筛选/搜索类型(需求名、需求状态、优先级、提出人、标签)
	 * @param searchVal 筛选/搜索值
	 * @param sort 排序字段
	 * @param order 排序方式
	 * @param userId 当前登录用户的ID(与自己相关的需求凸显)
	 * @return
	 */
	@ApiOperation("搜索需求列表数据")
	@RequestMapping("/search")
	public Map<String,Object> search(
			HttpServletResponse response, String projectId,int page,int rows, String searchType, 
			String searchVal, String sort, String order, String userId){
		
		//解决跨域访问需求
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		return elRequirementBaseInfoService.search
				(projectId, searchType, searchVal, page, rows, sort, order, userId);
	}
	
	/**
	 * 模糊匹配提出人接口
	 * @param projectId
	 * @param name
	 * @return
	 */
	@ApiOperation("模糊搜索提出人")
	@RequestMapping("/match")
	public String match(String matchType, String projectId, String matchName){
		
		return elRequirementBaseInfoService.match(projectId, matchType, matchName);
	}
	
	
	/**
	 * 新建需求接口
	 * @param projectId  项目ID
	 * @param requirementName 需求名
	 */
	@Valid
	@ApiOperation("新增需求接口")
	@GetMapping(value = "/addRequirement", produces = "application/json;charset=UTF-8")
	public CommonResponse addRequirement(@ApiParam(required = true, value = "项目id") @RequestParam(required = true, value = "projectId", defaultValue = "") String projectId,
			@ApiParam(required = true, value = "需求名称") @RequestParam(required = true, value = "requirementName", defaultValue = "")String name){
		try {
			ELRequirementBaseInfo requireInfo = new ELRequirementBaseInfo();
			requireInfo.setProjectId(projectId);
			requireInfo.setName(name);
		//	ELUser user = super.getLoginUser();//获取登录用户信息
		//	if(null==user){	  
		//	requireInfo.setCreateBy(user.getId());//创建人跟修改人是一样的
		//	}
			int i = elRequirementBaseInfoService.addRequirement(requireInfo);
			return CommonResponse.createCommonResponse(i);
		} catch (Exception e) {
			logger.error("新建需求出错",e);
			return CommonResponse.createExceptionCommonResponse(e);
		}
	}
	
	
	/**
	 * 编辑页面加载需求信息接口
	 * @param response
	 * @param requiremnetId
	 */
	@ApiOperation("根据id获取需求")
	@RequestMapping("/loadEditData")
	public Map<String, Object> loadEditData(String requirementId){
		
		
		return elRequirementBaseInfoService.getInfoByRequirementId(requirementId);
	}
	
	
	/**
	 * 编辑页面修改数据接口
	 */
	@ApiOperation("修改需求")
	@RequestMapping("/editData")
	public void editData(HttpServletResponse response, ELRequirementBaseInfo requirement){
		
		elRequirementBaseInfoService.editData(requirement);
	}
	
	

	@Valid
	@ApiOperation("新增需求")
	@RequestMapping("/saveName")
	public void insert( String name){
		ELRequirementBaseInfo elRequirementBaseInfo = new ELRequirementBaseInfo();
		elRequirementBaseInfo.setName(name);
		UUID uuid = UUID.randomUUID();
		elRequirementBaseInfo.setId(uuid.toString().replace("-", ""));
		elRequirementBaseInfoService.saveRequirementName(elRequirementBaseInfo);
	} 
	

	
	/*
	 *查询待实现需求 
	 */
	@Valid
	@ApiOperation("查询待实现的需求")
	@RequestMapping(value = "/selectRequireUnfinished", produces = "application/json;charset=UTF-8")
	public int selectRequireUnfinished(ELRequirementBaseInfo requirementBaseInfo){
		return elRequirementBaseInfoService.selectRequireUnfinished(requirementBaseInfo);
	}
	
	
	/**----------------------------------------需求修改分割线---------------------------------------------------------*/
	 /**
	  * @描述（获取项目成员）
	  * @author 杨华勇
	  * @version 1.0
	  * @date 2017年5月15日 下午12:20:57
	  * @parameter （参数及其意义）
	  * @throws 异常类及抛出条件
	  * @return 返回值
	  */
	 @Valid
	 @ApiOperation(value = "获取项目成员")
	 @RequestMapping(value = "/getMemberById", produces = "application/json;charset=UTF-8")
	 public CommonResponse getMemberById(@ApiParam(required = true, value = "项目id") @RequestParam(required = true, value = "projectId", defaultValue = "") String projectId){
		return CommonResponse.createCommonResponse(elRequirementBaseInfoService.getMemberById(projectId))  ;
	 }
	
	/**
	  * @描述（获取需求详细信息）
	  * @author 杨华勇
	  * @version 1.0
	  * @date 2017年5月8日 上午11:14:35
	  * @parameter （参数及其意义）
	  * @throws 异常类及抛出条件
	  * @return 返回值
	  */
	 @Valid
	 @ApiOperation(value = "获取需求详细信息")
	 @RequestMapping(value = "/getRequirementInfoById", produces = "application/json;charset=UTF-8")
	 public CommonResponse getRequirementInfoById(
			 @ApiParam(required = true, value = "需求id") 
			 @RequestParam(required = true, value = "requirementId", defaultValue = "") String requirementId
			){
		 //ELUser user = super.getLoginUser();
		 //System.out.println(user);
		 CommonResponse response = new CommonResponse();
		 if(null == requirementId || StringUtils.isBlank(requirementId)){
				response.put("relust", "issueID 不能为空");
				response.put("relust", "fail");
				return response;
		 }
		 ELRequirementBaseInfo elRequirementBaseInfo = elRequirementBaseInfoService.getRequirementInfoById(requirementId);
		 if(elRequirementBaseInfo.getResponsibleIds() != null && elRequirementBaseInfo.getResponsibleIds().size() > 0){
			//批量查询责任人信息
			 elRequirementBaseInfo.setResponsibleList(elPeopleService.batchList((String[])elRequirementBaseInfo.getResponsibleIds().toArray(new String[elRequirementBaseInfo.getResponsibleIds().size()])));
		 }
		 
		 if(elRequirementBaseInfo.getLableIds() != null && elRequirementBaseInfo.getLableIds().size() > 0){
			//查询批量查询标签信息
			ELDict elDict = new ELDict();
			elDict.setType("sys_common_lable");
			List<ELDict> lablelist = dictService.findList(elDict);
			
			List<ELDict> labList = new ArrayList<ELDict>();
			if(null != elRequirementBaseInfo.getLableIds() && null != lablelist){
				for (String laId : elRequirementBaseInfo.getLableIds()) {
					for (ELDict lable : lablelist) {
						if(laId.equals(lable.getId())){
							labList.add(lable);
						}
					}
				}
			}
			elRequirementBaseInfo.setLableList(labList);
		 }
		 response.setData(elRequirementBaseInfo);
		 return response;
	 }
	 
	 /**
	  * @描述（需求添加成员）
	  * @author 杨华勇
	  * @version 1.0
	  * @date 2017年5月8日 上午11:14:35
	  * @parameter （参数及其意义）
	  * @throws 异常类及抛出条件
	  * @return 返回值
	  */
	 @Valid
	 @ApiOperation(value = "添加需求添加成员")
	 @RequestMapping(value = "/addMembers", produces = "application/json;charset=UTF-8")
	 public CommonResponse addMembers(
			 @ApiParam(required = true, value = "需求id") 
			 @RequestParam(required = true, value = "requirementId", defaultValue = "") String requirementId,
			 @ApiParam(required = true, value = "成员id") 
			 @RequestParam(required = true, value = "memberId", defaultValue = "") String memberId
			){
		 try {
			 elRequirementBaseInfoService.addMembers(memberId,requirementId);
		 } catch (Exception e) {
	       e.printStackTrace();
	       return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		 }
		 return CommonResponse.createCommonResponse();
	 }
	 
	 /**
	  * @描述（需求删除成员）
	  * @author 杨华勇 
	  * @version 1.0
	  * @date 2017年5月18日 上午8:36:43
	  * @throws 异常类及抛出条件
	  * @return 返回值
	  */
	 @Valid
	 @ApiOperation(value = "需求删除成员")
	 @RequestMapping(value = "/deleteMember", produces = "application/json;charset=UTF-8")
	 public CommonResponse deleteMember(
			 @ApiParam(required = true, value = "需求id") 
			 @RequestParam(required = true, value = "requirementId", defaultValue = "") String requirementId,
			 @ApiParam(required = true, value = "成员id") 
			 @RequestParam(required = true, value = "memberId", defaultValue = "") String memberId
			 ){
		 try {
			 elRequirementBaseInfoService.deleteMember(memberId, requirementId);
		 } catch (Exception e) {
	       e.printStackTrace();
	       return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		 }
		 return CommonResponse.createCommonResponse();
	 }
	 
	 /**
	  * @描述（需求添加标签）
	  * @author 杨华勇
	  * @version 1.0
	  * @date 2017年5月8日 上午11:14:35
	  * @parameter （参数及其意义）
	  * @throws 异常类及抛出条件
	  * @return 返回值
	  */
	 @Valid
	 @ApiOperation(value = "需求添加标签")
	 @RequestMapping(value = "/addLableRelation", produces = "application/json;charset=UTF-8")
	 public CommonResponse addLableRelation(
			 @ApiParam(required = true, value = "需求id") 
			 @RequestParam(required = true, value = "requirementId", defaultValue = "") String requirementId,
			 @ApiParam(required = true, value = "标签id") 
			 @RequestParam(required = true, value = "lableId", defaultValue = "") String lableId
			){
		 try {
			 elRequirementBaseInfoService.addLableRelation(lableId,requirementId);
		 } catch (Exception e) {
	       e.printStackTrace();
	       return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		 }
		 return CommonResponse.createCommonResponse();
	 }
	 
	 /**
	  * @描述（需求删除标签）
	  * @author 杨华勇
	  * @version 1.0
	  * @date 2017年5月8日 上午11:14:35
	  * @parameter （参数及其意义）
	  * @throws 异常类及抛出条件
	  * @return 返回值
	  */
	 @Valid
	 @ApiOperation(value = "需求删除标签")
	 @RequestMapping(value = "/deleteLableRelation", produces = "application/json;charset=UTF-8")
	 public CommonResponse deleteLableRelation(
			 @ApiParam(required = true, value = "需求id") 
			 @RequestParam(required = true, value = "requirementId", defaultValue = "") String requirementId,
			 @ApiParam(required = true, value = "标签id") 
			 @RequestParam(required = true, value = "lableId", defaultValue = "") String lableId
			 ){
		 try {
			 elRequirementBaseInfoService.deleteLableRelation(lableId,requirementId);
		 } catch (Exception e) {
	       e.printStackTrace();
	       return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		 }
		 return CommonResponse.createCommonResponse();
	 }
	 
	 /*
	  * 添加/修改工作量
	  */
	 @Valid
	 @ApiOperation(value = "添加工作量")
	 @RequestMapping(value = "/addIssueWorkload", produces = "application/json;charset=UTF-8")
	 public CommonResponse addIssueWorkload(
			 @ApiParam(required = true, value = "需求id") 
			 @RequestParam(required = true, value = "requirementId", defaultValue = "") String requirementId,
			 @ApiParam(required = true, value = "成员id") 
			 @RequestParam(required = true, value = "workload", defaultValue = "") String workload
			){
		 try {
			 elRequirementBaseInfoService.addIssueWorkload(workload,requirementId);
		 } catch (Exception e) {
	       e.printStackTrace();
	       return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		 }
		 return CommonResponse.createCommonResponse();
	 }
	 
	 
	 /**
	  * @描述（需求添加检查项）
	  * @author 杨华勇
	  * @version 1.0
	  * @date 2017年5月18日 上午11:14:35
	  * @parameter （参数及其意义）
	  * @throws 异常类及抛出条件
	  * @return 返回值
	  */
	 @Valid
	 @ApiOperation(value = "徐需求添加检查项")
	 @RequestMapping(value = "/addCommonCheckItem", produces = "application/json;charset=UTF-8")
	 public CommonResponse addCommonCheckItem(
			 @ApiParam(required = true, value = "需求id") 
			 @RequestParam(required = true, value = "requirementId", defaultValue = "") String requirementId,
			 @ApiParam(required = true, value = "项目id") 
			 @RequestParam(required = true, value = "projectId", defaultValue = "") String projectId,
			 @ApiParam(required = true, value = "名称") 
			 @RequestParam(required = true, value = "name", defaultValue = "") String name,
			 @ApiParam(required = true, value = "描述") 
			 @RequestParam(required = true, value = "description", defaultValue = "") String description
			 ){
		 CommonCheckItem checkItem = new CommonCheckItem();
		 try {
			 checkItem.setName(name);;
			 checkItem.setProjectId(projectId);
			 checkItem.setOwnerId(requirementId);
			 checkItem = elRequirementBaseInfoService.addCommonCheckItem(checkItem);
		 } catch (Exception e) {
	       e.printStackTrace();
	       return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		 }
		 return CommonResponse.createCommonResponse(checkItem);
	 }
	 
	 /**
	  * @描述（需求软删除检查项）
	  * @author 杨华勇
	  * @version 1.0
	  * @date 2017年5月19日 上午11:14:35
	  * @parameter （参数及其意义）
	  * @throws 异常类及抛出条件
	  * @return 返回值
	  */
	 @Valid
	 @ApiOperation(value = "需求软删除检查项")
	 @RequestMapping(value = "/deleteCommonCheckItem", produces = "application/json;charset=UTF-8")
	 public CommonResponse deleteCommonCheckItem(
			 @ApiParam(required = true, value = "需求id") 
			 @RequestParam(required = true, value = "requirementId", defaultValue = "") String requirementId,
			 @ApiParam(required = true, value = "检查项id") 
			 @RequestParam(required = true, value = "checkItemId", defaultValue = "") String checkItemId
			 ){
		 try {
			 elRequirementBaseInfoService.deleteCommonCheckItem(requirementId, checkItemId);
		 } catch (Exception e) {
	       e.printStackTrace();
	       return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		 }
		 return CommonResponse.createCommonResponse();
	 }
	 
	 /**
	  * @描述（需求修改检查项）
	  * @author 杨华勇
	  * @version 1.0
	  * @date 2017年5月19日 上午11:14:35
	  * @parameter （参数及其意义）
	  * @throws 异常类及抛出条件
	  * @return 返回值
	  */
	 @Valid
	 @ApiOperation(value = "需求修改检查项")
	 @RequestMapping(value = "/updateCommonCheckItem", produces = "application/json;charset=UTF-8")
	 public CommonResponse updateCommonCheckItem(
			 @ApiParam(required = true, value = "需求id") 
			 @RequestParam(required = true, value = "requirementId", defaultValue = "") String requirementId,
			 @ApiParam(required = true, value = "检查项id") 
			 @RequestParam(required = true, value = "checkItemId", defaultValue = "") String checkItemId,
			 @ApiParam(required = true, value = "检查项状态") 
			 @RequestParam(required = true, value = "state", defaultValue = "") String state
			 ){
		 try {
			 elRequirementBaseInfoService.updateCommonCheckItem(requirementId,checkItemId,state);
		 } catch (Exception e) {
	       e.printStackTrace();
	       return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		 }
		 return CommonResponse.createCommonResponse();
	 }
	 
	 /**
	 * 编辑页面修改数据接口
	 */
	@ApiOperation(value = "需求基础信息修改")
	@RequestMapping("/editData2")
	public CommonResponse editData2(ELRequirementBaseInfo elRequirementBaseInfo){
		try {
			if(StringUtil.isNotEmpty(elRequirementBaseInfo.getEndDateStr()))
			{
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sf.parse(elRequirementBaseInfo.getEndDateStr());
				elRequirementBaseInfo.setEndDate(date);
			}
			elRequirementBaseInfoService.editData2(elRequirementBaseInfo);
		} catch (Exception e) {
	       e.printStackTrace();
	       return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		 }
		 return CommonResponse.createCommonResponse();
	}
	
	/**
	 * 获取需求列表
	 * 
	 * @param issueId
	 * @param projectId
	 */
	@Valid
	@GetMapping(value = "/getRequirementList", produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "分页获取问题列表")
	public CommonResponse getIssueList(
			@ApiParam(required = true, value = "第几页") @RequestParam(required = true, value = "page", defaultValue = "1") String pageSize,
			@ApiParam(required = true, value = "排序方式(ASC或DESC)") @RequestParam(required = true, value = "order", defaultValue = "desc") String order,
			@ApiParam(required = true, value = "分页数量") @RequestParam(required = true, value = "rows", defaultValue = "10") String rows,
			@ApiParam(required = true, value = "排序字段(field值)") @RequestParam(required = true, value = "sort", defaultValue = "") String sort,
			@ApiParam(required = true, value = "项目id") @RequestParam(required = true, value = "projectId", defaultValue = "") String projectId,
			@ApiParam(required = false, value = "搜索_查看类型") @RequestParam(required = false, value = "selType", defaultValue = "") String selType,
			@ApiParam(required = false, value = "搜索_查看类型值") @RequestParam(required = false, value = "selVal", defaultValue = "") String selVal,
			@ApiParam(required = false, value = "搜索_模糊匹配值") @RequestParam(required = false, value = "keywords", defaultValue = "") String keywords,
			@ApiParam(required = false, value = "带实现需求标识") @RequestParam(required = false, value = "condition", defaultValue = "") String condition
			) {
		CommonResponse response = new CommonResponse();
		try {
			if(null == projectId || StringUtils.isBlank(projectId)){
				response.put("relust", "projectId 不能为空");
				response.put("relust", "fail");
				return response;
			}
			List<ELRequirementBaseInfo> list = new ArrayList<ELRequirementBaseInfo>();
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("selType", selType);
			parameter.put("selVal", selVal);
			if("submitter".equals(selType)){
				parameter.put("selVal", CommonUtils.strToSet(selVal));
			}
			parameter.put("keywords", keywords);
			parameter.put("projectId", projectId);
			parameter.put("condition", condition);
			
			List<String> requirementIds = new ArrayList<String>();
			List<String> ids  = StringUtil.isNotEmpty(selVal)?Arrays.asList(selVal.split(",")):new ArrayList<String>();
			if("responsible".equals(selType) && ids.size() > 0){
				requirementIds = elRequirementBaseInfoService.getRequirementIdsByResponsibleIds(ids);
				if(requirementIds.size() == 0){
					response.put("total",0);
					response.put("rows", list);
					return response;
				}
			}
			if("lable".equals(selType) && ids.size() > 0){
				requirementIds = elRequirementBaseInfoService.getRequirementIdsByLableIds(ids);
				if(requirementIds.size() == 0){
					response.put("total",0);
					response.put("rows", list);
					return response;
				}
			}
			parameter.put("requirementIds", requirementIds);
			
			int total = elRequirementBaseInfoService.getRequirementBaseinfoListCount(parameter);
			Pager page = new Pager(Integer.valueOf(pageSize),Integer.valueOf(rows),total);

			if(!StringUtils.isBlank(sort) && !"firstSort".equals(sort)){
				page.setSort("b."+sort);
				page.setOrder(order);
				if("plannedEndTimeString".equals(sort)){
					page.setSort("b.planned_end_time");
				}
				if("createtimeString".equals(sort)){
					page.setSort("b.createtime");
				}
			}else{
				page.setSort(null);
			}
			parameter.put("page", page);
			
			list = elRequirementBaseInfoService.getRequirementBaseinfoList(parameter);
			if(null != list && list.size() > 0){
				Set<String> lableIds = new HashSet<String>();
				Set<String> responsibleIds = new HashSet<String>();
				Set<String> requirementListIds = new HashSet<String>();
				for (ELRequirementBaseInfo elRequirementBaseInfo : list) {
					lableIds.addAll(elRequirementBaseInfo.getLableIds());
					responsibleIds.addAll(elRequirementBaseInfo.getResponsibleIds());
					requirementListIds.add(elRequirementBaseInfo.getId());
				}
				
				List<ELUser> responsibleList = new ArrayList<ELUser>();
				List<ELDict> lablelist = new ArrayList<ELDict>();
				List<ELTaskBaseInfo> taskList = new ArrayList<ELTaskBaseInfo>();
				if(responsibleIds.size() > 0){
					//批量查询责任人信息
					responsibleList = elPeopleService.batchList((String[])responsibleIds.toArray(new String[responsibleIds.size()])); //elPeopleService.getUser("");
				}
				if(lableIds.size() > 0){
					//查询批量查询标签信息
					ELDict elDict = new ELDict();
					elDict.setType("sys_common_lable");
					lablelist = dictService.findList(elDict);
				}
				//查询任务
				if(requirementListIds.size() > 0){
					taskList = elTaskBaseInfoService.getTasksBySourceObjIds(projectId, requirementListIds, "requirement");
				}
				
				for (ELRequirementBaseInfo elRequirementBaseInfo : list) {
					List<String> reIds = elRequirementBaseInfo.getResponsibleIds();
					List<ELUser> userList = new ArrayList<ELUser>();
					List<ELTaskBaseInfo> elRequirementTaskList = new ArrayList<ELTaskBaseInfo>();
					
					List<String> laIds = elRequirementBaseInfo.getLableIds();
					List<ELDict> labList = new ArrayList<ELDict>();
					
					if(null != reIds && null != responsibleList){
						for (String reId : reIds) {
							for (ELUser user : responsibleList) {
								if(reId.equals(user.getId())){
									userList.add(user);
								}
							}
						}
					}
					
					if(null != laIds && null != lablelist){
						for (String laId : laIds) {
							for (ELDict lable : lablelist) {
								if(laId.equals(lable.getId())){
									labList.add(lable);
								}
							}
						}
					}
					
					//任务
					for (ELTaskBaseInfo elTaskBaseInfo : taskList) {
						if(elRequirementBaseInfo.getId().equals(elTaskBaseInfo.getSourceObjId())){
							elRequirementTaskList.add(elTaskBaseInfo);
						}
					}
					
					elRequirementBaseInfo.setResponsibleList(userList);
					elRequirementBaseInfo.setLableList(labList);
					elRequirementBaseInfo.setTaskList(elRequirementTaskList);
				}
			}
			response.put("total",total);
			response.put("rows", list);
		} catch (Exception e) {
			response.put("relust", "fail");
			logger.error("获取问题列表信息出错");
			return CommonResponse.createExceptionCommonResponse(e);
		}
		return response;
	}
	
}
