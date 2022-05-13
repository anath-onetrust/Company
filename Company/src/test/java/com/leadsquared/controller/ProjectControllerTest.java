package com.leadsquared.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadsquared.request.DisAssociateProjectRequest;
import com.leadsquared.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProjectControllerTest.class)
@ActiveProfiles("test")
public class ProjectControllerTest {

    @MockBean
    private ProjectService projectService;

    @Autowired
    private MockMvc mockMvc;

    private ProjectController projectController ;
    private ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();

    @Test
    public void getEmployee() throws Exception {

        mockMvc.perform(get("/project/employee/"+UUID.randomUUID()));
        verify(projectService, times(0)).getEmployeeByProjectId(any());
    }

    @Test
    public void removeProject() throws Exception {
        DisAssociateProjectRequest disAssociateProjectRequest= new DisAssociateProjectRequest();
        disAssociateProjectRequest.setProjectGuid(UUID.fromString("041a6aa0-070e-449c-b527-fb48f92c8615"));
        disAssociateProjectRequest.setDepartmentCode("D00001");
        disAssociateProjectRequest.setEmployeeCode("E00001");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(disAssociateProjectRequest);
        UUID projectGuid = UUID.randomUUID();
        mockMvc.perform(delete("/project/disassociate")
                .content(json).contentType(MediaType.APPLICATION_JSON));
        verify(projectService, times(0)).disAssociateProject(disAssociateProjectRequest);
    }

}
