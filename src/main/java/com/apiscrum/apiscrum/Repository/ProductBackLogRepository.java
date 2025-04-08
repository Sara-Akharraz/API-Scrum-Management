package com.apiscrum.apiscrum.Repository;

import com.apiscrum.apiscrum.Entity.ProductBackLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBackLogRepository extends JpaRepository<ProductBackLog,Long> {

}
