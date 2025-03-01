package com.apiscrum.APIScrum.Repository;

import com.apiscrum.APIScrum.Entity.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Long> {
}
