package com.elead.ppm.project.domain.entity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import com.elead.platform.common.api.DataEntity;
import com.elead.platform.common.api.ELUser;

public class ELProjectBaseInfo extends DataEntity<ELProjectBaseInfo>{

	private static final long serialVersionUID = 1L;

    private String projectProgramId;          //项目群ID

    private String projectCode;               //项目编号

    private String projectName;               //项目名称

    private String description;               //详细描述

    private String projectClass;              //项目类型

    private String pmName;                    //项目经理姓名

    private String pmId;                      //项目经理ID

    private Byte fileFlag;                    //项目是否归档

    private String state;                     //项目状态

    private String activeState;               //活动状态

    private String prodId;                    //所属产品线ID

    private String deptId;                    //所属部门

    private Date startTime;                   //启动时间

    private Date closeTime;                   //完成时间

    private Byte phase;                       //项目阶段

    private Float budgetDollar;               //预算美金

    private Float budgetRmb;                  //预算人民币

    private Short workload;                   //工作量

    private Byte visibleRange;                //项目可见范围
    
    private ELUser<?> elUser ; //用户信息
    
	/**
	 * @return the elUser
	 */
	public ELUser<?> getElUser() {
		return elUser;
	}

	/**
	 * @param elUser the elUser to set
	 */
	public void setElUser(ELUser<?> elUser) {
		this.elUser = elUser;
	}

	public String getProjectProgramId() {
        return projectProgramId;
    }

    public void setProjectProgramId(String projectProgramId) {
        this.projectProgramId = projectProgramId == null ? null : projectProgramId.trim();
    }

    public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName == null ? null : projectName.trim();
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getProjectClass() {
        return projectClass;
    }

    public void setProjectClass(String projectClass) {
        this.projectClass = projectClass == null ? null : projectClass.trim();
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getPmId() {
        return pmId;
    }

    public void setPmId(String pmId) {
        this.pmId = pmId == null ? null : pmId.trim();
    }

    public Byte getFileFlag() {
		return fileFlag;
	}

	public void setFileFlag(Byte fileFlag) {
		this.fileFlag = fileFlag;
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

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Byte getPhase() {
        return phase;
    }

    public void setPhase(Byte phase) {
        this.phase = phase;
    }

    public Float getBudgetDollar() {
        return budgetDollar;
    }

    public void setBudgetDollar(Float budgetDollar) {
        this.budgetDollar = budgetDollar;
    }

    public Float getBudgetRmb() {
		return budgetRmb;
	}

	public void setBudgetRmb(Float budgetRmb) {
		this.budgetRmb = budgetRmb;
	}

	public Short getWorkload() {
        return workload;
    }

    public void setWorkload(Short workload) {
        this.workload = workload;
    }

    public Byte getVisibleRange() {
        return visibleRange;
    }

    public void setVisibleRange(Byte visibleRange) {
        this.visibleRange = visibleRange;
    }
    
    public static String percnet(double d,double e){
    	if(d==0){
    	   return "0%";
    	}
        double p = d/e;  
        DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();  
        nf.applyPattern("00%"); //00表示小数点2位  
        nf.setMaximumFractionDigits(2); //2表示精确到小数点后2位  
        return nf.format(p);  
    }  
    
    
	
}
