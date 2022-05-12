package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.Investor;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.model.UserRole;
import design.startupInvestment.springboot.repository.InvestorRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.InvestorRequest;
import design.startupInvestment.springboot.security.dto.response.InvestorResponse;
import design.startupInvestment.springboot.security.mapper.InvestorMapper;
import design.startupInvestment.springboot.security.service.InvestorService;
import design.startupInvestment.springboot.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InvestorDetailsServiceImpl implements InvestorService {

    @Autowired
    UserService userService;
    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    UserRepository userRepository;

    public ApiResponse getAllInvestors() {
        try {
            List<InvestorResponse> investorResponses = new ArrayList<>();
            List<Investor> investors = investorRepository.findAll();
            if (!investors.isEmpty()) {
                for (Investor investor : investors) {
                    InvestorResponse investorResponse = InvestorMapper.INSTANCE.convertToInvestorResponse(investor);
                    investorResponses.add(investorResponse);
                }
                return new ApiResponse(investorResponses, null, HttpStatus.OK, LocalDateTime.now());
            }
            return new ApiResponse(investorResponses, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse getInvestorById(long id) {
        Optional<Investor> investorData = investorRepository.findById(id);

        if (investorData.isPresent()) {
            InvestorResponse investor = InvestorMapper.INSTANCE.convertToInvestorResponse(investorData.get());
            return new ApiResponse(investor, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        } else {
            return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        }
    }

    public ApiResponse createInvestor(InvestorRequest investorRequest) {
        try {
            boolean userExist = userRepository.existsByUsername(investorRequest.getUser().getUsername()) && userRepository.existsByEmail(investorRequest.getUser().getEmail());
            if (userExist) {
                return new ApiResponse(userExist, "userexist", HttpStatus.NO_CONTENT, LocalDateTime.now());

            }
            design.startupInvestment.springboot.model.User newUser = userService.registration(investorRequest.getUser());
            newUser.setUserRole(UserRole.INVESTOR);
            Investor investor = InvestorMapper.INSTANCE.convertToInvestor(investorRequest);
            investor.setUser(newUser);
            investorRepository.save(investor);
            return new ApiResponse(investor, "INVESTOR CREATED", HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "userexist", HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }
    }

    public ApiResponse updateInvestor(long id, InvestorRequest investorRequest) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            design.startupInvestment.springboot.model.User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    Optional<Investor> investorData = investorRepository.findById(id);
                    if (investorData.isPresent()) {
                        Investor investor = investorData.get();
                        investor.setInvestingStages(investorRequest.getInvestingStages());
                        investor.setTicketSize(investorRequest.getTicketSize());
                        investorRepository.save(investor);
                        //InvestorResponse investorResponse = InvestorMapper.INSTANCE.convertToInvestorResponse(investor);
                        return new ApiResponse(investor, "INVESTOR IS UPDATED", HttpStatus.OK, LocalDateTime.now());
                    } else {
                        return new ApiResponse(null, "INVESTOR DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                    }
                }
                return new ApiResponse(null, "NO UPDATE", HttpStatus.NO_CONTENT, LocalDateTime.now());
            }
            return null;
        } catch (Exception e) {
            return new ApiResponse(null, "userexist", HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }
    }

    public ApiResponse deleteInvestor(long id) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            design.startupInvestment.springboot.model.User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    Optional<Investor> investor = investorRepository.findById(id);
                    if (investor.isPresent()) {
                        investorRepository.deleteById(id);
                        return new ApiResponse(null, "INVESTOR IS DELETED", HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "userexist", HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "userexist", HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }
    }

    public ApiResponse deleteAllInvestors() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    List<Investor> investors = investorRepository.findAll();
                    if (!investors.isEmpty()) {
                        investorRepository.deleteAll();
                        return new ApiResponse(null, "ALL INVESTORS ARE DELETED", HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT INVESTOR", HttpStatus.OK, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "userexist", HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }
    }
}
