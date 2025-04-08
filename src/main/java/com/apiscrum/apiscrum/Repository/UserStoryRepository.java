package com.apiscrum.apiscrum.Repository;

import com.apiscrum.apiscrum.Entity.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Long> {

}