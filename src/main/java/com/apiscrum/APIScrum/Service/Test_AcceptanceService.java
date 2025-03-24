package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Test_Acceptance;
import com.apiscrum.APIScrum.enums.Test_AcceptanceState;

import java.util.List;

public interface Test_AcceptanceService {
    public Test_Acceptance addTest(Test_Acceptance test);
    public Test_Acceptance updateTest(Long id,Test_Acceptance testAcceptance);
    public void deleteTest(Long id);
    public Test_Acceptance getTestById(Long id);
    public List<Test_Acceptance> getAllTests();
    public Test_Acceptance updateTestState(Long id, Test_AcceptanceState state);
}
