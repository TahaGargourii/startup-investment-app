package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.Admin;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.model.UserRole;
import design.startupInvestment.springboot.repository.AdminRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.AdminRequest;
import design.startupInvestment.springboot.security.dto.response.AdminResponse;
import design.startupInvestment.springboot.security.mapper.AdminMapper;
import design.startupInvestment.springboot.security.service.AdminService;
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
public class AdminDetailsServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    public ApiResponse getAllAdmins() {
        try {
            List<Admin> admins = new ArrayList<Admin>();
            List<AdminResponse> adminsResponses = new ArrayList<>();
            adminRepository.findAll().forEach(admins::add);

            for (Admin admin : admins) {
                AdminResponse adminsResponse = AdminMapper.INSTANCE.convertToAdminResponse(admin);
                adminsResponses.add(adminsResponse);
            }
            return new ApiResponse(adminsResponses, "USER IS NOT AN STARTUP", HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse getAdminById(long id) {
        Optional<Admin> adminData = adminRepository.findById(id);

        try {
            if (adminData.isPresent()) {
                AdminResponse admin = AdminMapper.INSTANCE.convertToAdminResponse(adminData.get());
                return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.NO_CONTENT, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse createAdmin(AdminRequest adminRequest) {
        try {

            boolean userExist = userRepository.existsByUsername(adminRequest.getUser().getUsername()) || userRepository.existsByEmail(adminRequest.getUser().getEmail());
            if (userExist) {
                return new ApiResponse(null, "ADMIN EXIST ", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
            User newUser = userService.registration(adminRequest.getUser());
            newUser.setUserRole(UserRole.ADMIN);
            Admin admin = adminRepository.save(new Admin(newUser));

            return new ApiResponse(admin, null, HttpStatus.OK, LocalDateTime.now());

        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse deleteAdmin(long id) {
        try {
            Optional<Admin> admin = adminRepository.findById(id);
            if (admin.isPresent()) {
                adminRepository.deleteById(id);
                return new ApiResponse(null, "DELETED", HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }
    }

    public ApiResponse deleteAllAdmins() {
        try {
            List<Admin> admins = adminRepository.findAll();
            if (!admins.isEmpty()) {
                adminRepository.deleteAll();
                return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUP", HttpStatus.NO_CONTENT, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }
    }


}
