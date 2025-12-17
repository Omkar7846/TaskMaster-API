package com.TaskMaster.TaskMaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.TaskMaster.TaskMaster.Entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}