package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.TeamRequest;
import design.startupInvestment.springboot.security.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author taha
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class TeamController {
    @Autowired
    TeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<ApiResponse> getAllTeams() {
        ApiResponse apiResponse = teamService.getAllTeams();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<ApiResponse> getTeamById(@PathVariable("id") long id) {
        ApiResponse apiResponse = teamService.getTeamById(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/teams")
    public ResponseEntity<ApiResponse> createTeam(@RequestBody TeamRequest teamRequest) {
        ApiResponse apiResponse = teamService.createTeam(teamRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


    @DeleteMapping("/teams/{id}")
    public ResponseEntity<ApiResponse> deleteTeam(@PathVariable("id") long id) {
        ApiResponse apiResponse = teamService.deleteTeam(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @DeleteMapping("/teams")
    public ResponseEntity<ApiResponse> deleteAllTeams() {
        ApiResponse apiResponse = teamService.deleteAllTeams();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}
