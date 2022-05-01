package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.exceptions.ApiExceptionResponse;
import design.boilerplate.springboot.model.Investor;
import design.boilerplate.springboot.security.dto.response.InvestorResponse;
import design.boilerplate.springboot.security.dto.response.InvestorResponse;
import design.boilerplate.springboot.security.mapper.InvestorMapper;
import design.boilerplate.springboot.security.mapper.InvestorMapper;
import design.boilerplate.springboot.model.Investor;
import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.UserRole;
import design.boilerplate.springboot.repository.InvestorRepository;
import design.boilerplate.springboot.repository.UserRepository;
import design.boilerplate.springboot.security.dto.request.InvestorRequest;
import design.boilerplate.springboot.security.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class InvestorController {

    @Autowired
    UserService userService;
    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/investors")
    public ResponseEntity<List<InvestorResponse>> getAllInvestors() {
        try {
            List<Investor> investors = new ArrayList<Investor>();
            List<InvestorResponse> investorsResponses = new ArrayList<>();
            investorRepository.findAll().forEach(investors::add);

            for (Investor investor : investors) {
                InvestorResponse investorsResponse = InvestorMapper.INSTANCE.convertToInvestorResponse(investor);
                investorsResponses.add(investorsResponse);
            }
            return new ResponseEntity<>(investorsResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/investors/{id}")
    public ResponseEntity<InvestorResponse> getInvestorById(@PathVariable("id") long id) {
        Optional<Investor> investorData = investorRepository.findById(id);

        if (investorData.isPresent()) {
            InvestorResponse investor = InvestorMapper.INSTANCE.convertToInvestorResponse(investorData.get());
            return new ResponseEntity<>(investor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/investors")
    public ResponseEntity<Investor> createInvestor(@RequestBody InvestorRequest investorRequest) {
        try {
            boolean userExist = userRepository.existsByUsername(investorRequest.getUser().getUsername()) && userRepository.existsByEmail(investorRequest.getUser().getEmail());
            if (userExist) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            User newUser = userService.registration(investorRequest.getUser());
            newUser.setUserRole(UserRole.INVESTOR);
            Investor investor = InvestorMapper.INSTANCE.convertToInvestor(investorRequest);
            investor.setUser(newUser);
            investorRepository.save(investor);
            return new ResponseEntity<>(investor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PutMapping("/investors/{id}")
	public ResponseEntity<InvestorResponse> updateInvestor(@PathVariable("id") long id, @RequestBody InvestorRequest investorRequest) {
		Optional<Investor> investorData = investorRepository.findById(id);

		if (investorData.isPresent()) {
			Investor investor = investorData.get();
            investor.setInvestingStages(investorRequest.getInvestingStages());
			investor.setTicketSize(investorRequest.getTicketSize());
            investorRepository.save(investor);
            InvestorResponse investorResponse = InvestorMapper.INSTANCE.convertToInvestorResponse(investor);
			return new ResponseEntity<>(investorResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @DeleteMapping("/investors/{id}")
    public ResponseEntity<ApiExceptionResponse> deleteInvestor(@PathVariable("id") long id) {
        try {
            final ApiExceptionResponse notExistResponse = new ApiExceptionResponse(null,"INVESTOR DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
            final ApiExceptionResponse existResponse = new ApiExceptionResponse(null,"INVESTORS IS DELETED", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            Optional<Investor> investor = investorRepository.findById(id);
            if (investor.isPresent()) {
                investorRepository.deleteById(id);
                return ResponseEntity.status(existResponse.getStatus()).body(notExistResponse);
            } else {
                return ResponseEntity.status(notExistResponse.getStatus()).body(notExistResponse);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/investors")
    public ResponseEntity<ApiExceptionResponse> deleteAllInvestors() {
        try {
            final ApiExceptionResponse notExistResponse = new ApiExceptionResponse(null,"INVESTOR DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
            final ApiExceptionResponse existResponse = new ApiExceptionResponse(null,"ALL INVESTORS ARE DELETED", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            List<Investor> investors = investorRepository.findAll();
            if (!investors.isEmpty()) {
                investorRepository.deleteAll();
                return ResponseEntity.status(existResponse.getStatus()).body(notExistResponse);
            } else {
                return ResponseEntity.status(notExistResponse.getStatus()).body(notExistResponse);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



