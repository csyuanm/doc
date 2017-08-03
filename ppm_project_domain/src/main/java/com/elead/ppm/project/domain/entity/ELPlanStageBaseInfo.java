package com.elead.ppm.project.domain.entity;


import com.elead.platform.common.api.DataEntity;

public class ELPlanStageBaseInfo extends DataEntity<ELPlanStageBaseInfo> {
	
	private static final long serialVersionUID = 1L;

    private String projectId;         //所属项目ID

    private String planStageName;     //计划类型名称

    private String description;       //详细描述

    private Integer serialNumber;     //序号

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getPlanStageName() {
		return planStageName;
	}

	public void setPlanStageName(String planStageName) {
		this.planStageName = planStageName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
    
    




}
