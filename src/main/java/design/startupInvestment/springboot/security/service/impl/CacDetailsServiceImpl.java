package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.CAC;

import design.startupInvestment.springboot.model.Startup;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.repository.InvestorRepository;
import design.startupInvestment.springboot.repository.CacRepository;
import design.startupInvestment.springboot.repository.StartupRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.CacRequest;
import design.startupInvestment.springboot.security.dto.response.CacResponse;
import design.startupInvestment.springboot.security.mapper.CacMapper;
import design.startupInvestment.springboot.security.service.CacService;
import design.startupInvestment.springboot.security.service.CacService;
import design.startupInvestment.springboot.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class CacDetailsServiceImpl implements CacService {


    @Autowired
    CacRepository cacRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    CacService cacService;
    @Autowired
    UserService userService;
    @Autowired
    StartupRepository startupRepository;

    @Override
    public ApiResponse getAllCacsByStartup(long startupId) {
        try {
            List<CacResponse> cacResponses = new ArrayList<>();
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<Startup> startup = startupRepository.findById(startupId);
                if (!startup.isPresent()) {
                    return new ApiResponse(cacResponses, null, HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
                List<CAC> cacs = cacRepository.findByStartup(startup.get());
                if (!cacs.isEmpty()) {
                    for (CAC cac : cacs) {
                        CacResponse cacResponse = CacMapper.INSTANCE.convertToCacResponse(cac);
                        cacResponses.add(cacResponse);
                    }
                }
                return new ApiResponse(cacResponses, null, HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse getAllCacsByStartupAndMonth(long startupId, String month) {
        try {
            List<CacResponse> cacResponses = new ArrayList<>();
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<Startup> startup = startupRepository.findById(startupId);
                if (!startup.isPresent()) {
                    return new ApiResponse(cacResponses, null, HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
                List<CAC> cacs = cacRepository.findByStartupAndMonth(startup.get(),month);
                if (!cacs.isEmpty()) {
                    for (CAC cac : cacs) {
                        CacResponse cacResponse = CacMapper.INSTANCE.convertToCacResponse(cac);
                        cacResponses.add(cacResponse);
                    }
                }
                return new ApiResponse(cacResponses, null, HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse createCac(CacRequest cacRequest) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<Startup> startup = startupRepository.findById(cacRequest.getStartupId());
                if (!startup.isPresent()) {
                    return new ApiResponse(null, null, HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
                CAC cac = CacMapper.INSTANCE.convertToCac(cacRequest);
                cac.setStartup(startup.get());
                cacRepository.save(cac);
                return new ApiResponse(cac, "CAC CREATED", HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }


}
