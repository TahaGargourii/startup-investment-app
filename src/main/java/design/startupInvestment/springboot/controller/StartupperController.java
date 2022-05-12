package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.StartupperRequest;
import design.startupInvestment.springboot.security.service.StartupperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class StartupperController {

    @Autowired
    StartupperService startupperService;

    @GetMapping("/startuppers")
    public ResponseEntity<ApiResponse> getAllStartuppers() {
        ApiResponse apiResponse = startupperService.getAllStartuppers();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/startuppers/{id}")
    public ResponseEntity<ApiResponse> getStartupperById(@PathVariable("id") long id) {
        ApiResponse apiResponse = startupperService.getStartupperById(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/startuppers")
    public ResponseEntity<ApiResponse> createStartupper(@RequestBody StartupperRequest startupperRequest) {
        ApiResponse apiResponse = startupperService.createStartupper(startupperRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


    @DeleteMapping("/startuppers/{id}")
    public ResponseEntity<ApiResponse> deleteStartupper(@PathVariable("id") long id) {
        ApiResponse apiResponse = startupperService.deleteStartupper(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @DeleteMapping("/startuppers")
    public ResponseEntity<ApiResponse> deleteAllStartuppers() {
        ApiResponse apiResponse = startupperService.deleteAllStartuppers();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


}



