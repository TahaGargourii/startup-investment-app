package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.InvestorRequest;
import design.startupInvestment.springboot.security.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@CrossOrigin
public class InvestorController {

    @Autowired
    InvestorService investorService;

    @GetMapping("/investors")
    public ResponseEntity<ApiResponse> getAllInvestors() {
        ApiResponse response = investorService.getAllInvestors();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/investors/{id}")
    public ResponseEntity<ApiResponse> getInvestorById(@PathVariable("id") long id) {
        ApiResponse response = investorService.getInvestorById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/investors")
    public ResponseEntity<ApiResponse> createInvestor(@RequestBody InvestorRequest investorRequest) {
        ApiResponse response = investorService.createInvestor(investorRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/investors/{id}")
    public ResponseEntity<ApiResponse> updateInvestor(@PathVariable("id") long id, @RequestBody InvestorRequest investorRequest) {
        ApiResponse response = investorService.updateInvestor(id, investorRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/investors/{id}")
    public ResponseEntity<ApiResponse> deleteInvestor(@PathVariable("id") long id) {
        ApiResponse response = investorService.deleteInvestor(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/investors")
    public ResponseEntity<ApiResponse> deleteAllInvestors() {
        ApiResponse response = investorService.deleteAllInvestors();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}



