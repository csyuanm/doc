package com.elead.ppm.project.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.elead.platform.common.service.dao.CrudDao;
import com.elead.ppm.project.domain.entity.ELProjectMemberBaseInfo;

import org.apache.ibatis.annotations.Param;


/**
 * 项目成员表DAO接口
 * @author wangxz
 */

@Mapper
@Repository
public interface ELProjectMemberBaseInfoDao extends CrudDao<ELProjectMemberBaseInfo> {
	
	/**
	 * 根据需求id查询参与人对象集合
	 * @param 需求id
	 * @return 参与人集合
	 */
	List<ELProjectMemberBaseInfo> findMemberByRequirementId(String requirementId);
	
	
	/**
	 * @描述（通过成员id和项目id获取成员对象）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年4月18日 下午4:51:18
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	ELProjectMemberBaseInfo getProjectMemberById(String elMemberId,String elProjectId);
	

	/**
	 * @描述（通过项目id获取项目成员）
	 * @author wencz
	 * @version 1.0
	 * @date 2017年5月2日 下午4:28:27
	 * @parameter （参数及其意义）
	 * @throws 异常类及抛出条件
	 * @return 返回值
	 */
	List<ELProjectMemberBaseInfo> getProjectMemberByProjectId(String elProjectId);
	
	/**
	 * 通过项目id获取项目成员名字
	 * @author 蒙茂良
	 * 创建时间：2017年5月2日  上午11:30:39
	 * version 1.0
	 * @param elProjectId
	 * @return
	 */
	String[] getProjectMemberNameByProjectId(String elProjectId);
	/**
	 * 显示项目成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:51:32
	 * version 1.0
	 * @param memberInfo（projectId）
	 * @return
	 */
	List<ELProjectMemberBaseInfo>  getProjectMember(ELProjectMemberBaseInfo memberInfo);
	
	/**
	 * 删除项目成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:58:41
	 * version 1.0
	 * @param id
	 */
	void deleteProjectMember(String id);
	
	/**
	 * 查找项目成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:58:55
	 * version 1.0
	 * @param projectId
	 * @param name
	 * @return
	 */
	List<ELProjectMemberBaseInfo> getMemberByName(@Param("projectId") String projectId,@Param("name") String name);
	
	/**
	 *
	 */
	int insert(ELProjectMemberBaseInfo memberInfo);
	
	/**
	 * 检查添加成员是否重复
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:59:47
	 * version 1.0
	 * @param memberInfo（projectId、name）
	 * @return
	 */
	int checkRepeat(ELProjectMemberBaseInfo memberInfo);
	
	/**
	 * 根据id修改成员角色
	 * @author 蒙茂良
	 * 创建时间：2017年5月24日  下午5:46:39
	 * version 1.0
	 * @param id
	 * @param roleId
	 */
	void updateRoleById(@Param("id")String id, @Param("roleId")String roleId);
}
