package com.elead.ppm.project.domain.entity;

import com.elead.platform.common.api.DataEntity;

/**
 * 标签实体类
 * @author Administrator
 *
 */
public class ELRequirementLabelRelation extends DataEntity<ELRequirementLabelRelation>{
	
	private static final long serialVersionUID = 1L;

    private String requirementId;

    private String lableId;

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId == null ? null : requirementId.trim();
    }

    public String getLableId() {
        return lableId;
    }

    public void setLableId(String lableId) {
        this.lableId = lableId == null ? null : lableId.trim();
    }
}