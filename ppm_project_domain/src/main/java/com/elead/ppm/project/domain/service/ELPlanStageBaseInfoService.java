package com.elead.ppm.project.domain.service;

import com.elead.ppm.project.domain.entity.ELProjectBaseInfo;

/**
 * 计划类型Service
 * @author 封阳
 * @date 2017/4/12
 *
 */
public interface ELPlanStageBaseInfoService {
	/**
	 * 插入项目阶段
	 * @param elPlanStage
	 */
	void saveProjectStageBaseInfo(ELProjectBaseInfo baseInfo); 
	/**
	 * 更新项目阶段
	 * @param elPlanStage
	 */
	void updatePlanStageBaseInfo(String elProject);
}
