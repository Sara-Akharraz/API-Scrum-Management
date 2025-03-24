package com.apiscrum.APIScrum.Repository;

import com.apiscrum.APIScrum.Entity.Task;
import com.apiscrum.APIScrum.Entity.Test_Acceptance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Test_AcceptanceRepository extends JpaRepository<Test_Acceptance,Long> {
}
