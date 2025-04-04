package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.Entity.TestAcceptance;
import com.apiscrum.apiscrum.enums.TestAcceptanceState;

import java.util.List;

public interface TestAcceptanceService {
    public TestAcceptance addTest(TestAcceptance test);
    public TestAcceptance updateTest(Long id, TestAcceptance testAcceptance);
    public void deleteTest(Long id);
    public TestAcceptance getTestById(Long id);
    public List<TestAcceptance> getAllTests();
    public TestAcceptance updateTestState(Long id, TestAcceptanceState state);
}
