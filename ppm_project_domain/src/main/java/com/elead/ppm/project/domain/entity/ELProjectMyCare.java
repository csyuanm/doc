/**
 * 
 */
package com.elead.ppm.project.domain.entity;

import com.elead.platform.common.api.DataEntity;

/**
 * @author mml
 * @version 创建时间：2017年4月18日  下午2:22:07
 */
public class ELProjectMyCare extends DataEntity<ELProjectBaseInfo>{
	
	private static final long serialVersionUID = 1L;
	
	private String projectId;
	
	private String projectName;
	
	private String userId;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
