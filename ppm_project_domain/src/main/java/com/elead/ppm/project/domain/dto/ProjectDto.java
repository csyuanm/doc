package com.elead.ppm.project.domain.dto;

import com.elead.platform.common.api.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
* @Description: 	consumer与provider端订单传输对象
* @author wangxz
* @date 2017/03/22
*/
public class ProjectDto extends DataEntity<ProjectDto>{
	
		/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

		private String id ;
		 
	    private String projectProgramId;          //项目群ID

	    private String projectCode;               //项目编号

	    private String name;               //项目名称

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
	    
	    private String userid ; //用户id

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the userid
		 */
		public String getUserid() {
			return userid;
		}

		/**
		 * @param userid the userid to set
		 */
		public void setUserid(String userid) {
			this.userid = userid;
		}


		/**
		 * @return the projectProgramId
		 */
		public String getProjectProgramId() {
			return projectProgramId;
		}

		/**
		 * @param projectProgramId the projectProgramId to set
		 */
		public void setProjectProgramId(String projectProgramId) {
			this.projectProgramId = projectProgramId;
		}

		/**
		 * @return the projectCode
		 */
		public String getProjectCode() {
			return projectCode;
		}

		/**
		 * @param projectCode the projectCode to set
		 */
		public void setProjectCode(String projectCode) {
			this.projectCode = projectCode;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}

		/**
		 * @return the projectClass
		 */
		public String getProjectClass() {
			return projectClass;
		}

		/**
		 * @param projectClass the projectClass to set
		 */
		public void setProjectClass(String projectClass) {
			this.projectClass = projectClass;
		}

		/**
		 * @return the pmName
		 */
		public String getPmName() {
			return pmName;
		}

		/**
		 * @param pmName the pmName to set
		 */
		public void setPmName(String pmName) {
			this.pmName = pmName;
		}

		/**
		 * @return the pmId
		 */
		public String getPmId() {
			return pmId;
		}

		/**
		 * @param pmId the pmId to set
		 */
		public void setPmId(String pmId) {
			this.pmId = pmId;
		}

		/**
		 * @return the fileFlag
		 */
		public Byte getFileFlag() {
			return fileFlag;
		}

		/**
		 * @param fileFlag the fileFlag to set
		 */
		public void setFileFlag(Byte fileFlag) {
			this.fileFlag = fileFlag;
		}

		/**
		 * @return the state
		 */
		public String getState() {
			return state;
		}

		/**
		 * @param state the state to set
		 */
		public void setState(String state) {
			this.state = state;
		}

		/**
		 * @return the activeState
		 */
		public String getActiveState() {
			return activeState;
		}

		/**
		 * @param activeState the activeState to set
		 */
		public void setActiveState(String activeState) {
			this.activeState = activeState;
		}

		/**
		 * @return the prodId
		 */
		public String getProdId() {
			return prodId;
		}

		/**
		 * @param prodId the prodId to set
		 */
		public void setProdId(String prodId) {
			this.prodId = prodId;
		}

		/**
		 * @return the deptId
		 */
		public String getDeptId() {
			return deptId;
		}

		/**
		 * @param deptId the deptId to set
		 */
		public void setDeptId(String deptId) {
			this.deptId = deptId;
		}

		/**
		 * @return the startTime
		 */
		public Date getStartTime() {
			return startTime;
		}

		/**
		 * @param startTime the startTime to set
		 */
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		/**
		 * @return the closeTime
		 */
		public Date getCloseTime() {
			return closeTime;
		}

		/**
		 * @param closeTime the closeTime to set
		 */
		public void setCloseTime(Date closeTime) {
			this.closeTime = closeTime;
		}

		/**
		 * @return the phase
		 */
		public Byte getPhase() {
			return phase;
		}

		/**
		 * @param phase the phase to set
		 */
		public void setPhase(Byte phase) {
			this.phase = phase;
		}

		/**
		 * @return the budgetDollar
		 */
		public Float getBudgetDollar() {
			return budgetDollar;
		}

		/**
		 * @param budgetDollar the budgetDollar to set
		 */
		public void setBudgetDollar(Float budgetDollar) {
			this.budgetDollar = budgetDollar;
		}

		/**
		 * @return the budgetRmb
		 */
		public Float getBudgetRmb() {
			return budgetRmb;
		}

		/**
		 * @param budgetRmb the budgetRmb to set
		 */
		public void setBudgetRmb(Float budgetRmb) {
			this.budgetRmb = budgetRmb;
		}

		/**
		 * @return the workload
		 */
		public Short getWorkload() {
			return workload;
		}

		/**
		 * @param workload the workload to set
		 */
		public void setWorkload(Short workload) {
			this.workload = workload;
		}

		/**
		 * @return the visibleRange
		 */
		public Byte getVisibleRange() {
			return visibleRange;
		}

		/**
		 * @param visibleRange the visibleRange to set
		 */
		public void setVisibleRange(Byte visibleRange) {
			this.visibleRange = visibleRange;
		}
	    
	    
	    
	    
}
