
package com.leadsquared.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Entity
@Table(name = "project",uniqueConstraints = { @UniqueConstraint(name = "UniqueProject", columnNames = { "projcode", "departcode" }) })
public class Project {

	@Id
	@GeneratedValue
	@Column(name = "project_id")
	private Long id ;
	@Column(name = "name")
	private String name;
	@Column(name = "guid")
	private String guid ;

	@Column(name = "projcode")
	private String projCode;

	@Column(name = "departcode")
	private String departCode;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "emp_project",
			joinColumns = @JoinColumn(name = "emp_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Employee> employee;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "deparment_project",
			joinColumns = @JoinColumn(name = "dept_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Department> department;

	public Project() {
	}


	public String getName() {
		return name;
	}

	public String getGuid() {
		return guid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public Long getId() {
		return id;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public String getProjCode() {
		return projCode;
	}

	public String getDepartCode() {
		return departCode;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}
}

