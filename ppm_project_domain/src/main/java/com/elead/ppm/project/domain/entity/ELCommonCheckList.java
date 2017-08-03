package com.elead.ppm.project.domain.entity;

import com.elead.platform.common.api.DataEntity;

/**
 * 检查项实体类
 * @author Administrator
 *
 */
public class ELCommonCheckList extends DataEntity<ELCommonCheckList>{
	
	private static final long serialVersionUID = 1L;

    private String ownerId;

    private String projectId;

    private String checkType;

    private String createName;

    private String name;

    private String description;

    private Float percentage;

    private Byte state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType == null ? null : checkType.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

}