package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.ContactRequest;
import design.startupInvestment.springboot.security.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/contacts")
    public ResponseEntity<ApiResponse> getAllContacts() {
        ApiResponse apiResponse = contactService.getAllContacts();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<ApiResponse> getContactById(@PathVariable("id") long id) {
        ApiResponse apiResponse = contactService.getContactById(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/contacts")
    public ResponseEntity<ApiResponse> createContact(@RequestBody ContactRequest contactRequest) {
        ApiResponse apiResponse = contactService.createContact(contactRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<ApiResponse> deleteContact(@PathVariable("id") long id) {
        ApiResponse apiResponse = contactService.deleteContact(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @DeleteMapping("/contacts")
    public ResponseEntity<ApiResponse> deleteAllContacts() {
        ApiResponse apiResponse = contactService.deleteAllContacts();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}



