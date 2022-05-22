package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.AdminRequest;
import design.startupInvestment.springboot.security.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admins")
    public ResponseEntity<ApiResponse> getAllAdmins() {
        ApiResponse apiResponse = adminService.getAllAdmins();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<ApiResponse> getAdminById(@PathVariable("id") long id) {
        ApiResponse apiResponse = adminService.getAdminById(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/admins")
    public ResponseEntity<ApiResponse> createAdmin(@RequestBody AdminRequest adminRequest) {
        ApiResponse apiResponse = adminService.createAdmin(adminRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


    @DeleteMapping("/admins/{id}")
    public ResponseEntity<ApiResponse> deleteAdmin(@PathVariable("id") long id) {
        ApiResponse apiResponse = adminService.deleteAdmin(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @DeleteMapping("/admins")
    public ResponseEntity<ApiResponse> deleteAllAdmins() {
        ApiResponse apiResponse = adminService.deleteAllAdmins();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


}



