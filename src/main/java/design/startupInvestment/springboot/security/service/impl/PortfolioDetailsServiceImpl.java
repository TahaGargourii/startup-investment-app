package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.Investor;
import design.startupInvestment.springboot.model.Portfolio;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.model.UserRole;
import design.startupInvestment.springboot.repository.InvestorRepository;
import design.startupInvestment.springboot.repository.PortfolioRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.PortfolioRequest;
import design.startupInvestment.springboot.security.dto.response.PortfolioResponse;
import design.startupInvestment.springboot.security.mapper.PortfolioMapper;
import design.startupInvestment.springboot.security.service.PortfolioService;
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
public class PortfolioDetailsServiceImpl implements PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    PortfolioService portfolioService;

    @Override
    public ApiResponse getAllPortfolios(String type, String capTable) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            List<PortfolioResponse> portfolioResponses = new ArrayList<>();
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    List<Portfolio> portfolios = portfolioRepository.findAll();
                    if (!portfolios.isEmpty()) {
                        for (Portfolio portfolio : portfolios) {
                            PortfolioResponse portfolioResponse = PortfolioMapper.INSTANCE.convertToPortfolioResponse(portfolio);
                            portfolioResponses.add(portfolioResponse);
                        }
                    }
                    return new ApiResponse(portfolioResponses, null, HttpStatus.OK, LocalDateTime.now());
                } else {
                    return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse getPortfolioById(long id) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    Optional<Portfolio> portfolio = portfolioRepository.findById(id);
                    if (portfolio.isPresent()) {
                        return new ApiResponse(portfolio.get(), null, HttpStatus.OK, LocalDateTime.now());
                    } else {
                        return new ApiResponse(null, "PORTFOLIO DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, "NO CREATION", HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ApiResponse createPortfolio(PortfolioRequest portfolioRequest) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    Portfolio portfolio = PortfolioMapper.INSTANCE.convertToPortfolio(portfolioRequest);
                    portfolio.setInvestor(user.getInvestor());
                    portfolioRepository.save(portfolio);
                    return new ApiResponse(null, "PORTFOLIO CREATED", HttpStatus.OK, LocalDateTime.now());
                } else {
                    return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, "NO CREATION", HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ApiResponse updatePortfolio(long id, PortfolioRequest portfolioRequest) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    Optional<Portfolio> portfolioData = portfolioRepository.findById(id);
                    if (portfolioData.isPresent()) {
                        Portfolio _portfolio = portfolioData.get();
                        _portfolio.setSize(portfolioRequest.getSize());
                        portfolioRepository.save(_portfolio);
                        return new ApiResponse(_portfolio, "PORTFOLIOS IS UPDATED", HttpStatus.OK, LocalDateTime.now());
                    } else {
                        return new ApiResponse(null, "PORTFOLIO DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                    }
                }
                return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
            return new ApiResponse(null, "NO UPDATE", HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "NO UPDATE", HttpStatus.NO_CONTENT, LocalDateTime.now());
        }
    }


    @Override
    public ApiResponse deletePortfolio(long id) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    Optional<Portfolio> portfolio = portfolioRepository.findById(id);
                    if (portfolio.isPresent()) {
                        portfolioRepository.deleteById(id);
                        return new ApiResponse(null, "PORTFOLIO IS DELETED", HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN PORTFOLIO", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "USER IS NOT AN PORTFOLIO", HttpStatus.BAD_REQUEST, LocalDateTime.now());

        }

    }

    @Override
    public ApiResponse deleteAllPortfolios() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    List<Portfolio> portfolios = portfolioRepository.findAll();
                    if (!portfolios.isEmpty()) {
                        portfolioRepository.deleteAll();
                        return new ApiResponse(null, "ALL PORTFOLIOS ARE DELETED", HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN PORTFOLIO", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "USER IS NOT AN PORTFOLIO", HttpStatus.BAD_REQUEST, LocalDateTime.now());

        }
    }

    @Override
    public ApiResponse findByInvestor(long investorId) {
        try {
            Optional<Investor> investor = investorRepository.findById(investorId);
            if (investor.isPresent()) {
                Portfolio portfolio = portfolioRepository.findByInvestor(investor.get());
                if (portfolio == null) {
                    return new ApiResponse(null, "portfolio does not exist", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
                return new ApiResponse(portfolio, null, HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
            return new ApiResponse(null, "USER IS NOT AN Investor", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "USER IS NOT AN PORTFOLIO", HttpStatus.BAD_REQUEST, LocalDateTime.now());

        }
    }
}
