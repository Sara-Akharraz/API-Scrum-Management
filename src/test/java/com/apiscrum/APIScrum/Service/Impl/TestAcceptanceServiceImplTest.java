package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Entity.TestAcceptance;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Repository.TestAcceptanceRepository;
import com.apiscrum.apiscrum.enums.TestAcceptanceState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestAcceptanceServiceImplTest {
    @Mock
    private TestAcceptanceRepository testAcceptanceRepository;
    @InjectMocks
    private TestAcceptanceServiceImpl testAcceptanceService;

    private TestAcceptance testAcceptance;
    private final UserStory userStory=new UserStory();
    @BeforeEach
    public void setup(){
        testAcceptance=new TestAcceptance(1L,"Login Feature","User is on the login page","User enters valid credentials and clicks login","User is redirected to the dashboard",null,"User fails to log in if credentials are incorrect",userStory, TestAcceptanceState.Passed);
    }
    @Test
    public void TestAddTest(){
        when(testAcceptanceRepository.save(any(TestAcceptance.class))).thenReturn(testAcceptance);
        TestAcceptance createdTest =testAcceptanceService.addTest(testAcceptance);
        assertNotNull(createdTest);
        assertEquals("Login Feature",createdTest.getScenario());
    }
    @Test
    public void TestGetTestById(){
        when(testAcceptanceRepository.findById(1L)).thenReturn((Optional.of(testAcceptance)));
        TestAcceptance foundTest=testAcceptanceService.getTestById(1L);
        assertNotNull(foundTest);
        assertEquals("Login Feature",foundTest.getScenario());
    }
    @Test
    public void TestDeleteTest(){
        when(testAcceptanceRepository.existsById(1L)).thenReturn(true);
        doNothing().when(testAcceptanceRepository).deleteById(1L);
        testAcceptanceService.deleteTest(1L);
        verify(testAcceptanceRepository,times(1)).deleteById(1L);
    }
    @Test
    public void TestUpdateTest(){
        TestAcceptance updatedTest=new TestAcceptance(1L,"Login Feature","User is on the login page","User enters valid credentials and clicks login","User is redirected to the dashboard","User sees a welcome message","User fails to log in if credentials are incorrect",userStory, TestAcceptanceState.Passed);
        when(testAcceptanceRepository.findById(1L)).thenReturn(Optional.of(testAcceptance));
        when(testAcceptanceRepository.save(any(TestAcceptance.class))).thenReturn(updatedTest);
        testAcceptanceService.updateTest(1L,updatedTest);
        verify(testAcceptanceRepository,times(1)).save(any(TestAcceptance.class));
    }
    @Test
    void TestgetAllTests(){
        List<TestAcceptance> tests= Arrays.asList(testAcceptance,new TestAcceptance(2L,"Search for a product","User is on the homepage","User types a product name in the search bar and presses Enter","Relevant search results are displayed",null,null,userStory, TestAcceptanceState.Pending));
        when(testAcceptanceRepository.findAll()).thenReturn(tests);
        List<TestAcceptance> testList=testAcceptanceService.getAllTests();
        assertEquals(2,testList.size());
        verify(testAcceptanceRepository,times(1)).findAll();

    }
    @Test
    void TestUpdateTestState(){
        when(testAcceptanceRepository.findById(1L)).thenReturn(Optional.of(testAcceptance));
        when(testAcceptanceRepository.save(any(TestAcceptance.class))).thenReturn(testAcceptance);
        testAcceptanceService.updateTestState(1L, TestAcceptanceState.Failed);
        assertEquals(TestAcceptanceState.Failed,testAcceptance.getState());
        verify(testAcceptanceRepository,times(1)).save(any(TestAcceptance.class));
    }
}
