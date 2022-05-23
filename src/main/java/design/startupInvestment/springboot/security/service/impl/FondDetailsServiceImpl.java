package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.*;
import design.startupInvestment.springboot.repository.*;
import design.startupInvestment.springboot.security.dto.request.FondRequest;
import design.startupInvestment.springboot.security.dto.response.FondResponse;
import design.startupInvestment.springboot.security.mapper.FondMapper;
import design.startupInvestment.springboot.security.service.FondService;
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
public class FondDetailsServiceImpl implements FondService {

    @Autowired
    FondRepository fondRepository;
    @Autowired
    StartupRepository startupRepository;
    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Autowired
    PortfolioRepository portfolioRepository;

    public ApiResponse getAllFonds(String type, String capTable) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            List<FondResponse> fondResponses = new ArrayList<>();
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
            List<Fond> fonds = fondRepository.findAll();
            if (!fonds.isEmpty()) {
                for (Fond fond : fonds) {
                    FondResponse fondResponse = FondMapper.INSTANCE.convertToFondResponse(fond);
                    fondResponses.add(fondResponse);
                }
                return new ApiResponse(fondResponses, null, HttpStatus.OK, LocalDateTime.now());
            }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
             }
            return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        }
    }

    public ApiResponse getFondByIdAndStartupOrInvestor(long id) {
        try {
            User userInvestor = userService.getConnectedInvestor();
            User userStartupper = userService.getConnectedStartupper();
            Startup startup = null;
            Fond fond = null;
            if (userInvestor != null) {
                fond = fondRepository.findFondByIdAndInvestor(id, userInvestor.getInvestor());
            } else if (userStartupper != null) {
                startup = startupRepository.findByStartupper(userStartupper.getStartupper());
                fond = fondRepository.findFondByIdAndStartup(id, startup);
            } else {
                return new ApiResponse(null, "UNEXISTANT USER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
            if (fond != null) {
                FondResponse fondResponse = FondMapper.INSTANCE.convertToFondResponse(fond);
                return new ApiResponse(fondResponse, null, HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "FOND DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse createFondByStartup(FondRequest fondRequest) {
        try {
            User user = userService.getConnectedStartupper();

              if (user != null) {
            Optional<Startup> startup = startupRepository.findById(fondRequest.getStartupId());
            Optional<Investor> investor = investorRepository.findById(fondRequest.getInvestorId());
            Fond fond = FondMapper.INSTANCE.convertToFond(fondRequest);
            fond.setStartup(startup.get());
            fond.setInvestor(investor.get());
            int size = investor.get().getPortfolio().getSize();
            investor.get().getPortfolio().setSize(size++);
        //    startup.get().getPortfolio().setSize(size++);
            portfolioRepository.save(investor.get().getPortfolio());
            startup.get().setPortfolio(investor.get().getPortfolio());
            fondRepository.save(fond);
            return new ApiResponse(fond, "FOND CREATED", HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse getAllFondsByStartup(long startupId) {
        try {
            User user = userService.getConnectedStartupper();
            List<FondResponse> fondResponses = new ArrayList<>();
            if (user != null) {
                Optional<Startup> startup = startupRepository.findById(startupId);
                if (!startup.isPresent()) {
                    return new ApiResponse(fondResponses, null, HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
                List<Fond> fonds = fondRepository.findFondByStartup(startup);
                if (!fonds.isEmpty()) {
                    for (Fond fond : fonds) {
                        FondResponse fondResponse = FondMapper.INSTANCE.convertToFondResponse(fond);
                        fondResponses.add(fondResponse);
                    }
                    return new ApiResponse(fondResponses, null, HttpStatus.OK, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }

            return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse getAllFondsByInvestor() {
        try {
            User user = userService.getConnectedInvestor();
            List<FondResponse> fondResponses = new ArrayList<>();
            if (user != null) {
                List<Fond> fonds = fondRepository.findFondByInvestor(user.getInvestor());
                if (!fonds.isEmpty()) {
                    for (Fond fond : fonds) {
                        FondResponse fondResponse = FondMapper.INSTANCE.convertToFondResponse(fond);
                        fondResponses.add(fondResponse);
                    }
                    return new ApiResponse(fondResponses, null, HttpStatus.OK, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
            return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

}
