package com.apiscrum.APIScrum.Repository;

import com.apiscrum.APIScrum.Entity.User;
import com.apiscrum.APIScrum.Entity.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
