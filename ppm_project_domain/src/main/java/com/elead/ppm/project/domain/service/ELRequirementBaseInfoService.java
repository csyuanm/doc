package com.elead.ppm.project.domain.service;

import java.util.List;
import java.util.Map;
import com.elead.ppm.project.domain.entity.CommonCheckItem;
import com.elead.ppm.project.domain.entity.ELRequirementBaseInfo;

/**
 * 项目需求的service接口
 */
public interface ELRequirementBaseInfoService {
	
	/**
	 * 加载需求列表
	 * @param projectId
	 * @return
	 */
	public Map<String,Object> getRequirementInfo(String projectId,int pageNum,int pageSize,String sort, String order,
			String userId);
	
	
	/**
	 * 搜索、筛选需求
	 * @param selectCondition 筛选条件
	 * @param selectVal 筛选值
	 * @return
	 */
	public Map<String,Object> search(
			String projectId, String searchType, String searchVal, int pageNum,
			int pageSize, String sort, String order, String userId);
	
	
	/**
	 * 模糊匹配提出人、标签
	 * @param projectId
	 * @param name
	 * @return
	 */
	public String match(String projectId, String matchType, String matchName);
	
	
	/**
	 * 新建需求
	 * @param projectId  项目ID
	 * @param name 需求名
	 * @param createId  创建人id
	 */
	public int addRequirement(ELRequirementBaseInfo requireInfo);
	
	
	/**
	 * 根据需求id获取需求的相关信息
	 * @param requirementId 需求id
	 * @return
	 */
	public Map<String, Object> getInfoByRequirementId(String requirementId);
	
	
	/**
	 * 更改需求数据
	 * @param requirement
	 */
	public void editData(ELRequirementBaseInfo requirement);
	
	
	public void saveRequirementName(ELRequirementBaseInfo elRequirementBaseInfo);

	
//	public Page<ELUser> queryPage(Page<ELUser> page, ELUser user);
	
	/**
	 * 查询待实现需求
	 * @author 蒙茂良
	 * 创建时间：2017年4月25日  下午6:05:07
	 * version 1.0
	 * @param requirementBaseInfo
	 * @return
	 */
	int selectRequireUnfinished(ELRequirementBaseInfo requirementBaseInfo);
	
	/******************************************************************************************/
	/**
	 * @描述（获取项目成员）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月19日 下午12:02:55
	 * @parameter proectId 项目id
	 */
	
	List<Map> getMemberById(String proectId);
	
	/**
	 * @描述（获取计划相信信息）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月18日 下午4:03:13
	 * @parameter issueId 需求id
	 */
	ELRequirementBaseInfo getRequirementInfoById(String requirementId);
	
	/**
	 * @描述（需求添加成员）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月19日 下午12:02:55
	 * @parameter members 成员id ; issueId 需求id
	 */
	void addMembers(String members,String requirementId);
	
	/**
	 * @描述（需求删除成员）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月19日 下午12:02:55
	 * @parameter memberId 成员id  issueId 需求
	 */
	void deleteMember(String memberId,String requirementId);
	
	/**
	 * @描述（添加标签）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月19日 下午12:03:21
	 * @parameter lables 标签id ; planId 计划id
	 */
	void addLableRelation(String lables,String requirementId);
	
	/**
	 * @描述（删除标签）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月19日 下午12:03:21
	 * @parameter lables 标签id ; planId 计划id
	 */
	void deleteLableRelation(String lableId,String requirementId);
	
	/**
	 * @描述（添加工作量）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月19日 下午12:01:27
	 * @parameter workload 工作量 ; planId 计划id
	 */
	void addIssueWorkload(String workload,String requirementId);
	
	/**
	 * @描述（添加检查项）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月19日 下午12:04:56
	 * @parameter planId 计划id ; checkItemjson 检查项信息json
	 */
	CommonCheckItem addCommonCheckItem(CommonCheckItem checkItem);
	
	/**
	 * @描述（软删除检查项）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月19日 下午12:04:56
	 * @parameter planId 计划id ; checkItemId 检查项信息id
	 */
	void deleteCommonCheckItem(String requirementId,String checkItemId);
	
	/**
	 * @描述（修改检查项状态）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月19日 下午12:04:56
	 * @parameter issueId 需求id ; checkItemId 检查项信息id
	 */
	void updateCommonCheckItem(String requirementId,String checkItemId,String state);
	
	/**
	 * @描述（修改需求属性）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月19日 下午12:01:27
	 * @parameter IssueBaseinfo
	 */
	void editData2(ELRequirementBaseInfo elRequirementBaseInfo);
	
	/**
	 * 获取issue 列表
	 * @param issueId
	 * @param project
	 * @return List<IssueBaseinfo>
	 */
	List<ELRequirementBaseInfo> getRequirementBaseinfoList(Map<String, Object> parameter);
	
	/**
	 * 获取issue列表总数
	 * @param issueId
	 * @param project
	 * @return List<IssueBaseinfo>
	 */
	int getRequirementBaseinfoListCount(Map<String, Object> parameter);
	
	
	/**
	 * 根据责任人id集合查询问题集合
	 * @param List<String>
	 */
	List<String> getRequirementIdsByResponsibleIds(List<String> ids);
	
	/**
	 * 根据标签id集合查询问题集合
	 * @param List<String>
	 */
	List<String> getRequirementIdsByLableIds(List<String> ids);
	
//	/**
//	 * 根据projectId查询责任人Id集合
//	 * @param List<String>
//	 */
//	List<String> getResponsibleIdsByProjectId(String projectId);
//	
//	/**
//	 * 根据projectId查询标签Id集合
//	 * @param List<String>
//	 */
//	List<String> getLableIdsByProjectId(String projectId);
}
