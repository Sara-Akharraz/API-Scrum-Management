package com.apiscrum.APIScrum.Repository;

import com.apiscrum.APIScrum.Entity.Task;
import com.apiscrum.APIScrum.Entity.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Long> {

}