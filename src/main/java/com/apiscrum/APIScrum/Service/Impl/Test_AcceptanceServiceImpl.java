package com.apiscrum.APIScrum.Service.Impl;

import com.apiscrum.APIScrum.Entity.Test_Acceptance;
import com.apiscrum.APIScrum.Repository.Test_AcceptanceRepository;
import com.apiscrum.APIScrum.Service.Test_AcceptanceService;
import com.apiscrum.APIScrum.enums.Test_AcceptanceState;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Test_AcceptanceServiceImpl implements Test_AcceptanceService {
    private Test_AcceptanceRepository testAcceptanceRepository;
    public Test_AcceptanceServiceImpl(Test_AcceptanceRepository testAcceptanceRepository){
        this.testAcceptanceRepository=testAcceptanceRepository;
    }
    @Override
    public Test_Acceptance addTest(Test_Acceptance test) {
        return testAcceptanceRepository.save(test);
    }

    @Override
    public Test_Acceptance updateTest(Long id, Test_Acceptance updatedTestAcceptance) {
       Optional<Test_Acceptance> prevTest=testAcceptanceRepository.findById(id);
       if(prevTest.isPresent()){
           Test_Acceptance test=prevTest.get();
           test.setScenario(updatedTestAcceptance.getScenario());
           test.setGiven(updatedTestAcceptance.getGiven());
           test.setWhen(updatedTestAcceptance.getWhen());
           test.setThen(updatedTestAcceptance.getThen());
           test.setAnd(updatedTestAcceptance.getAnd());
           test.setBut(updatedTestAcceptance.getBut());
           test.setAssociatedUserStory(updatedTestAcceptance.getAssociatedUserStory());
           return testAcceptanceRepository.save(test);

       }
       else{
           throw new EntityNotFoundException("Acceptance Test Not found for id :" +id);
       }

    }

    @Override
    public void deleteTest(Long id) {
        if(testAcceptanceRepository.existsById(id)){
            testAcceptanceRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Acceptance Test Not found for id :"+id);
        }

    }
    @Override
    public Test_Acceptance updateTestState(Long id, Test_AcceptanceState state) {
        Optional<Test_Acceptance> prevTest=testAcceptanceRepository.findById(id);
        if(prevTest.isPresent()){
            Test_Acceptance test=prevTest.get();
            test.setState(state);
            return testAcceptanceRepository.save(test);
        }
        else{
            throw new EntityNotFoundException("Acceptance Test Not found for id:" +id);
        }
    }
    @Override
    public Test_Acceptance getTestById(Long id) {
        return testAcceptanceRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Acceptance Test Not found for id :"+id));
    }

    @Override
    public List<Test_Acceptance> getAllTests() {
        return testAcceptanceRepository.findAll();
    }
}
