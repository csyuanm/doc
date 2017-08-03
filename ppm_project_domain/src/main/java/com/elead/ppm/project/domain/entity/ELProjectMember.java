package com.elead.ppm.project.domain.entity;

import java.util.Date;

import com.elead.platform.common.api.DataEntity;

public class ELProjectMember extends DataEntity<ELProjectMember> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String projectId;

    private String memberId;

    private String name;

    private String jobNumber;

    private Byte serialNumber;

    private String roleId;

    private Byte state;

    private String region;

    private Double percentage;

    private String remark;

    private Date inputTime;

    private Date releaseTime;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber == null ? null : jobNumber.trim();
    }

    public Byte getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Byte serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }
}