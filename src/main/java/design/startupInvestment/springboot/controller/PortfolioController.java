package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.PortfolioRequest;
import design.startupInvestment.springboot.security.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author taha
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/portfolios")
    public ResponseEntity<ApiResponse> getAllPortfolios(@RequestParam(required = false) String type, @RequestParam(required = false) String capTable) {
        ApiResponse apiResponse = portfolioService.getAllPortfolios(type, capTable);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/portfolios/{id}")
    public ResponseEntity<ApiResponse> getPortfolioById(@PathVariable("id") long id) {
        ApiResponse apiResponse = portfolioService.getPortfolioById(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("/portfolios")
    public ResponseEntity<ApiResponse> createPortfolio(@RequestBody PortfolioRequest portfolioRequest) {
        ApiResponse apiResponse = portfolioService.createPortfolio(portfolioRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PutMapping("/portfolios/{id}")
    public ResponseEntity<ApiResponse> updatePortfolio(@PathVariable("id") long id, @RequestBody PortfolioRequest portfolioRequest) {
        ApiResponse apiResponse = portfolioService.updatePortfolio(id, portfolioRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @DeleteMapping("/portfolios/{id}")
    public ResponseEntity<ApiResponse> deletePortfolio(@PathVariable("id") long id) {
        ApiResponse apiResponse = portfolioService.deletePortfolio(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @DeleteMapping("/portfolios")
    public ResponseEntity<ApiResponse> deleteAllPortfolios() {
        ApiResponse apiResponse = portfolioService.deleteAllPortfolios();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/portfolios/investors/{investorId}")
    public ResponseEntity<ApiResponse> findByInvestor(@PathVariable("investorId") long investorId) {
        ApiResponse apiResponse = portfolioService.findByInvestor(investorId);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
}
