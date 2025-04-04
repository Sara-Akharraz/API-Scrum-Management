package com.apiscrum.apiscrum.Repository;

import com.apiscrum.apiscrum.Entity.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Long> {
}
