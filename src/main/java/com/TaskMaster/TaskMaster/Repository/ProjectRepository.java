package com.TaskMaster.TaskMaster.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.TaskMaster.TaskMaster.Entity.Project;
import com.TaskMaster.TaskMaster.Entity.User;
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUser(User user);
    Optional<Project> findByIdAndUserId(Long projectId, Long userId);
}