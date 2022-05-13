
package com.leadsquared.entity;

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
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue
	@Column(name = "dept_id")
	private Long id ;
	@Column(name = "departcode",unique = true)
	private String departmentCode;
	@Column(name = "name")
	private String name;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "emp_department",
			joinColumns = @JoinColumn(name = "emp_id",unique = false),
			inverseJoinColumns = @JoinColumn(name = "dept_id",unique = false))
	private List<Employee> employee;
	public Department() {
	}
	public String getDepartmentCode() {
		return departmentCode;
	}

	public String getName() {
		return name;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
}

