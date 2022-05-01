package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.exceptions.ApiExceptionResponse;
import design.boilerplate.springboot.model.*;
import design.boilerplate.springboot.model.Startupper;
import design.boilerplate.springboot.repository.StartupperRepository;
import design.boilerplate.springboot.repository.UserRepository;
import design.boilerplate.springboot.security.dto.request.StartupperRequest;
import design.boilerplate.springboot.security.dto.response.FileResponse;
import design.boilerplate.springboot.security.dto.response.StartupperResponse;
import design.boilerplate.springboot.security.dto.response.StartupperResponse;
import design.boilerplate.springboot.security.mapper.FileMapper;
import design.boilerplate.springboot.security.mapper.StartupperMapper;
import design.boilerplate.springboot.security.mapper.StartupperMapper;
import design.boilerplate.springboot.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StartupperController {

	@Autowired
	StartupperRepository startupperRepository;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@GetMapping("/startuppers")
	public ResponseEntity<List<StartupperResponse>> getAllStartuppers() {
		try {
			List<Startupper> startuppers = new ArrayList<Startupper>();
			List<StartupperResponse> startuppersResponses = new ArrayList<>();
			startupperRepository.findAll().forEach(startuppers::add);

			for (Startupper startupper : startuppers) {
				StartupperResponse startuppersResponse = StartupperMapper.INSTANCE.convertToStartupperResponse(startupper);
				startuppersResponses.add(startuppersResponse);
			}
			return new ResponseEntity<>(startuppersResponses, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/startuppers/{id}")
	public ResponseEntity<ApiExceptionResponse> getStartupperById(@PathVariable("id") long id) {
		Optional<Startupper> startupperData = startupperRepository.findById(id);
		final ApiExceptionResponse notExistResponse = new ApiExceptionResponse(null,"STARTUPPER DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
		final ApiExceptionResponse existResponse;
		if (startupperData.isPresent()) {
			StartupperResponse startupper = StartupperMapper.INSTANCE.convertToStartupperResponse(startupperData.get());
			existResponse = new ApiExceptionResponse(startupper,"STARTUPPER DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
			return ResponseEntity.status(notExistResponse.getStatus()).body(existResponse);
		} else {
			return ResponseEntity.status(notExistResponse.getStatus()).body(notExistResponse);
		}
	}

	@PostMapping("/startuppers")
	public ResponseEntity<Startupper> createStartupper(@RequestBody StartupperRequest startupperRequest) {
		try {

			final ApiExceptionResponse notExistResponse = new ApiExceptionResponse(null,"STARTUPPER DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
			boolean userExist = userRepository.existsByUsername(startupperRequest.getUser().getUsername()) || userRepository.existsByEmail(startupperRequest.getUser().getEmail()) ;
			if (userExist) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			User newUser = userService.registration(startupperRequest.getUser());
			newUser.setUserRole(UserRole.STARTUPPER);
			Startupper startupper = startupperRepository.save(new Startupper(newUser));

			return new ResponseEntity<>(startupper, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

/*	@PutMapping("/startuppers/{id}")
	public ResponseEntity<Startupper> updateStartupper(@PathVariable("id") long id, @RequestBody Startupper startupper) {
		Optional<Startupper> startupperData = startupperRepository.findById(id);

		if (startupperData.isPresent()) {
			Startupper _startupper = startupperData.get();
			_startupper.setStartupperName(startupper.getStartupperName());
			_startupper.setDateOfUpload(startupper.getDateOfUpload());
			_startupper.setUploadedBy(startupper.getUploadedBy());
			_startupper.setField(startupper.getField());
			return new ResponseEntity<>(startupperRepository.save(_startupper), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/

	@DeleteMapping("/startuppers/{id}")
	public ResponseEntity<ApiExceptionResponse> deleteStartupper(@PathVariable("id") long id) {
		try {
			final ApiExceptionResponse notExistResponse = new ApiExceptionResponse(null,"STARTUPPER DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
			final ApiExceptionResponse existResponse = new ApiExceptionResponse(null,"STARTUPPERS IS DELETED", HttpStatus.BAD_REQUEST, LocalDateTime.now());
			Optional<Startupper> startupper = startupperRepository.findById(id);
			if (startupper.isPresent()) {
				startupperRepository.deleteById(id);
				return ResponseEntity.status(existResponse.getStatus()).body(notExistResponse);
			} else {
				return ResponseEntity.status(notExistResponse.getStatus()).body(notExistResponse);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/startuppers")
	public ResponseEntity<ApiExceptionResponse> deleteAllStartuppers() {
		try {
			final ApiExceptionResponse notExistResponse = new ApiExceptionResponse(null,"STARTUPPER DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
			final ApiExceptionResponse existResponse = new ApiExceptionResponse(null,"ALL STARTUPPERS ARE DELETED", HttpStatus.BAD_REQUEST, LocalDateTime.now());
			List<Startupper> startuppers = startupperRepository.findAll();
			if (!startuppers.isEmpty()) {
				startupperRepository.deleteAll();
				return ResponseEntity.status(existResponse.getStatus()).body(notExistResponse);
			} else {
				return ResponseEntity.status(notExistResponse.getStatus()).body(notExistResponse);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

/*	@GetMapping("/startuppers/startupperName")
	public ResponseEntity<Startupper> findByStartupperName(@RequestParam String startupperName) {
		try {
			Startupper startupper = startupperRepository.findById(startupperName);

			if (startupper == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(startupper, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}*/

}



