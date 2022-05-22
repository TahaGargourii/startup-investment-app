package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.Startupper;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.model.UserRole;
import design.startupInvestment.springboot.repository.StartupperRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.StartupperRequest;
import design.startupInvestment.springboot.security.dto.response.StartupperResponse;
import design.startupInvestment.springboot.security.mapper.StartupperMapper;
import design.startupInvestment.springboot.security.service.StartupperService;
import design.startupInvestment.springboot.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class StartupperDetailsServiceImpl implements StartupperService {

    @Autowired
    StartupperRepository startupperRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    public ApiResponse getAllStartuppers() {
        try {
            List<Startupper> startuppers = new ArrayList<Startupper>();
            List<StartupperResponse> startuppersResponses = new ArrayList<>();
            startupperRepository.findAll().forEach(startuppers::add);

            for (Startupper startupper : startuppers) {
                StartupperResponse startuppersResponse = StartupperMapper.INSTANCE.convertToStartupperResponse(startupper);
                startuppersResponses.add(startuppersResponse);
            }
            return new ApiResponse(startuppersResponses, "USER IS NOT AN STARTUP", HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse getStartupperById(long id) {
        Optional<Startupper> startupperData = startupperRepository.findById(id);

        try {
            if (startupperData.isPresent()) {
                StartupperResponse startupper = StartupperMapper.INSTANCE.convertToStartupperResponse(startupperData.get());
                return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.NO_CONTENT, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse createStartupper(StartupperRequest startupperRequest) {
        try {

            boolean userExist = userRepository.existsByUsername(startupperRequest.getUser().getUsername()) || userRepository.existsByEmail(startupperRequest.getUser().getEmail());
            if (userExist) {
                return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
            User newUser = userService.registration(startupperRequest.getUser());
            newUser.setUserRole(UserRole.STARTUPPER);
            Startupper startupper = startupperRepository.save(new Startupper(newUser));

            return new ApiResponse(startupper, "USER IS NOT AN STARTUP", HttpStatus.OK, LocalDateTime.now());

        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse deleteStartupper(long id) {
        try {
            Optional<Startupper> startupper = startupperRepository.findById(id);
            if (startupper.isPresent()) {
                startupperRepository.deleteById(id);
                return new ApiResponse(null, "DELETED", HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }
    }

    public ApiResponse deleteAllStartuppers() {
        try {
            List<Startupper> startuppers = startupperRepository.findAll();
            if (!startuppers.isEmpty()) {
                startupperRepository.deleteAll();
                return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.NO_CONTENT, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }
    }


}
