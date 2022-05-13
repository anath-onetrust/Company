package com.leadsquared.request;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DisAssociateProjectRequest {
    @NotNull
    private UUID projectGuid;
    @NotNull
    private String departmentCode;
    @NotNull
    private String employeeCode;

    public UUID getProjectGuid() {
        return projectGuid;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setProjectGuid(UUID projectGuid) {
        this.projectGuid = projectGuid;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
}
