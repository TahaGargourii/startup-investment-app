package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.exceptions.ApiExceptionResponse;
import design.boilerplate.springboot.model.Investor;
import design.boilerplate.springboot.model.Portfolio;
import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.UserRole;
import design.boilerplate.springboot.repository.InvestorRepository;
import design.boilerplate.springboot.repository.PortfolioRepository;
import design.boilerplate.springboot.repository.UserRepository;
import design.boilerplate.springboot.security.dto.request.PortfolioRequest;
import design.boilerplate.springboot.security.dto.response.PortfolioResponse;
import design.boilerplate.springboot.security.mapper.PortfolioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author taha
 */
@RestController
@RequestMapping("/api")
public class PortfolioController {
    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InvestorRepository investorRepository;

    @GetMapping("/portfolios")
    public ResponseEntity<ApiExceptionResponse> getAllPortfolios() {
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
                        return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(portfolioResponses, null, HttpStatus.OK, LocalDateTime.now()));
                    }
                } else {
                    return new ResponseEntity<>(new ApiExceptionResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now()));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/portfolios/{id}")
    public ResponseEntity<ApiExceptionResponse> getPortfolioById(@PathVariable("id") long id) {

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    Optional<Portfolio> portfolio = portfolioRepository.findById(id);
                    if (portfolio.isPresent()) {
                        PortfolioResponse portfolioResponse = PortfolioMapper.INSTANCE.convertToPortfolioResponse(portfolio.get());
                        return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(portfolioResponse, null, HttpStatus.OK, LocalDateTime.now()));
                    } else {
                        return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(null, "PORTFOLIO DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now()));
                    }
                } else {
                    return new ResponseEntity<>(new ApiExceptionResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(null, "NO CREATION", HttpStatus.NO_CONTENT, LocalDateTime.now()));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/portfolios")
    public ResponseEntity<ApiExceptionResponse> createPortfolio(@RequestBody PortfolioRequest portfolioRequest) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    Portfolio portfolio = PortfolioMapper.INSTANCE.convertToPortfolio(portfolioRequest);
                    portfolio.setInvestor(user.getInvestor());
                    portfolioRepository.save(portfolio);
                    return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(portfolio, "PORTFOLIO CREATED", HttpStatus.OK, LocalDateTime.now()));
                } else {
                    return new ResponseEntity<>(new ApiExceptionResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(null, "NO CREATION", HttpStatus.NO_CONTENT, LocalDateTime.now()));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/portfolios/{id}")
    public ResponseEntity<ApiExceptionResponse> updatePortfolio(@PathVariable("id") long id, @RequestBody PortfolioRequest portfolioRequest) {
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
                        return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(_portfolio, "PORTFOLIOS IS UPDATED", HttpStatus.OK, LocalDateTime.now()));
                    } else {
                        return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(null, "PORTFOLIO DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now()));
                    }
                }
                return new ResponseEntity<>(new ApiExceptionResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(null, "NO UPDATE", HttpStatus.NO_CONTENT, LocalDateTime.now()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/portfolios/{id}")
    public ResponseEntity<ApiExceptionResponse> deletePortfolio(@PathVariable("id") long id) {
        try {
            final ApiExceptionResponse notExistResponse = new ApiExceptionResponse(null, "PORTFOLIO DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
            final ApiExceptionResponse existResponse = new ApiExceptionResponse(null, "PORTFOLIOS IS DELETED", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            Optional<Portfolio> portfolio = portfolioRepository.findById(id);
            if (portfolio.isPresent()) {
                portfolioRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(null, "PORTFOLIOS IS DELETED", HttpStatus.OK, LocalDateTime.now()));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(null, "PORTFOLIO DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now()));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/portfolios")
    public ResponseEntity<ApiExceptionResponse> deleteAllPortfolios() {
        try {
            List<Portfolio> portfolios = portfolioRepository.findAll();
            if (!portfolios.isEmpty()) {
                portfolioRepository.deleteAll();
                return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(null, "ALL PORTFOLIOS ARE DELETED", HttpStatus.OK, LocalDateTime.now()));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(null, null, HttpStatus.OK, LocalDateTime.now()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/portfolios/investors/{investorId}")
    public ResponseEntity<ApiExceptionResponse> findByInvestor(@PathVariable("investorId") long investorId) {
        try {
            Optional<Investor> investor = investorRepository.findById(investorId);
            if (investor.isPresent()) {
                Portfolio portfolio = portfolioRepository.findByInvestor(investor.get());
                if (portfolio == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiExceptionResponse(null, "PORTFOLIO DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now()));
                }
                return ResponseEntity.status(HttpStatus.OK).body(new ApiExceptionResponse(portfolio, null, HttpStatus.BAD_REQUEST, LocalDateTime.now()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiExceptionResponse(null, "INVESTOR DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
