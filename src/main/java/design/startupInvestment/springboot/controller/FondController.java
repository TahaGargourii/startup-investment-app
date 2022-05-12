package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.FondRequest;
import design.startupInvestment.springboot.security.service.FondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FondController {

    @Autowired
    FondService fondService;

    @GetMapping("/fonds")
    public ResponseEntity<ApiResponse> getAllFonds(@RequestParam(required = false) String type, @RequestParam(required = false) String capTable) {
        ApiResponse response = fondService.getAllFonds(type, capTable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/fonds/{id}")
    public ResponseEntity<ApiResponse> getFondByIdAndStartupOrInvestor(@PathVariable("id") long id) {
        ApiResponse response = fondService.getFondByIdAndStartupOrInvestor(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/fonds")
    public ResponseEntity<ApiResponse> createFondByStartup(@RequestBody FondRequest fondRequest) {
        ApiResponse response = fondService.createFondByStartup(fondRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/fonds/{startupId}/startups")
    public ResponseEntity<ApiResponse> getAllFondsByStartup(@PathVariable("startupId") long startupId) {
        ApiResponse response = fondService.getAllFondsByStartup(startupId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/fonds/investors")
    public ResponseEntity<ApiResponse> getAllFondsByInvestor() {
        ApiResponse response = fondService.getAllFondsByInvestor();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}



