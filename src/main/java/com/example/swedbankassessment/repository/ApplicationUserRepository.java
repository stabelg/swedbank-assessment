package com.example.swedbankassessment.repository;

import com.example.swedbankassessment.entity.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
    ApplicationUser findByUsernameOrEmail(String username, String email);
}
