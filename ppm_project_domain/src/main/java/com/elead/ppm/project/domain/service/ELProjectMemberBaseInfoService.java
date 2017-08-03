package com.elead.ppm.project.domain.service;

import java.util.List;

import com.elead.ppm.project.domain.entity.ELProjectBaseInfo;
import com.elead.ppm.project.domain.entity.ELProjectMember;
import com.elead.ppm.project.domain.entity.ELProjectMemberBaseInfo;


/**
 * @author zhangwei
 * @title: 项目成员服务接口
 * @date 2017/03/23
 */
public interface ELProjectMemberBaseInfoService {
	/**
	 * 添加项目成员
	 * @param elProjectMemberBaseInfo
	 */
	void saveProjectMemberInfo(String[] elMemberId,ELProjectBaseInfo elProjectBaseInfo);
	/**
	 * 修改项目成员
	 * @param elProjectMemberBaseInfo
	 */
	void updateProjectMemberInfo(String[] elMemberId,ELProjectBaseInfo elProjectBaseInfo);
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
	
	
	
	String[] getProjectMemberNameByProjectId(String elProjectId);
	
	/**
	 * 显示项目成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:51:32
	 * version 1.0
	 * @param memberInfo （projectId）
	 * @return
	 */
	List<ELProjectMemberBaseInfo>  getProjectMember(ELProjectMemberBaseInfo memberInfo);
	
	/**
	 * 删除项目成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:52:08
	 * version 1.0
	 * @param memberInfo
	 */
	void deleteProjectMember(String id);
	
	/**
	 * 查找项目成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:52:41
	 * version 1.0
	 * @param projectId
	 * @param name
	 * @return
	 */
	List<ELProjectMemberBaseInfo> getMemberByName(String projectId, String name);
	
	/**
	 * 添加成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:52:57
	 * version 1.0
	 * @param memberInfo
	 * @return
	 */
	String addMember(ELProjectMemberBaseInfo memberInfo);
	
	/**
	 * 根据id修改成员角色
	 * @author 蒙茂良
	 * 创建时间：2017年5月24日  下午5:46:39
	 * version 1.0
	 * @param id
	 * @param roleId
	 */
	void updateRoleById(String id, String roleId);
}
