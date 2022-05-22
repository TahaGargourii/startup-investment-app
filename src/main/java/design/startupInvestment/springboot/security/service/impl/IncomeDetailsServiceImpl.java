package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.Income;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.repository.InvestorRepository;
import design.startupInvestment.springboot.repository.IncomeRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.IncomeRequest;
import design.startupInvestment.springboot.security.dto.response.IncomeResponse;
import design.startupInvestment.springboot.security.mapper.IncomeMapper;
import design.startupInvestment.springboot.security.service.IncomeService;
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
public class IncomeDetailsServiceImpl implements IncomeService {


    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    IncomeService incomeService;
    @Autowired
    UserService userService;

    @Override
    public ApiResponse getAllIncomes() {
        try {
            List<IncomeResponse> incomeResponses = new ArrayList<>();
            User user = userService.getConnectedStartupper();
            if (user != null) {
                List<Income> incomes = incomeRepository.findAll();
                if (!incomes.isEmpty()) {
                    for (Income income : incomes) {
                        IncomeResponse incomeResponse = IncomeMapper.INSTANCE.convertToIncomeResponse(income);
                        incomeResponses.add(incomeResponse);
                    }
                }
                return new ApiResponse(incomeResponses, null, HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse getIncomeById(long id) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                    Optional<Income> income = incomeRepository.findById(id);
                    if (income.isPresent()) {
                        return new ApiResponse(income.get(), null, HttpStatus.OK, LocalDateTime.now());
                    } else {
                        return new ApiResponse(null, "INCOME DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse createIncome(IncomeRequest incomeRequest) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Income income = IncomeMapper.INSTANCE.convertToIncome(incomeRequest);
                incomeRepository.save(income);
                return new ApiResponse(income, "INCOME CREATED", HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse updateIncome(long id, IncomeRequest incomeRequest) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<Income> incomeData = incomeRepository.findById(id);
                if (incomeData.isPresent()) {
                    Income _income = incomeData.get();
                    //_income.setSize(incomeRequest.getSize());
                    incomeRepository.save(_income);
                    return new ApiResponse(_income, "INCOMES IS UPDATED", HttpStatus.OK, LocalDateTime.now());
                } else {
                    return new ApiResponse(null, "INCOME DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }


    @Override
    public ApiResponse deleteIncome(long id) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                    Optional<Income> income = incomeRepository.findById(id);
                    if (income.isPresent()) {
                        incomeRepository.deleteById(id);
                        return new ApiResponse(null, "INCOME IS DELETED", HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN INCOME", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }

            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }

    }

    @Override
    public ApiResponse deleteAllIncomes() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getConnectedStartupper();
            if (user != null) {
                    List<Income> incomes = incomeRepository.findAll();
                    if (!incomes.isEmpty()) {
                        incomeRepository.deleteAll();
                        return new ApiResponse(null, "ALL INCOMES ARE DELETED", HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }
    }
}
