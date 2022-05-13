package com.leadsquared.controller;
import java.util.List;
import java.util.UUID;
import com.leadsquared.entity.Employee;
import com.leadsquared.request.DisAssociateProjectRequest;
import com.leadsquared.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    //TODO::More specific  error message in case of exception, input validation.
    @Autowired
    ProjectService projectService;

    @GetMapping("/employee/{guid}")
    public List<Employee>  getAllEmployee(@PathVariable(required = true) UUID  guid) {
        return projectService.getEmployeeByProjectId(guid);
    }

    @DeleteMapping("/disassociate")
    public String disassociateProject(@RequestBody DisAssociateProjectRequest disAssociateProjectRequest) {

        return projectService.disAssociateProject(disAssociateProjectRequest);
    }
}