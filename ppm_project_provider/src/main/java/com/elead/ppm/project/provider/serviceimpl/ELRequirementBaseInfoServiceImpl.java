/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.elead.ppm.project.provider.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elead.platform.common.service.service.CrudService;
import com.elead.ppm.project.domain.entity.CommonCheckItem;
import com.elead.ppm.project.domain.entity.ELCommonCheckList;
import com.elead.ppm.project.domain.entity.ELProjectMemberBaseInfo;
import com.elead.ppm.project.domain.entity.ELRequirementBaseInfo;
import com.elead.ppm.project.domain.entity.ELRequirementLabelRelation;
import com.elead.ppm.project.domain.entity.ELTaskBaseInfo;
import com.elead.ppm.project.domain.service.ELRequirementBaseInfoService;
import com.elead.ppm.project.provider.dao.ELCommonAttachmentDao;
import com.elead.ppm.project.provider.dao.ELCommonCheckListDao;
import com.elead.ppm.project.provider.dao.ELCommonCommentDao;
import com.elead.ppm.project.provider.dao.ELProjectMemberBaseInfoDao;
import com.elead.ppm.project.provider.dao.ELRequirementBaseInfoDao;
import com.elead.ppm.project.provider.dao.ELRequirementLabelRelationDao;
import com.elead.ppm.project.provider.dao.ELTaskBaseInfoDao;


/**
 * 项目需求的service实现类
 */
@Service(value = "elRequirementBaseInfoService")
@Transactional(readOnly=false)
public class ELRequirementBaseInfoServiceImpl extends 
	CrudService <ELRequirementBaseInfoDao, ELRequirementBaseInfo> implements ELRequirementBaseInfoService {
	
	@Autowired
	private ELRequirementBaseInfoDao requirementDao;
	
	@Autowired
	private ELRequirementLabelRelationDao labelDao;
	
	@Autowired
	private ELCommonCheckListDao checkDao;
	
	@Autowired
	private ELTaskBaseInfoDao taskDao;
	
	@Autowired
	private ELProjectMemberBaseInfoDao memberDao;
	
	@Autowired
	private ELCommonAttachmentDao attachmentDao;
	
	@Autowired
	private ELCommonCommentDao commentDao;
	
	
	/**
	 * 加载需求列表
	 */
	public Map<String,Object> getRequirementInfo(String projectId,int pageNum,int pageSize,String sort, String order,
			String userId){
		
		//需求总数
		int total = requirementDao.queryTotal(projectId, "", "");
		
		int startIndex = (pageNum-1) * pageSize;
		//查询项目需求对象列表
		List<ELRequirementBaseInfo> requirementList = 
				requirementDao.findRequirementsByProjectId(projectId,startIndex,pageSize,sort,order);
		
		return getRequirementInfoUtil(requirementList, total, userId);
	}
	

	
	/**
	 * 搜索、筛选需求
	 */
	public Map<String,Object> search(
			String projectId, String searchType, String searchVal, int pageNum,
			int pageSize, String sort, String order, String userId){
		
		//需求总数
		int total = 0;
		
		int startIndex = (pageNum-1) * pageSize;
		
		//需求集合
		List<ELRequirementBaseInfo> requirementList = new ArrayList<ELRequirementBaseInfo>();
		
		if("name".equals(searchType)){ //根据需求名搜索
			//模糊查询
			searchVal = "%"+searchVal+"%";
			
			total = requirementDao.queryTotal(projectId, "name", searchVal);
			
			requirementList = requirementDao.findRequirementsByName
					(projectId, startIndex, pageSize, searchVal, sort, order);
			
//		}else if("lable_id".equals(searchType)){ //标签id筛选
//			
//			requirementList = requirementDao.getRequirmentByLabelId
//					(projectId, startIndex, pageSize, searchVal, sort, order);
			
		}else{  //需求状态/优先级/提出人筛选
			total = requirementDao.queryTotal(projectId, searchType, searchVal);
			
			requirementList = requirementDao.screenRequirement
				(projectId, startIndex, pageSize, searchType, searchVal, sort, order);
		}
		
		return getRequirementInfoUtil(requirementList, total, userId);
	}
	
	
	/**
	 * 模糊匹配提出人、标签
	 * @param projectId
	 * @param name
	 * @return
	 */
	public String match(String projectId, String matchType, String matchName){
		JSONArray jsons = new JSONArray();
		
		matchName = "%"+matchName+"%";
		 
		if("submitter_name".equals(matchType)){  //匹配提出人
			List<ELRequirementBaseInfo> list = requirementDao.getSubmitterByName(projectId, matchName);
			for(ELRequirementBaseInfo re : list){
				JSONObject json = new JSONObject();
				try {
					json.put("id", re.getSubmitterId());
					json.put("text", re.getSubmitterName());
					
					jsons.put(json);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		
		return jsons.toString();
	}
	
	
	
	/**
	 * 新建需求
	 * @param projectId  项目ID
	 * @param name 需求名
	 * @param createId  创建人id
	 */
	@Transactional(readOnly=false)
	public int addRequirement(ELRequirementBaseInfo requireInfo){
		requireInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		return requirementDao.addRequirement(requireInfo);
	}
	
	
	/**
	 * 根据需求id获取需求的相关信息
	 * @param requirementId 需求id
	 * @return
	 */
	public Map<String, Object> getInfoByRequirementId(String requirementId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			ELRequirementBaseInfo requirement = requirementDao.getRequiremenById(requirementId);
			
			map.put("requirement", requirement);
			map.put("labels", labelDao.findLabelByRequirementId(requirementId));
			map.put("checks", checkDao.findCheckByRequirementId(requirementId));
			
			Map<String, Object> parMap = new HashMap<String, Object>();
			parMap.put("projectId", requirement.getProjectId());
			parMap.put("sourceObjId", requirement.getId());
			parMap.put("sourceObjName", "requirement");
			
			map.put("tasks", taskDao.findTaskByRequirementId(parMap));
			map.put("members", memberDao.findMemberByRequirementId(requirementId));
			//map.put("comments", commentDao.getCommentsByTypeAndOwnerId("需求", requirementId));
			//map.put("attachments", attachmentDao.getAttachmentsByTypeAndOwnerId("需求", requirementId));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	/**
	 * 更改需求数据
	 * @param requirement
	 */
	@Transactional(readOnly=false)
	public void editData(ELRequirementBaseInfo requirement){
		System.out.println("打桩2");
		System.out.println(requirement);
		System.out.println(requirement.getId());
		System.out.println(requirement.getName());
		requirementDao.updateByPrimaryKeySelective(requirement);
	}
	
	
	
	/**
	 * 工具类 --- 根据需求查询任务、标签、检查项、参与人，并将需求列表所需数据打包返回；
	 * @param requirementList 需求对象集合
	 * @param requirementSize 需求总数
	 * @return
	 */
	public Map<String,Object> getRequirementInfoUtil
		(List<ELRequirementBaseInfo> requirementList, int total, String userId){
		
		//结果集
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//存放数据
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		//遍历需求集合，查询需求相关的任务、标签、检查项、参与人等信息；
		for(ELRequirementBaseInfo requirement : requirementList){
			
			//存放一条需求列表的一条记录
			Map<String,Object> map = new HashMap<String,Object>();
			
			//需求名
			if(requirement.getName() == null){
				map.put("name", ""); 
			}else{
				map.put("name", requirement.getName()); 
			}
			
			//需求描述
			if(requirement.getDescription() == null){
				map.put("description", "");
			}else{
				map.put("description", requirement.getDescription());
			}
			
			//需求状态
			if(requirement.getState() == null){
				map.put("state", "");
			}else{
				switch (requirement.getState()) {
				case "1":
					map.put("state", "已过期");  
					break;
				case "2":
					map.put("state", "待分析");  
					break;
				case "3":
					map.put("state", "待实现");  
					break;
				case "4":
					map.put("state", "已实现");  
					break;
				default:
					break;
				}
			}
			
			//优先级
			if(requirement.getPriority() == null){
				map.put("priority", "");
			}else{
				Byte priority = requirement.getPriority();
				String priorityStr = "";
				
				if(priority == 1){
					priorityStr = "低";
				}else if(priority == 2){
					priorityStr = "中";
				}else if(priority == 3){
					priorityStr = "高";
				}
				map.put("priority", priorityStr); 
			}
			
			//工作量
			if(requirement.getWorkload() == null){
				map.put("workload", "");
			}else{
				map.put("workload", requirement.getWorkload()+" 天");
			}
			
			//提出人
			if(requirement.getSubmitterName() == null){
				map.put("submitter_name", "");
			}else{
				map.put("submitter_name", requirement.getSubmitterName());
			}

			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			//创建日期
			if(requirement.getCreateTimestamp() == null){
				map.put("createtime","");
			}else{
				map.put("createtime", sdf.format(requirement.getCreateTimestamp())); 
			}
			
			//截止日期
			if(requirement.getEndDate() == null){
				map.put("end_date", "");
			}else{
				map.put("end_date", sdf.format(requirement.getEndDate())); 
			}
			
			Map<String, Object> parmap = new HashMap<String, Object>();
			parmap.put("projectId", requirement.getProjectId());
			parmap.put("sourceObjId", requirement.getId());
			parmap.put("sourceObjName", "requirement");
			//任务
			List<ELTaskBaseInfo> tasks = taskDao.findTaskByRequirementId(parmap);
			if(tasks.size() == 0){
				map.put("task", "");
			}else{
				String taskStr = "";
				for(ELTaskBaseInfo task : tasks){//任务 状态为：未完成和完成
					if("0".equals(task.getState())){
						taskStr += "<input type='checkbox' />" + task.getName() + "&nbsp&nbsp";
					}else if("1".equals(task.getState())){ //选中状态
						taskStr += "<input type='checkbox' checked='true' />" + task.getName() + "&nbsp&nbsp";
					}
				}
				map.put("task", taskStr); 
			}
			
			
			//标签
			List<ELRequirementLabelRelation> labels = labelDao.findLabelByRequirementId(requirement.getId());
			if(labels.size() == 0){
				map.put("label", "");
			}else{
				String labelStr = "";
				for(ELRequirementLabelRelation label : labels){
					labelStr += label.getLableId() + "&nbsp&nbsp";
				}
				map.put("label", labelStr);
			}
			
			//检查项
			List<ELCommonCheckList> checks = checkDao.findCheckByRequirementId(requirement.getId());
			if(checks.size() == 0){
				map.put("check", "");
			}else{
				String checkStr = "";
				for(ELCommonCheckList check : checks){//检查项状态为：未完成；完成
					if(check.getState() == 0){//未完成
						checkStr += "<input type='checkbox' />" + check.getName() + "&nbsp&nbsp";
					}else if(check.getState() == 1){ //完成
						checkStr += "<input type='checkbox'checked='true' />" + check.getName() + "&nbsp&nbsp";
					}
				}
				map.put("check", checkStr);
			}
			
			//标记与自己相关的需求(默认为false,  true表示相关)
			boolean relatedRequirement = false;
			
			//参与人
			List<ELProjectMemberBaseInfo> members = memberDao.findMemberByRequirementId(requirement.getId());
			if(members.size() == 0){
				map.put("member", "未指派");
			}else{
				String memberStr = "";
				for(ELProjectMemberBaseInfo member : members){
					memberStr += member.getName() + "&nbsp&nbsp";
					
					if(userId.equals(member.getMemberId())){
						relatedRequirement = true;
					}
					
				}
				map.put("member", memberStr);
			}
			
			//操作
			String requirementId = '"' + requirement.getId() + '"';
			map.put("operate", "<a href='javascript:void(0)' onclick='edit("+ requirementId +");'>编辑</a>");
			
			//相关需求
			map.put("related", relatedRequirement);
			
			list.add(map);
		}
		
		resultMap.put("total", total); //需求总数
		resultMap.put("rows", list);  //需求列表数据
		
		return resultMap;
		
	}
	
	

	@Override
	public void saveRequirementName(ELRequirementBaseInfo elRequirementBaseInfo) {
		// TODO Auto-generated method stub
		super.insert(elRequirementBaseInfo);
	}


	/**
	 * 查询待实现需求
	 */
	@Override
	public int selectRequireUnfinished(ELRequirementBaseInfo requirementBaseInfo) {
		return requirementDao.selectRequireUnfinished(requirementBaseInfo);
	}
	
	/****************************************************************************************************/
	/**
	 * 获取项目关联的成员
	 */
	@Override
    public List<Map> getMemberById(String projectId) {
	    return requirementDao.getMemberById(projectId);
    }
	
	/**
	 * 获取问题详细
	 * @param issueId
	 */
	@Override
	public ELRequirementBaseInfo getRequirementInfoById(String requirementId) {
	    return requirementDao.getRequirementInfoById(requirementId);
	}
	
	/**
	 * 批量插入成员
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
    public void addMembers(String members,String requirementId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String[] lablearr = members.split(";");
		for(String responsibleid : lablearr){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
			map.put("requirementId", requirementId);
			map.put("responsibleid", responsibleid);
			list.add(map);
		}
		requirementDao.addMembers(list);
    }
	
	/**
	 * 删除问题成员
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
    public void deleteMember(String memberId, String requirementId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("requirementId", requirementId);
		map.put("responsibleid", memberId);
		requirementDao.deleteMember(map);
    }
	
	/**
	 * 删除标签
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
    public void deleteLableRelation(String lableId, String requirementId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("requirementId", requirementId);
		map.put("lableId", lableId);
		requirementDao.deleteLableRelation(map);
    }

	/**
	 * 批量插入标签
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
    public void addLableRelation(String lables,String requirementId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String[] lablearr = lables.split(";");
		for(String lableid : lablearr){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
			map.put("requirementId", requirementId);
			map.put("lableId", lableid);
			list.add(map);
		}
		requirementDao.addLableRelation(list);
    }
	
	/**
	 * 添加工作量
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
	public void addIssueWorkload(String workload,String requirementId){
		ELRequirementBaseInfo requirementBaseInfo = new ELRequirementBaseInfo();
		requirementBaseInfo.setId(requirementId);
		requirementBaseInfo.setWorkload(Float.valueOf(workload));
		requirementDao.updateIssueBaseInfo(requirementBaseInfo);
	}
	
	/**
	 * 添加检查项
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
    public CommonCheckItem addCommonCheckItem(CommonCheckItem checkItem) {
		checkItem.setIsNewRecord(true);
		checkItem.preInsert();
		checkItem.setCheckType("4");//（计划检查项[1];任务检查项[2];问题检查项[3];需求检查项[4];风险检查项[5]）
		checkItem.setCreateName(""); //创建人姓名
		checkItem.setState(0);// default[0];完成[1];关闭[2]
		requirementDao.addCommonCheckItem(checkItem); //保存操作
		return checkItem;
    }
	
	/**
	 * 软删除检查项
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
    public void deleteCommonCheckItem(String requirementId, String checkItemId) {
		CommonCheckItem checkItem = new CommonCheckItem();
		checkItem.setOwnerId(requirementId);
		checkItem.setDelFlag("1");//删除标记（0：正常；1：删除；2：审核）
		checkItem.setId(checkItemId);//检查项id
		requirementDao.deleteCommonCheckItem(checkItem);
    }
	
	/**
	 * 软删除检查项
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
	public void updateCommonCheckItem(String requirementId,String checkItemId,String state) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("requirementId", requirementId);
		map.put("id", checkItemId);
		map.put("state", state);
		requirementDao.updateCommonCheckItem(map);
	}
	
	/**
	 * 修改计划
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
    public void editData2(ELRequirementBaseInfo elRequirementBaseInfo) {
		requirementDao.updateByPrimaryKeySelective(elRequirementBaseInfo);
    }
	
	/**
	 * 查询问题信息list
	 * @param ELProjectBaseInfo
	 */
	public List<ELRequirementBaseInfo> getRequirementBaseinfoList(Map<String, Object> parameter){
		List<ELRequirementBaseInfo> list = requirementDao.getRequirementBaseinfoList(parameter);
		if(null != list && list.size() > 0){
			for (ELRequirementBaseInfo elRequirementBaseInfo : list) {
				switch (elRequirementBaseInfo.getState()) {
				case "1":
					elRequirementBaseInfo.setState("<font color=\"red\">已过期</font>");
					break;
				case "2":
					elRequirementBaseInfo.setState("<font color=\"#7fff00\">待分析</font>");
					break;
				case "3":
					elRequirementBaseInfo.setState("<font color=\"#7fff00\">待实现</font>");
					break;
				case "4":
					elRequirementBaseInfo.setState("<font color=\"blue\">已实现</font>");
					break;
				default:
					elRequirementBaseInfo.setState("<font color=\"#7fff00\">待分析</font>");
					break;
				}
				
				elRequirementBaseInfo.setOperate("<a href=\"javascript:void(0)\" onclick=\"edit('"+elRequirementBaseInfo.getId()+"')\">编辑</a>");
			}
		}
		return list;
	}
	
	/**
	 * 查询问题信息list
	 * @param ELProjectBaseInfo
	 */
	public int getRequirementBaseinfoListCount(Map<String, Object> parameter){
		return requirementDao.getRequirementBaseinfoListCount(parameter);
	}
	
	/**
	 * 根据责任人id集合查询问题集合
	 * @param List<String>
	 */
	public List<String> getRequirementIdsByResponsibleIds(List<String> ids){
		return requirementDao.getRequirementIdsByResponsibleIds(ids);
	}
	
	/**
	 * 根据标签id集合查询问题集合
	 * @param List<String>
	 */
	public List<String> getRequirementIdsByLableIds(List<String> ids){
		return requirementDao.getRequirementIdsByLableIds(ids);
	}
	
}