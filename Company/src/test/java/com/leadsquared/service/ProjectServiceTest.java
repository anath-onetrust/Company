package com.leadsquared.service;


import com.leadsquared.entity.Department;
import com.leadsquared.entity.Employee;
import com.leadsquared.entity.Project;
import com.leadsquared.repository.EmployeeProjectRepository;
import com.leadsquared.repository.ProjectRepository;
import com.leadsquared.request.DisAssociateProjectRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private EmployeeProjectRepository employeeProjectRepository;
    @InjectMocks
    private ProjectService projectService;

    @Test
    void getEmployeeByProjectId(){
        List<Project> projectList= getProjectList();
        when(projectRepository.findByGuid(any())).thenReturn(projectList);
        List<Employee> employeeList= projectService.getEmployeeByProjectId(UUID.randomUUID());
        Assertions.assertNotNull(employeeList);
    }

    @Test
    void removeProject(){
        List<Project> projectList= getProjectList();
        when(projectRepository.findByGuid(any())).thenReturn(projectList);
        doNothing().when(employeeProjectRepository).deleteById(any());
        List<Employee> employeeList= projectService.getEmployeeByProjectId(UUID.randomUUID());
        DisAssociateProjectRequest disAssociateProjectRequest= new DisAssociateProjectRequest();
        disAssociateProjectRequest.setProjectGuid(UUID.fromString("041a6aa0-070e-449c-b527-fb48f92c8615"));
        disAssociateProjectRequest.setDepartmentCode("D00001");
        disAssociateProjectRequest.setEmployeeCode("E00001");
        String response = projectService.disAssociateProject(disAssociateProjectRequest);
       Assertions.assertEquals("Project disassociated successfully",response);
    }
    //TODO::Add more test case
    private List<Project> getProjectList(){
        List<Project> projectList= new ArrayList<>();
        Project project = new Project();
        project.setProjCode("P00001");
        project.setDepartCode("D00001");
        project.setGuid("041a6aa0-070e-449c-b527-fb48f92c8615");
        List<Department> department = new ArrayList<>();
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setName("Alex");
        employee.setEmpCode("E00001");
        employeeList.add(employee);
        project.setDepartment(department);
        project.setEmployee(employeeList);
        project.setName("Demo");
        project.setProjCode("PROJ001");
        projectList.add(project);
        return projectList;
    }
}
