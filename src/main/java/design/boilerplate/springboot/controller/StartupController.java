package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.exceptions.ApiExceptionResponse;

import design.boilerplate.springboot.model.*;
import design.boilerplate.springboot.security.mapper.StartupMapper;
import design.boilerplate.springboot.repository.StartupRepository;
import design.boilerplate.springboot.repository.StartupperRepository;
import design.boilerplate.springboot.repository.UserRepository;
import design.boilerplate.springboot.security.dto.request.StartupRequest;
import design.boilerplate.springboot.security.dto.response.StartupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class StartupController {

	@Autowired
	StartupRepository startupRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	StartupperRepository startupperRepository;

	@GetMapping("/startups")
	public ResponseEntity<List<StartupResponse>> getAllStartups(@RequestParam(required = false) String startupName) {
		try {
			Startup startup = new Startup();
			startup.setName(startupName);
			ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
			Example<Startup> example = Example.of(startup, matcher);
			List<Startup> startups = startupRepository.findAll(example);
			List<StartupResponse> startupResponses = new ArrayList<>();
			for (Startup startup1 : startups) {
				StartupResponse startupResponse = StartupMapper.INSTANCE.convertToStartupResponse(startup1);
				startupResponses.add(startupResponse);
			}
			return new ResponseEntity<>(startupResponses, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/startups/{id}")
	public ResponseEntity<StartupResponse> getStartupById(@PathVariable("id") long id) {
		Optional<Startup> startupData = startupRepository.findById(id);

		if (startupData.isPresent()) {
			StartupResponse startup = StartupMapper.INSTANCE.convertToStartupResponse(startupData.get());
			return new ResponseEntity<>(startup, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/startups")
	public ResponseEntity<ApiExceptionResponse> createStartup(@RequestBody StartupRequest startupRequest) {
		final ApiExceptionResponse response = new ApiExceptionResponse(null,"STARTUP DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
			if (user != null && user.getUserRole().equals(UserRole.STARTUPPER) && user.getStartupper() != null) {
				//startupRequest.setStartupper(user.getStartupper());
				Startup startup = StartupMapper.INSTANCE.convertToStartup(startupRequest);
				startup.setStartupper(user.getStartupper());
				Startup _startup = startupRepository.save(startup);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/startups/{id}")
	public ResponseEntity<ApiExceptionResponse> updateStartup(@PathVariable("id") long id, @RequestBody StartupRequest startupRequest) {
		final ApiExceptionResponse response = new ApiExceptionResponse(null,"STARTUP DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
		try {
			Startup startup = startupRepository.findById(id).get();
			if (startup != null) {
				startup.setName(startupRequest.getStartupName());
				startupRepository.save(startup);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/startups/{id}")
	public ResponseEntity<ApiExceptionResponse> deleteStartup(@PathVariable("id") long id) {
		try {
			final ApiExceptionResponse notExistResponse = new ApiExceptionResponse(null,"STARTUP DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
			final ApiExceptionResponse existResponse = new ApiExceptionResponse(null,"STARTUPS IS DELETED", HttpStatus.BAD_REQUEST, LocalDateTime.now());
			Optional<Startup> startup = startupRepository.findById(id);
			if (startup.isPresent()) {
				startupRepository.deleteById(id);
				return ResponseEntity.status(existResponse.getStatus()).body(notExistResponse);
			} else {
				return ResponseEntity.status(notExistResponse.getStatus()).body(notExistResponse);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/startups")
	public ResponseEntity<ApiExceptionResponse> deleteAllStartups() {
		try {
			final ApiExceptionResponse notExistResponse = new ApiExceptionResponse(null,"STARTUP DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
			final ApiExceptionResponse existResponse = new ApiExceptionResponse(null,"ALL STARTUPS ARE DELETED", HttpStatus.BAD_REQUEST, LocalDateTime.now());
			List<Startup> startups = startupRepository.findAll();
			if (!startups.isEmpty()) {
				startupRepository.deleteAll();
				return ResponseEntity.status(existResponse.getStatus()).body(notExistResponse);
			} else {
				return ResponseEntity.status(notExistResponse.getStatus()).body(notExistResponse);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

/*	@GetMapping("/startups/startupper/{startupperId}")
	public ResponseEntity<List<StartupResponse>> findByStartupper(@PathVariable Long startupperId) {
		try {
			Optional<Startupper> startupper = startupperRepository.findById(startupperId);
			List<Startup> startups = startupRepository.f(startupper);
			List<StartupResponse> startupResponses = new ArrayList<>();
			for (Startup startup : startups) {
				StartupResponse startupResponse = StartupMapper.INSTANCE.convertToStartupResponse(startup);
				startupResponses.add(startupResponse);
			}
			return new ResponseEntity<>(startupResponses, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}*/
}



