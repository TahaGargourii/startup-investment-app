package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.Startup;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.model.UserRole;
import design.startupInvestment.springboot.repository.StartupRepository;
import design.startupInvestment.springboot.repository.StartupperRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.StartupRequest;
import design.startupInvestment.springboot.security.dto.response.StartupResponse;
import design.startupInvestment.springboot.security.mapper.StartupMapper;
import design.startupInvestment.springboot.security.service.StartupService;
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
public class StartupDetailsServiceImpl implements StartupService {

    @Autowired
    StartupRepository startupRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    StartupperRepository startupperRepository;

    @Autowired
    UserService userService;
    public ApiResponse getAllStartups() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            design.startupInvestment.springboot.model.User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            List<StartupResponse> startupResponses = new ArrayList<>();
            if (user != null) {
                if (user.getStartupper() != null && user.getUserRole().equals(UserRole.STARTUPPER)) {
                    List<Startup> startups = startupRepository.findAll();
                    if (!startups.isEmpty()) {
                        for (Startup startup : startups) {
                            StartupResponse startupResponse = StartupMapper.INSTANCE.convertToStartupResponse(startup);
                            startupResponses.add(startupResponse);
                        }
                        return new ApiResponse(startupResponses, null, HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
    }

    public ApiResponse getAllStartupsByStartupper() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            design.startupInvestment.springboot.model.User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            List<StartupResponse> startupResponses = new ArrayList<>();
            if (user != null) {
                if (user.getStartupper() != null && user.getUserRole().equals(UserRole.STARTUPPER)) {
                    List<Startup> startups = startupRepository.findAllByStartupper(user.getStartupper());
                    if (!startups.isEmpty()) {
                        for (Startup startup : startups) {
                            StartupResponse startupResponse = StartupMapper.INSTANCE.convertToStartupResponse(startup);
                            startupResponses.add(startupResponse);
                        }
                        return new ApiResponse(startupResponses, null, HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
    }

    public ApiResponse getStartupById(long id) {
        Optional<Startup> startupData = startupRepository.findById(id);

        if (startupData.isPresent()) {
            StartupResponse startup = StartupMapper.INSTANCE.convertToStartupResponse(startupData.get());
            return new ApiResponse(startup, null, HttpStatus.OK, LocalDateTime.now());
        } else {
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        }
    }

    public ApiResponse createStartup(StartupRequest startupRequest) {
        final ApiResponse response = new ApiResponse(null, "STARTUP DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        try {
            User user = userService.getConnectedStartupper();
            /// hello
            if (user != null) {
                Startup startup = StartupMapper.INSTANCE.convertToStartup(startupRequest);
                startup.setStartupper(user.getStartupper());
                Startup _startup = startupRepository.save(startup);
                return new ApiResponse(startup, null, HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
            }
        } catch (Exception e) {
            return null;
        }
    }

    public ApiResponse updateStartup(long id, StartupRequest startupRequest) {
        final ApiResponse response = new ApiResponse(null, "STARTUP DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        try {
            Startup startup = startupRepository.findById(id).get();
            if (startup != null) {
                startup.setName(startupRequest.getName());
                startupRepository.save(startup);
                return new ApiResponse(startup, null, HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(startup, null, HttpStatus.OK, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        }
    }

    public ApiResponse deleteStartup(long id) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            design.startupInvestment.springboot.model.User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getStartupper() != null && user.getUserRole().equals(UserRole.STARTUPPER)) {
                    Optional<Startup> startup = startupRepository.findById(id);
                    if (startup.isPresent()) {
                        startupRepository.deleteById(id);
                        return new ApiResponse(null, "STARTUP IS DELETED", HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        }
    }

    public ApiResponse deleteAllStartups() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getStartupper() != null && user.getUserRole().equals(UserRole.STARTUPPER)) {
                    List<Startup> startups = startupRepository.findAll();
                    if (!startups.isEmpty()) {
                        startupRepository.deleteAll();
                        return new ApiResponse(null, "ALL STARTUPS ARE DELETED", HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        }
    }

}
