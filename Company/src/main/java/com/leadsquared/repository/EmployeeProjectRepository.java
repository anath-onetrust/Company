	package com.leadsquared.repository;

    import com.leadsquared.entity.EmployeeProject;
    import com.leadsquared.entity.EmployeeProjectId;
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, EmployeeProjectId> {

    }