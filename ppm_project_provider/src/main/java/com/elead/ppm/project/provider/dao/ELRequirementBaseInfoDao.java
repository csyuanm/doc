package com.elead.ppm.project.provider.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.elead.platform.common.service.dao.CrudDao;
import com.elead.ppm.project.domain.entity.CommonCheckItem;
import com.elead.ppm.project.domain.entity.ELRequirementBaseInfo;

/**
 * 项目需求Dao接口
 * @author Administrator
 *
 */
@Mapper
@Repository
public interface ELRequirementBaseInfoDao extends CrudDao<ELRequirementBaseInfo>{
	
	/**
	 * 根据项目id查询需求列表
	 */
	List<ELRequirementBaseInfo> findRequirementsByProjectId(
			@Param("projectId")String projectId, @Param("startIndex")int startIndex, 
			@Param("pageSize")int pageSize,@Param("sort")String sort, 
			@Param("order")String order);
	
	
	/**
	 * 根据需求名模糊查询需求列表
	 */
	List<ELRequirementBaseInfo> findRequirementsByName(
			@Param("projectId")String projectId, @Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize,@Param("keyWord")String keyWord,
			@Param("sort")String sort, @Param("order")String order);
	
	
	/**
	 * 根据筛选条件和筛选值查询需求列表
	 * @param selectCondition 筛选条件
	 * @param selectVal 筛选值
	 * @return
	 */
	List<ELRequirementBaseInfo> screenRequirement(
			@Param("projectId")String projectId, @Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize,@Param("selectCondition")String selectCondition, 
			@Param("selectVal")String selectVal,@Param("sort")String sort, 
			@Param("order")String order);
	
	
	/**
	 * 根据项目id查询需求总数
	 * @param projectId 项目id
	 * @param type
	 * @param value
	 * @return
	 */
	int queryTotal(@Param("projectId")String projectId, 
			@Param("type")String type, @Param("value")String value);
	
	
	/**
	 * 根据输入内容模糊匹配提出人
	 * @param projectId
	 * @param name
	 * @return
	 */
	List<ELRequirementBaseInfo> getSubmitterByName
		(@Param("projectId")String projectId, @Param("name")String name);
	
	/**
	 * 根据标签id搜索需求
	 * @param projectId
	 * @param startIndex
	 * @param pageSize
	 * @param lebelId
	 * @param sort
	 * @param order
	 * @return
	 */
	List<ELRequirementBaseInfo> getRequirmentByLabelId(
			@Param("projectId")String projectId, @Param("startIndex")int startIndex,@Param("pageSize")int pageSize,
			@Param("labelId")String labelId,@Param("sort")String sort, @Param("order")String order
		);
	
	/**
	 * 新建需求
	 * @param requirement
	 */
	int addRequirement(ELRequirementBaseInfo requirement);
	
	
	/**
	 * 根据需求id查询需求对象
	 * @param requirementId
	 */
	ELRequirementBaseInfo getRequiremenById(String requirementId);
	
	
	/**
	 * 根据需求对象更新数据
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(ELRequirementBaseInfo record);
	
	/**
	 * 查询待实现需求
	 * @author 蒙茂良
	 * 创建时间：2017年4月25日  下午6:04:25
	 * version 1.0
	 * @param requirementBaseInfo
	 * @return
	 */
	int selectRequireUnfinished(ELRequirementBaseInfo requirementBaseInfo);
	
	
	/*******************************************************************************/
	/**
	 * @描述（获取项目关联的成员）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月9日 上午11:56:26
	 * @parameter responsibleRelationInfoList 成员集合
	 */
	List<Map> getMemberById(String projectId);
	
	/**
	 * @描述（获取计划相信信息）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月10日 下午4:03:13
	 * @parameter issueId 需求id
	 */
	ELRequirementBaseInfo getRequirementInfoById(String requirementId);
	
	/**
	 * @描述（批量添加成员）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月9日 上午11:56:26
	 * @parameter responsibleRelationInfoList 成员集合
	 */
	void addMembers(List<Map<String, Object>> map) ;
	
	/**
	 * @描述（删除需求成员）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月9日 上午11:56:26
	 * @parameter responsibleRelationInfoList 成员集合
	 */
	void deleteMember(Map<String, Object> map);
	
	/**
	 * @描述（批量添加标签）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月9日 上午11:57:09
	 * @parameter map 标签集合
	 */
	void addLableRelation(List<Map<String, Object>> map);
	
	/**
	 * @描述（删除标签）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月9日 上午11:57:09
	 * @parameter map 标签中间表对象
	 */
	void deleteLableRelation(Map<String, Object> map);
	
	/**
	 * @描述（修改）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月9日 上午11:57:09
	 * @parameter issueBaseinfo
	 */
	void updateIssueBaseInfo(ELRequirementBaseInfo elRequirementBaseInfo);
	
	/**
	 * @描述（添加检查项）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月9日 上午11:57:47
	 * @parameter checkItem 检查项实体对象
	 */
	void addCommonCheckItem(CommonCheckItem checkItem);
	
	/**
	 * @描述（删除检查项）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月9日 上午11:57:47
	 * @parameter checkItem 检查项实体对象
	 */
	void deleteCommonCheckItem(CommonCheckItem checkItem);
	
	/**
	 * @描述（修改检查项）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月9日 上午11:57:47
	 * @parameter checkItem 检查项实体对象
	 */
	void updateCommonCheckItem(Map<String, Object> map);
	
	/**
	 * @描述（问题列表分页查询）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月17日 上午11:56:26
	 * @parameter projectId
	 */
	public List<ELRequirementBaseInfo> getRequirementBaseinfoList(Map<String, Object> parameter);
	
	/**
	 * @描述（问题列表数量）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月17日 上午11:56:26
	 * @parameter projectId
	 */
	public int getRequirementBaseinfoListCount(Map<String, Object> parameter);
	
	/**
	 * @描述（根据责任人查询问题id集合）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月17日 上午11:56:26
	 * @parameter projectId
	 */
	public List<String> getRequirementIdsByResponsibleIds(List<String> ids);
	
	/**
	 * @描述（根据标签查询问题id集合）
	 * @author 杨华勇
	 * @version 1.0
	 * @date 2017年5月17日 上午11:56:26
	 * @parameter projectId
	 */
	public List<String> getRequirementIdsByLableIds(List<String> ids);
	
}
