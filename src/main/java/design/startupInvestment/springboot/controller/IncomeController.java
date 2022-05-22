package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.IncomeRequest;
import design.startupInvestment.springboot.security.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author taha
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

    @GetMapping("/incomes")
    public ResponseEntity<ApiResponse> getAllIncomes() {
        ApiResponse apiResponse = incomeService.getAllIncomes();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/incomes/{id}")
    public ResponseEntity<ApiResponse> getIncomeById(@PathVariable("id") long id) {
        ApiResponse apiResponse = incomeService.getIncomeById(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/incomes")
    public ResponseEntity<ApiResponse> createIncome(@RequestBody IncomeRequest incomeRequest) {
        ApiResponse apiResponse = incomeService.createIncome(incomeRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


    @DeleteMapping("/incomes/{id}")
    public ResponseEntity<ApiResponse> deleteIncome(@PathVariable("id") long id) {
        ApiResponse apiResponse = incomeService.deleteIncome(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @DeleteMapping("/incomes")
    public ResponseEntity<ApiResponse> deleteAllIncomes() {
        ApiResponse apiResponse = incomeService.deleteAllIncomes();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}
