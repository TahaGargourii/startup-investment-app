package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.CAC;
import design.startupInvestment.springboot.model.Revenue;
import design.startupInvestment.springboot.model.Startup;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.repository.InvestorRepository;
import design.startupInvestment.springboot.repository.RevenueRepository;
import design.startupInvestment.springboot.repository.StartupRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.RevenueRequest;
import design.startupInvestment.springboot.security.dto.response.CacResponse;
import design.startupInvestment.springboot.security.dto.response.RevenueResponse;
import design.startupInvestment.springboot.security.mapper.CacMapper;
import design.startupInvestment.springboot.security.mapper.RevenueMapper;
import design.startupInvestment.springboot.security.service.RevenueService;
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
public class RevenueDetailsServiceImpl implements RevenueService {


    @Autowired
    RevenueRepository revenueRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    RevenueService revenueService;
    @Autowired
    UserService userService;
    @Autowired
    StartupRepository startupRepository;
    @Override
    public ApiResponse getAllRevenuesByStartup(long startupId) {
        try {
            List<RevenueResponse> revenueResponses = new ArrayList<>();
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<Startup> startup = startupRepository.findById(startupId);
                if (!startup.isPresent()) {
                    return new ApiResponse(revenueResponses, null, HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
                List<Revenue> revenues = revenueRepository.findByStartup(startup.get());
                if (!revenues.isEmpty()) {
                    for (Revenue revenue : revenues) {
                        RevenueResponse revenueResponse = RevenueMapper.INSTANCE.convertToRevenueResponse(revenue);
                        revenueResponses.add(revenueResponse);
                    }
                }
                return new ApiResponse(revenueResponses, null, HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse getAllRevenuesByStartupAndYear(long startupId, String year) {
        try {
            List<RevenueResponse> revenueResponses = new ArrayList<>();
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<Startup> startup = startupRepository.findById(startupId);
                if (!startup.isPresent()) {
                    return new ApiResponse(revenueResponses, null, HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
                List<Revenue> revenues = revenueRepository.findByStartupAndYear(startup.get(),year);
                if (!revenues.isEmpty()) {
                    for (Revenue revenue : revenues) {
                        RevenueResponse revenueResponse = RevenueMapper.INSTANCE.convertToRevenueResponse(revenue);
                        revenueResponses.add(revenueResponse);
                    }
                }
                return new ApiResponse(revenueResponses, null, HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }
    @Override
    public ApiResponse getRevenueById(long id) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                    Optional<Revenue> revenue = revenueRepository.findById(id);
                    if (revenue.isPresent()) {
                        return new ApiResponse(revenue.get(), null, HttpStatus.OK, LocalDateTime.now());
                    } else {
                        return new ApiResponse(null, "REVENUE DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse createRevenue(RevenueRequest revenueRequest) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<Startup> startup = startupRepository.findById(revenueRequest.getStartupId());
                if (!startup.isPresent()) {
                    return new ApiResponse(null, null, HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
                Revenue revenue = RevenueMapper.INSTANCE.convertToRevenue(revenueRequest);
                revenue.setStartup(startup.get());
                revenueRepository.save(revenue);
                return new ApiResponse(revenue, "REVENUE CREATED", HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

}
