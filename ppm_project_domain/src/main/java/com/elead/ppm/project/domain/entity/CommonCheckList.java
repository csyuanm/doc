package com.elead.ppm.project.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class CommonCheckList implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String checkId;

    private String ownerId;

    private String projectId;

    private String checkType;

    private String createName;

    private String checkName;

    private String checkDescription;

    private Float checkPercentage;

    private Byte checkState;

    private Byte delflag;

    private String createBy;

    private Date createtime;

    private String updateBy;

    private Date updatetime;
    
    public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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

    public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getCheckDescription() {
		return checkDescription;
	}

	public void setCheckDescription(String checkDescription) {
		this.checkDescription = checkDescription;
	}

	public Float getCheckPercentage() {
		return checkPercentage;
	}

	public void setCheckPercentage(Float checkPercentage) {
		this.checkPercentage = checkPercentage;
	}

	public Byte getCheckState() {
		return checkState;
	}

	public void setCheckState(Byte checkState) {
		this.checkState = checkState;
	}

	public Byte getDelflag() {
        return delflag;
    }

    public void setDelflag(Byte delflag) {
        this.delflag = delflag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}