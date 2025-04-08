package com.apiscrum.apiscrum.Controller;

import com.apiscrum.apiscrum.DTO.TestAcceptanceDTO;
import com.apiscrum.apiscrum.Entity.TestAcceptance;
import com.apiscrum.apiscrum.Mapper.TestAcceptanceMapper;
import com.apiscrum.apiscrum.Service.TestAcceptanceService;
import com.apiscrum.apiscrum.enums.TestAcceptanceState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/acceptance_tests")
public class TestAcceptanceController {

    private final TestAcceptanceService testAcceptanceService;

    private final TestAcceptanceMapper testAcceptanceMapper;
    public TestAcceptanceController(TestAcceptanceService testAcceptanceService, TestAcceptanceMapper testAcceptanceMapper){
        this.testAcceptanceService=testAcceptanceService;
        this.testAcceptanceMapper=testAcceptanceMapper;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('PRODUCT_OWNER', 'SCRUM_MANAGER')")
    public ResponseEntity<TestAcceptanceDTO> addTest(@Validated @RequestBody TestAcceptanceDTO testAcceptanceDTO){
        TestAcceptance testAcceptance=testAcceptanceMapper.toEntity(testAcceptanceDTO);
        TestAcceptance newTest=testAcceptanceService.addTest(testAcceptance);
        return new ResponseEntity<>(testAcceptanceMapper.toDTO(newTest), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PRODUCT_OWNER', 'SCRUM_MANAGER')")
    public ResponseEntity<TestAcceptanceDTO> getTestById(@PathVariable Long id){
        TestAcceptance test=testAcceptanceService.getTestById(id);
        return ResponseEntity.ok(testAcceptanceMapper.toDTO(test));
    }
    @GetMapping()
    @PreAuthorize("hasAnyRole('PRODUCT_OWNER', 'SCRUM_MANAGER')")
    public ResponseEntity<List<TestAcceptanceDTO>> getAllTests(){
        List<TestAcceptanceDTO> tests=testAcceptanceService.getAllTests().stream()
                .map(testAcceptanceMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tests);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PRODUCT_OWNER', 'SCRUM_MANAGER')")
    public ResponseEntity<TestAcceptanceDTO> updateTest(@PathVariable Long id, @RequestBody TestAcceptanceDTO testAcceptanceDTO){
        TestAcceptance test=testAcceptanceMapper.toEntity(testAcceptanceDTO);
        TestAcceptance updatedTest=testAcceptanceService.updateTest(id,test);
        return ResponseEntity.ok(testAcceptanceMapper.toDTO(updatedTest));
    }
    @PutMapping("/{id}/state")
    @PreAuthorize("hasAnyRole('PRODUCT_OWNER', 'SCRUM_MANAGER')")
    public ResponseEntity<TestAcceptanceDTO> updateTestState(@PathVariable Long id, @RequestBody TestAcceptanceState state){
        TestAcceptance test=testAcceptanceService.updateTestState(id,state);
        return ResponseEntity.ok(testAcceptanceMapper.toDTO(test));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PRODUCT_OWNER', 'SCRUM_MANAGER')")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id){
        testAcceptanceService.deleteTest(id);
        return ResponseEntity.noContent().build();
    }
}
