package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.response.UserResponse;
import design.startupInvestment.springboot.security.mapper.InvestorMapper;
import design.startupInvestment.springboot.security.mapper.StartupperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 
 * @author taha
 */
@RestController
@CrossOrigin
public class UserController {

	@Autowired
	UserRepository userRepository;
	StartupperMapper startupperMapper;
	InvestorMapper investorMapper;

	@GetMapping("/users/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable("id") long id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			//UserResponse userResponse = UserMapper.INSTANCE.convertToUserResponse(user.get());
			//return new ResponseEntity<>(userResponse, HttpStatus.OK);
			return null;
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

/*	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setUserName(user.getUserName());
			_user.setDateOfUpload(user.getDateOfUpload());
			_user.setField(user.getField());
			return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}*/

	@DeleteMapping("/users/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") long id) {
		try {
			final ApiResponse response = new ApiResponse(null, "USER DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
			Optional<User> userData = userRepository.findById(id);
			if (userData.isPresent()) {
				userRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return ResponseEntity.status(response.getStatus()).body(response);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}






}
