
package com.leadsquared.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmployeeProjectId implements Serializable {
	@Column(name = "emp_id")
	private Long empId ;

	@Column(name = "project_id")
	private Long projectId ;


	public EmployeeProjectId() {
	}

	public Long getEmpId() {
		return empId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
}

