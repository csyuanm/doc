package com.elead.ppm.project.domain.entity;

import java.util.Date;
import java.util.List;
import com.elead.platform.common.api.DataEntity;
import com.elead.platform.common.api.ELUser;
import com.elead.platform.system.domain.entity.ELDict;
import com.elead.ppm.task.domain.entity.ELTaskBaseInfo;

/**
 * 项目需求实体类
 * @author Administrator
 *
 */
public class ELRequirementBaseInfo extends DataEntity<ELRequirementBaseInfo>{
	
	private static final long serialVersionUID = 1L;

    private String projectId;

    private String code;

    private String name;

    private String description;

    private String state;

    private Byte priority;

    private Float workload;

    private String submitterId;

    private String submitterName;

    private Date createtime;
    
    private Date endDate;
    
    private String endDateStr;
    
    private String typeId;
    
    private String operate;

    private List<CommonCheckList> commonCheckList;

	private List<String> lableIds;
	
	private List<ELDict> lableList;
	
	private List<String> responsibleIds;
	
	private List<ELUser> responsibleList;
	
	private List<ELTaskBaseInfo> taskList;
	
    
    public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public Float getWorkload() {
        return workload;
    }

    public void setWorkload(Float workload) {
        this.workload = workload;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId == null ? null : submitterId.trim();
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName == null ? null : submitterName.trim();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

	public List<CommonCheckList> getCommonCheckList() {
		return commonCheckList;
	}

	public void setCommonCheckList(List<CommonCheckList> commonCheckList) {
		this.commonCheckList = commonCheckList;
	}

	public List<String> getLableIds() {
		return lableIds;
	}

	public void setLableIds(List<String> lableIds) {
		this.lableIds = lableIds;
	}

	public List<ELDict> getLableList() {
		return lableList;
	}

	public void setLableList(List<ELDict> lableList) {
		this.lableList = lableList;
	}

	public List<String> getResponsibleIds() {
		return responsibleIds;
	}

	public void setResponsibleIds(List<String> responsibleIds) {
		this.responsibleIds = responsibleIds;
	}

	public List<ELUser> getResponsibleList() {
		return responsibleList;
	}

	public void setResponsibleList(List<ELUser> responsibleList) {
		this.responsibleList = responsibleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public List<ELTaskBaseInfo> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<ELTaskBaseInfo> taskList) {
		this.taskList = taskList;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

}