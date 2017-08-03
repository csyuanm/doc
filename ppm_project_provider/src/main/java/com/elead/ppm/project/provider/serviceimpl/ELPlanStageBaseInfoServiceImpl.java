package com.elead.ppm.project.provider.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elead.platform.common.service.service.CrudService;
import com.elead.platform.system.domain.entity.ELDict;
import com.elead.platform.system.domain.service.ELDictService;
import com.elead.ppm.project.domain.entity.ELPlanStageBaseInfo;
import com.elead.ppm.project.domain.entity.ELProjectBaseInfo;
import com.elead.ppm.project.domain.service.ELPlanStageBaseInfoService;
import com.elead.ppm.project.domain.service.ELProjectBaseInfoService;
import com.elead.ppm.project.provider.dao.ELPlanStageBaseInfoDao;

@Service(value = "planStageBaseInfoService")
public class ELPlanStageBaseInfoServiceImpl extends CrudService<ELPlanStageBaseInfoDao, ELPlanStageBaseInfo> 
		implements ELPlanStageBaseInfoService {
	@Autowired
	ELProjectBaseInfoService elProjectBaseInfoService;
	
	@Autowired
	private ELDictService dictService;

	/**
	 * 插入项目阶段
	 * @param elPlanStage
	 */
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	@Override
	public void saveProjectStageBaseInfo(ELProjectBaseInfo baseInfo) {
		ELDict dict = new ELDict();
		dict.setType("project_stage_type");
		dict.setAttribute("项目阶段");
		List<ELDict> list = dictService.findList(dict);
		//set计划阶段属性
		List<ELPlanStageBaseInfo> elPlanStageBaseInfoList = new ArrayList<ELPlanStageBaseInfo>();
		//set计划阶段属性
		int i = 1;
		for(ELDict elDict : list){
			ELPlanStageBaseInfo elPlanStageBaseInfo = new ELPlanStageBaseInfo();
			elPlanStageBaseInfo.setProjectId(baseInfo.getId());
			elPlanStageBaseInfo.setPlanStageName(elDict.getValue());
			elPlanStageBaseInfo.setDescription(elDict.getDescription());
			elPlanStageBaseInfo.setSerialNumber(i);
			elPlanStageBaseInfo.setCreateBy(baseInfo.getCreateBy());
			elPlanStageBaseInfo.setUpdateBy(baseInfo.getUpdateBy());
			elPlanStageBaseInfoList.add(elPlanStageBaseInfo);
			i++ ;
		}
		Iterator<ELPlanStageBaseInfo> it = elPlanStageBaseInfoList.iterator();
		/**
		 * 保存项目阶段
		 */
		try {
			while(it.hasNext()){
				ELPlanStageBaseInfo elPlanStageBaseInfo = it.next();
				elPlanStageBaseInfo.preInsert();
				elPlanStageBaseInfo.setIsNewRecord(true);
				super.insert(elPlanStageBaseInfo);
			} 
        } catch (Exception e) {
	        e.printStackTrace();
        }
		
	}

	@Override
    public void updatePlanStageBaseInfo(String elProject) {
	    // TODO Auto-generated method stub
	    
    }
}
