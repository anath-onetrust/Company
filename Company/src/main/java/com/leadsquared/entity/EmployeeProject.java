

package com.leadsquared.entity;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "emp_project")
public class EmployeeProject {

	@EmbeddedId
	private EmployeeProjectId id;
}

