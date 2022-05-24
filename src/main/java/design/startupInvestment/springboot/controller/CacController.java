package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.CacRequest;
import design.startupInvestment.springboot.security.service.CacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author taha
 */
@RestController
@CrossOrigin
@RequestMapping("/api/cacs")
public class CacController {
    @Autowired
    CacService cacService;

    @GetMapping("/cacsByStartup")
    public ResponseEntity<ApiResponse> getAllCacsByStartup(@PathVariable("startupId") long startupId) {
        ApiResponse apiResponse = cacService.getAllCacsByStartup(startupId);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/cacsByStartupAndMonth")
    public ResponseEntity<ApiResponse> getAllCacsByStartupAndMonth(@RequestParam(required = false) long startupId, @RequestParam(required = false) String month) {
        ApiResponse apiResponse = cacService.getAllCacsByStartupAndMonth(startupId,month);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
/*
    @GetMapping("/cacs/{id}")
    public ResponseEntity<ApiResponse> getCacById(@PathVariable("id") long id) {
        ApiResponse apiResponse = cacService.getCacById(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }*/

    @PostMapping("/cacs")
    public ResponseEntity<ApiResponse> createCac(@RequestBody CacRequest cacRequest) {
        ApiResponse apiResponse = cacService.createCac(cacRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}
