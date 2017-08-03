/**
 * 
 */
package com.elead.ppm.project.consumer.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.elead.platform.common.api.CommonResponse;
import com.elead.platform.common.utils.CodeUtils;
import com.elead.platform.common.utils.JsonMapper;
import com.elead.platform.system.domain.entity.ELDict;
import com.elead.platform.system.domain.service.ELDictService;
import com.elead.ppm.project.domain.entity.ELCommonCodeRegulation;
import com.elead.ppm.project.domain.entity.ELProjectMember;
import com.elead.ppm.project.domain.entity.ELProjectMemberBaseInfo;
import com.elead.ppm.project.domain.service.ELProjectMemberBaseInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author mml
 * @version 创建时间：2017年5月2日  上午11:21:49
 */
@RestController
@Api(tags="项目成员")
@RequestMapping("/project/member")
public class ELProjectMemberBaseInfoController {
	
	@Autowired
	ELProjectMemberBaseInfoService elmemberService;
	
	@Autowired
	private ELDictService dictService;
	

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 成员显示
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:49:01
	 * version 1.0
	 * @param response
	 * @param memberInfo
	 * @return
	 */
	@ApiOperation("获取项目成员")
	@RequestMapping("projectMember")
	public String getProjectMember(@ApiParam(required=true,value="项目id")@RequestParam(value="projectId")@NotNull(message=CodeUtils.EMPTY_CODE)String projectId){
		try {
			ELProjectMemberBaseInfo memberInfo =new ELProjectMemberBaseInfo();
			memberInfo.setProjectId(projectId);
			List<ELProjectMemberBaseInfo> list = elmemberService.getProjectMember(memberInfo);
			return JsonMapper.toJsonString(CommonResponse.createCommonResponse(list));
		} catch (Exception e) {
	       e.printStackTrace();
	       return JsonMapper.toJsonString(CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage()));
		 }
	}
	
	/**
	 * 删除项目成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:48:02
	 * version 1.0
	 * @param response
	 * @param id（主id删除）
	 */
	@ApiOperation("删除项目成员")
	@RequestMapping("deleteProjectMember")
	public String deleteProjectMember(@ApiParam(required=true,value="成员主id")@RequestParam(value="id")@NotNull(message=CodeUtils.EMPTY_CODE)String id){
	
		try {
			elmemberService.deleteProjectMember(id);
			return JsonMapper.toJsonString(CommonResponse.createCommonResponse());
		} catch (Exception e) {
			logger.error("删除成员出错", e);
			return JsonMapper.toJsonString(CommonResponse.createExceptionCommonResponse(e));
		}
	}
	
	/**
	 * 查找项目成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:47:48
	 * version 1.0
	 * @param response
	 * @param projectId  
	 * @param name
	 * @return
	 */
	@ApiOperation("根据名字查找项目成员")
	@RequestMapping("searchMemberName")
	public String getMemberName(@ApiParam(required=true,value="项目id")@RequestParam(value="projectId")@NotNull(message=CodeUtils.EMPTY_CODE)String projectId, 
			@ApiParam(required=true,value="成员名字")@RequestParam(value="name")@NotNull(message=CodeUtils.EMPTY_CODE) String name){
		try {
			List<ELProjectMemberBaseInfo> list = elmemberService.getMemberByName(projectId, name);
			return JsonMapper.toJsonString(CommonResponse.createCommonResponse(list));
		} catch (Exception e) {
			logger.error("查找成员出错", e);
	       return JsonMapper.toJsonString(CommonResponse.createExceptionCommonResponse(e));
		 }
	}
	
	/**
	 * 添加成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:47:27
	 * version 1.0
	 * @param response
	 * @param memberInfo
	 * @return
	 */
	@ApiOperation("添加项目成员")
	@RequestMapping("addMember")
	public String addMember(@ApiParam(required=true,value="项目id")@RequestParam(value="projectId")@NotNull(message=CodeUtils.EMPTY_CODE)String projectId,
			@ApiParam(required=true,value="成员id")@RequestParam(value="memberId")@NotNull(message=CodeUtils.EMPTY_CODE)String memberId,
			@ApiParam(required=true,value="成员名字")@RequestParam(value="name")@NotNull(message=CodeUtils.EMPTY_CODE)String name) {
		ELProjectMemberBaseInfo memberInfo = new ELProjectMemberBaseInfo();
		memberInfo.setProjectId(projectId);
		memberInfo.setMemberId(memberId);
		memberInfo.setName(name);
		return	elmemberService.addMember(memberInfo);//已用CommonResponse转化
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
	@RequestMapping(value = "/getMemberRole", produces = "application/json;charset=UTF-8")
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
			//object.put("description", dict2.getDescription());
			object.put("label", dict2.getLabel());
			jsonArray.add(object);
		}
		return jsonArray;
	}
	
	/**
	 * 根据id修改成员角色
	 * @author 蒙茂良
	 * 创建时间：2017年5月24日  下午5:46:39
	 * version 1.0
	 * @param id 成员id
	 * @param roleId  角色id
	 */
	@ApiOperation("根据名字查找项目成员")
	@RequestMapping(value = "/updateRole", produces = "application/json;charset=UTF-8")
	public CommonResponse updateRole(@ApiParam(required=true,value="成员id")@RequestParam(value="id")@NotNull(message=CodeUtils.EMPTY_CODE)String id,
			@ApiParam(required=true,value="成员角色")@RequestParam(value="roleId")@NotNull(message=CodeUtils.EMPTY_CODE)String roleId) {
		
		try {
			elmemberService.updateRoleById(id, roleId);
			return CommonResponse.createCommonResponse();
		} catch (Exception e) {
			logger.error("修改成员角色出错", e);
	       return CommonResponse.createExceptionCommonResponse(e);
		 }
	}
}
