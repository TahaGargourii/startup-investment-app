package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.RevenueRequest;
import design.startupInvestment.springboot.security.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author taha
 */
@RestController
@CrossOrigin
@RequestMapping("/api/revenues")
public class RevenueController {
    @Autowired
    RevenueService revenueService;

/*    @GetMapping("/revenues")
    public ResponseEntity<ApiResponse> getAllRevenues() {
        ApiResponse apiResponse = revenueService.getAllRevenues();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }*/

    @GetMapping("/revenuesByStartup")
    public ResponseEntity<ApiResponse> getAllRevenuesByStartup(@PathVariable("startupId") long startupId) {
        ApiResponse apiResponse = revenueService.getAllRevenuesByStartup(startupId);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/revenuesByStartupAndMonth")
    public ResponseEntity<ApiResponse> getAllRevenuesByStartupAndMonth(@RequestParam(required = false) long startupId, @RequestParam(required = false) String month) {
        ApiResponse apiResponse = revenueService.getAllRevenuesByStartupAndMonth(startupId,month);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/revenues/{id}")
    public ResponseEntity<ApiResponse> getRevenueById(@PathVariable("id") long id) {
        ApiResponse apiResponse = revenueService.getRevenueById(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/revenues")
    public ResponseEntity<ApiResponse> createRevenue(@RequestBody RevenueRequest revenueRequest) {
        ApiResponse apiResponse = revenueService.createRevenue(revenueRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


}
