package com.apiscrum.APIScrum.Controller;

import com.apiscrum.APIScrum.DTO.Test_AcceptanceDTO;
import com.apiscrum.APIScrum.Entity.Test_Acceptance;
import com.apiscrum.APIScrum.Mapper.Test_AcceptanceMapper;
import com.apiscrum.APIScrum.Service.Test_AcceptanceService;
import com.apiscrum.APIScrum.enums.Test_AcceptanceState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/acceptance_tests")
public class Test_AcceptanceController {

    private final Test_AcceptanceService testAcceptanceService;

    private final Test_AcceptanceMapper testAcceptanceMapper;
    public Test_AcceptanceController(Test_AcceptanceService testAcceptanceService,Test_AcceptanceMapper testAcceptanceMapper){
        this.testAcceptanceService=testAcceptanceService;
        this.testAcceptanceMapper=testAcceptanceMapper;
    }

    @PostMapping
    public ResponseEntity<Test_AcceptanceDTO> addTest(@Validated @RequestBody Test_AcceptanceDTO testAcceptanceDTO){
        Test_Acceptance testAcceptance=testAcceptanceMapper.toEntity(testAcceptanceDTO);
        Test_Acceptance newTest=testAcceptanceService.addTest(testAcceptance);
        return new ResponseEntity<>(testAcceptanceMapper.toDTO(newTest), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Test_AcceptanceDTO> getTestById(@PathVariable Long id){
        Test_Acceptance test=testAcceptanceService.getTestById(id);
        return ResponseEntity.ok(testAcceptanceMapper.toDTO(test));
    }
    @GetMapping()
    public ResponseEntity<List<Test_AcceptanceDTO>> getAllTests(){
        List<Test_AcceptanceDTO> tests=testAcceptanceService.getAllTests().stream()
                .map(testAcceptanceMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tests);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Test_AcceptanceDTO> updateTest(@PathVariable Long id,@RequestBody Test_AcceptanceDTO testAcceptanceDTO){
        Test_Acceptance test=testAcceptanceMapper.toEntity(testAcceptanceDTO);
        Test_Acceptance updatedTest=testAcceptanceService.updateTest(id,test);
        return ResponseEntity.ok(testAcceptanceMapper.toDTO(updatedTest));
    }
    @PutMapping("/{id}/state")
    public ResponseEntity<Test_AcceptanceDTO> updateTestState(@PathVariable Long id, @RequestBody Test_AcceptanceState state){
        Test_Acceptance test=testAcceptanceService.updateTestState(id,state);
        return ResponseEntity.ok(testAcceptanceMapper.toDTO(test));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id){
        testAcceptanceService.deleteTest(id);
        return ResponseEntity.noContent().build();
    }
}
