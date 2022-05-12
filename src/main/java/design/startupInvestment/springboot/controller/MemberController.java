package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.MemberRequest;
import design.startupInvestment.springboot.security.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<ApiResponse> getAllMembers() {
        ApiResponse apiResponse = memberService.getAllMembers();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<ApiResponse> getMemberById(@PathVariable("id") long id) {
        ApiResponse apiResponse = memberService.getMemberById(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/members")
    public ResponseEntity<ApiResponse> createMember(@RequestBody MemberRequest memberRequest) {
        ApiResponse apiResponse = memberService.createMember(memberRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


    @DeleteMapping("/members/{id}")
    public ResponseEntity<ApiResponse> deleteMember(@PathVariable("id") long id) {
        ApiResponse apiResponse = memberService.deleteMember(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @DeleteMapping("/members")
    public ResponseEntity<ApiResponse> deleteAllMembers() {
        ApiResponse apiResponse = memberService.deleteAllMembers();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}



