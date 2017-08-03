package com.elead.ppm.project.domain.entity;

import com.elead.platform.common.api.DataEntity;

/**
 * 检查项
 * @author  wencz 
 * E-mail: wencz@e-lead.cn
 * @date 创建时间：2017年5月9日 上午11:50:08 
 * @version 1.0 
 * @company Elead
 */
public class CommonCheckItem extends DataEntity<CommonCheckItem> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ownerId; //检查项关联的对象id
	private String projectId; //所属项目ID
	private String checkType;//业务类型（计划[1];任务[2];问题[3]；需求[4];风险[5]）
	private String createName;//创建人姓名
	private String name; //名称
	private String description; //描述
	private Float percentage;//完成百分比
	private Integer state;//状态（ default[0];完成[1];关闭[2]）
	/**
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the checkType
	 */
	public String getCheckType() {
		return checkType;
	}
	/**
	 * @param checkType the checkType to set
	 */
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	/**
	 * @return the createName
	 */
	public String getCreateName() {
		return createName;
	}
	/**
	 * @param createName the createName to set
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
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
	 * @return the percentage
	 */
	public Float getPercentage() {
		return percentage;
	}
	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
