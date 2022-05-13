
package com.leadsquared.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue
	@Column(name = "emp_id")
	@JsonIgnore
	private Long id ;
	@Column(name = "empcode",unique = true)
	private String empCode;
	@Column(name = "name")
	private String name;
	public Employee() {
	}
	public String getEmpCode() {
		return empCode;
	}

	public String getName() {
		return name;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
}

