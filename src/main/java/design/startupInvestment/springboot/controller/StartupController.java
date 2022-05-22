package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.StartupRequest;
import design.startupInvestment.springboot.security.service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class StartupController {

	@Autowired
	StartupService startupService;

/*	@GetMapping("/startups")
	public ResponseEntity<ApiResponse> getAllStartups() {
		ApiResponse apiResponse = startupService.getAllStartups();
		return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
	}*/

	@GetMapping("/startups")
	public ResponseEntity<ApiResponse> getAllStartupsByStartupper() {
		ApiResponse apiResponse = startupService.getAllStartupsByStartupper();
		return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
	}

	@GetMapping("/startups/{id}")
	public ResponseEntity<ApiResponse> getStartupById(@PathVariable("id") long id) {
		ApiResponse apiResponse = startupService.getStartupById(id);
		return new ResponseEntity<>(apiResponse, apiResponse.getStatus());

	}

	@PostMapping("/startups")
	public ResponseEntity<ApiResponse> createStartup(@RequestBody StartupRequest startupRequest) {
		ApiResponse apiResponse = startupService.createStartup(startupRequest);
		return new ResponseEntity<>(apiResponse, apiResponse.getStatus());

	}

	@PutMapping("/startups/{id}")
	public ResponseEntity<ApiResponse> updateStartup(@PathVariable("id") long id, @RequestBody StartupRequest startupRequest) {
		ApiResponse apiResponse = startupService.updateStartup(id, startupRequest);
		return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
	}

	@DeleteMapping("/startups/{id}")
	public ResponseEntity<ApiResponse> deleteStartup(@PathVariable("id") long id) {
		ApiResponse apiResponse = startupService.deleteStartup(id);
		return new ResponseEntity<>(apiResponse, apiResponse.getStatus());

	}

	@DeleteMapping("/startups")
	public ResponseEntity<ApiResponse> deleteAllStartups() {
		ApiResponse apiResponse = startupService.deleteAllStartups();
		return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
	}
}



