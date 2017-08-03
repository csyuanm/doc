package com.elead.ppm.project.provider.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elead.platform.common.api.CommonResponse;
import com.elead.platform.common.api.ELUser;
import com.elead.platform.common.service.service.CrudService;
import com.elead.platform.common.utils.JsonMapper;
import com.elead.platform.system.domain.entity.ELAuthorizationControl;
import com.elead.platform.system.domain.entity.ELRole;
import com.elead.platform.system.domain.service.ELAuthorizationControlService;
import com.elead.platform.system.domain.service.ELPeopleService;
import com.elead.ppm.project.domain.entity.ELProjectBaseInfo;
import com.elead.ppm.project.domain.entity.ELProjectMemberBaseInfo;
import com.elead.ppm.project.domain.service.ELProjectMemberBaseInfoService;
import com.elead.ppm.project.provider.dao.ELProjectMemberBaseInfoDao;

/**
 * 项目成员service
 */
@Service(value = "projectMemberBaseInfoService")
public class ELProjectMemberBaseInfoServiceImpl extends CrudService<ELProjectMemberBaseInfoDao, ELProjectMemberBaseInfo>
        implements ELProjectMemberBaseInfoService
{

	@Autowired
	ELProjectMemberBaseInfoDao memberDao;
	
	@Autowired
	ELPeopleService elPeopleService;
	
	@Autowired
	private ELAuthorizationControlService authorizationControlService;

	/**
	 * 保存项目成员信息
	 */
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void saveProjectMemberInfo(String[] elMemberId, ELProjectBaseInfo baseInfo) {
		
		for (String member : elMemberId) {
			
			ELAuthorizationControl act = new ELAuthorizationControl();
			act.setPrincipalId(member);
			act.setPrincipalType("user");
			List<ELRole> roles = authorizationControlService.findRoleByUser(act);//获取角色
			String roleIds = "" ;
			for(ELRole elRole : roles){
				if(elRole!=null){
					roleIds+=elRole.getId()+";";
				}
			}
			
			ELProjectMemberBaseInfo elProjectMemberBaseInfo = new ELProjectMemberBaseInfo();
			/**
			 * 根据成员ID查询出成员的信息getter,setter
			 */
			elProjectMemberBaseInfo.setMemberId(member);
			ELUser<?> elUser = elPeopleService.getUser(member);
			elProjectMemberBaseInfo.setProjectId(baseInfo.getId());
			elProjectMemberBaseInfo.setName(elUser.getUserName());
			elProjectMemberBaseInfo.setCreateBy(baseInfo.getCreateBy());
			elProjectMemberBaseInfo.setUpdateBy(baseInfo.getUpdateBy());
			elProjectMemberBaseInfo.setJobNumber(elUser.getCode());
			elProjectMemberBaseInfo.setRemark("创建项目，默认创建项目阶段");
			elProjectMemberBaseInfo.setRoleId(roleIds);
			elProjectMemberBaseInfo.setIsNewRecord(true);
			elProjectMemberBaseInfo.preInsert();
			super.save(elProjectMemberBaseInfo);
		}
		
	}

	@Override
    public ELProjectMemberBaseInfo getProjectMemberById(String elMemberId, String elProjectId) {
		return memberDao.getProjectMemberById(elMemberId, elProjectId);
    }

	


	

	/**
	 * 更新项目成员
	 */
	@Override
	public void updateProjectMemberInfo(String[] elMemberId,  ELProjectBaseInfo baseInfo) {
		List<ELProjectMemberBaseInfo> list = memberDao.getProjectMemberByProjectId(baseInfo.getId());
		try {
			if(elMemberId.length>0){
				for (ELProjectMemberBaseInfo entity : list) {
					memberDao.deleteById(entity.getId());
				}
				for (String member : elMemberId) {
					ELProjectMemberBaseInfo elProjectMemberBaseInfo = new ELProjectMemberBaseInfo();
					/**
					 * 根据成员ID查询出成员的信息getter,setter
					 */
					elProjectMemberBaseInfo.setMemberId(member);
					elProjectMemberBaseInfo.setProjectId(baseInfo.getId());
					elProjectMemberBaseInfo.setName(baseInfo.getElUser().getUserName());
					elProjectMemberBaseInfo.setCreateBy(baseInfo.getCreateBy());
					elProjectMemberBaseInfo.setUpdateBy(baseInfo.getUpdateBy());
					elProjectMemberBaseInfo.setJobNumber(baseInfo.getElUser().getCode());
					elProjectMemberBaseInfo.setRemark("创建项目，默认创建项目阶段");
					elProjectMemberBaseInfo.setRoleId("");

					elProjectMemberBaseInfo.setIsNewRecord(true);
					elProjectMemberBaseInfo.preInsert();
					super.save(elProjectMemberBaseInfo);

				}
			}
			
		} catch (Exception e) {
			logger.error("添加成员出错", e);
		}

	}
	
	/**
	 * 获取项目成员名字
	 */
	@Override
	public String[] getProjectMemberNameByProjectId(String elProjectId) {
		
		return memberDao.getProjectMemberNameByProjectId(elProjectId);
	}
	

	
	/**
	 * 显示项目成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:51:32
	 * version 1.0
	 * @param memberInfo （projectId）
	 * @return
	 */
	@Override
	public List<ELProjectMemberBaseInfo> getProjectMember(ELProjectMemberBaseInfo memberInfo) {
		
		return memberDao.getProjectMember(memberInfo);
	}
	
	
	/**
	 * 删除项目成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:52:08
	 * version 1.0
	 * @param memberInfo
	 */
	@Transactional(readOnly=false)
	@Override
	public void deleteProjectMember(String id) {
		  memberDao.deleteProjectMember(id);
	}

	/**
	 * 查找项目成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:52:41
	 * version 1.0
	 * @param projectId
	 * @param name
	 * @return
	 */
	@Override
	public List<ELProjectMemberBaseInfo> getMemberByName(String projectId, String name) {
	
		name = "%"+name+"%";
		return memberDao.getMemberByName(projectId, name);
/*		for (ELProjectMemberBaseInfo re : list) {
			JSONObject json = new JSONObject();
			try {
				json.put("id", re.getProjectId());
				json.put("text", re.getName());

				jsons.put(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}*/
		
	}
	
	/**
	 * 添加成员
	 * @author 蒙茂良
	 * 创建时间：2017年5月10日  下午2:52:57
	 * version 1.0
	 * @param memberInfo
	 * @return
	 */
	@Transactional(readOnly=false)
	@Override
	public String addMember(ELProjectMemberBaseInfo memberInfo) {
		String str = null;
		//memberDao.getProjectMemberById(elMemberId, elProjectId);
		int count = memberDao.checkRepeat(memberInfo);
		try {

			if(count == 0){
					memberInfo.setIsNewRecord(true);
					super.save(memberInfo);
					return JsonMapper.toJsonString(CommonResponse.createCommonResponse("添加成功"));
			}else{
				return JsonMapper.toJsonString(CommonResponse.createCommonResponse("已添加该成员"));
			}	
		} catch (Exception e) {
			logger.error("添加成员出错", e);
			return JsonMapper.toJsonString(CommonResponse.createExceptionCommonResponse(e));
		}
				
	}

	/**
	 * 根据id修改成员角色
	 * @author 蒙茂良
	 * 创建时间：2017年5月24日  下午5:46:39
	 * version 1.0
	 * @param id
	 * @param roleId
	 */
	@Transactional(readOnly=false)
	@Override
	public void updateRoleById(String id, String roleId) {
		memberDao.updateRoleById(id, roleId);
	}
}
