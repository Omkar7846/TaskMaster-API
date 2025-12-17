package com.TaskMaster.TaskMaster.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TaskMaster.TaskMaster.Entity.Task;
import com.TaskMaster.TaskMaster.Entity.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByProject_Id(Long projectId, Pageable pageable);
    Optional<Task> findByIdAndProject_User(Long taskId, User user);

    @Query("""
        SELECT t FROM Task t 
        WHERE t.project.user = :user 
        AND (
            t.title LIKE :searchPattern OR 
            t.description LIKE :searchPattern
        )
        """)
    Page<Task> searchTasksByUserAndKeyword(
            @Param("user") User user,
            @Param("searchPattern") String searchPattern, 
            Pageable pageable );
}