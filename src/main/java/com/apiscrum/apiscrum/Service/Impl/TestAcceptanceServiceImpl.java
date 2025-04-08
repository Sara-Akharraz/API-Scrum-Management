package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Entity.TestAcceptance;
import com.apiscrum.apiscrum.Repository.TestAcceptanceRepository;
import com.apiscrum.apiscrum.Service.TestAcceptanceService;
import com.apiscrum.apiscrum.enums.TestAcceptanceState;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestAcceptanceServiceImpl implements TestAcceptanceService {
    private TestAcceptanceRepository testAcceptanceRepository;
    public TestAcceptanceServiceImpl(TestAcceptanceRepository testAcceptanceRepository){
        this.testAcceptanceRepository=testAcceptanceRepository;
    }
    @Override
    public TestAcceptance addTest(TestAcceptance test) {
        return testAcceptanceRepository.save(test);
    }

    @Override
    public TestAcceptance updateTest(Long id, TestAcceptance updatedTestAcceptance) {
       Optional<TestAcceptance> prevTest=testAcceptanceRepository.findById(id);
       if(prevTest.isPresent()){
           TestAcceptance test=prevTest.get();
           test.setScenario(updatedTestAcceptance.getScenario());
           test.setGiven(updatedTestAcceptance.getGiven());
           test.setWhen(updatedTestAcceptance.getWhen());
           test.setThen(updatedTestAcceptance.getThen());
           test.setAnd(updatedTestAcceptance.getAnd());
           test.setBut(updatedTestAcceptance.getBut());
           test.setState(updatedTestAcceptance.getState());
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
    public TestAcceptance updateTestState(Long id, TestAcceptanceState state) {
        Optional<TestAcceptance> prevTest=testAcceptanceRepository.findById(id);
        if(prevTest.isPresent()){
            TestAcceptance test=prevTest.get();
            test.setState(state);
            return testAcceptanceRepository.save(test);
        }
        else{
            throw new EntityNotFoundException("Acceptance Test Not found for id:" +id);
        }
    }
    @Override
    public TestAcceptance getTestById(Long id) {
        return testAcceptanceRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Acceptance Test Not found for id :"+id));
    }

    @Override
    public List<TestAcceptance> getAllTests() {
        return testAcceptanceRepository.findAll();
    }
}
