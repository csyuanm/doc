package com.elead.ppm.project.domain.entity;

import java.util.Date;

import com.elead.platform.common.api.DataEntity;

/**
 * 任务实体类
 * @author Administrator
 *
 */
public class ELTaskBaseInfo extends DataEntity<ELTaskBaseInfo>{
	
	private static final long serialVersionUID = 1L;

    private String code;

    private String projectId;

    private String ownerId;

    private String ownerType;

    private String name;

    private String description;

    private String state;

    private String activeState;

    private Float percentage;

    private Float workload;

    private Date startTime;

    private Date deadline;

    private Date completionTime;

    private Date closeTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType == null ? null : ownerType.trim();
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getActiveState() {
        return activeState;
    }

    public void setActiveState(String activeState) {
        this.activeState = activeState == null ? null : activeState.trim();
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Float getWorkload() {
        return workload;
    }

    public void setWorkload(Float workload) {
        this.workload = workload;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

}