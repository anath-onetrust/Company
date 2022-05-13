package com.leadsquared.service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.leadsquared.entity.Employee;
import com.leadsquared.entity.EmployeeProjectId;
import com.leadsquared.entity.Project;
import com.leadsquared.repository.ProjectRepository;
import com.leadsquared.repository.EmployeeProjectRepository;
import com.leadsquared.request.DisAssociateProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    public List<Employee>  getEmployeeByProjectId(UUID projectGuid){
        String guid = String.valueOf(projectGuid);
        List<Project> projectList = projectRepository.findByGuid(guid);
        return getEmlyeeList(projectList);
    }

    private List<Employee> getEmlyeeList(List<Project> projectList){
        return projectList.stream()
                .flatMap(project -> project.getEmployee().stream())
                .collect(Collectors.toList());
    }

    @Transactional
    public String disAssociateProject(DisAssociateProjectRequest disAssociateProjectRequest){
        List<Project> projectList = projectRepository.findByGuid(disAssociateProjectRequest.getProjectGuid().toString());
        projectList = projectList.stream().filter(project -> project.getDepartCode().equals(disAssociateProjectRequest.getDepartmentCode()))
                .collect(Collectors.toList());
        Employee projectOption = projectList.stream()
                .flatMap(project -> project.getEmployee().stream())
                .filter(employee -> employee.getEmpCode().equals(disAssociateProjectRequest.getEmployeeCode()))
                .findAny().orElse(null);
        if(projectOption!=null){
            EmployeeProjectId employeeProjectId = new EmployeeProjectId();
            employeeProjectId.setProjectId(projectOption.getId());
            employeeProjectId.setEmpId(projectOption.getId());
            employeeProjectRepository.deleteById(employeeProjectId);
            return "Project disassociated successfully";
        }
        else{
            return "Project disassociate Failed request combination not found";
        }
    }
}