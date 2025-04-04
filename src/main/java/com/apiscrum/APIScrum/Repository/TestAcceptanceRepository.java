package com.apiscrum.apiscrum.Repository;

import com.apiscrum.apiscrum.Entity.TestAcceptance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestAcceptanceRepository extends JpaRepository<TestAcceptance,Long> {
}
