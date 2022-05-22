package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.AdminRequest;

public interface AdminService {
    ApiResponse getAllAdmins();

    ApiResponse getAdminById(long id);

    ApiResponse createAdmin(AdminRequest adminRequest);

    ApiResponse deleteAdmin(long id);

    ApiResponse deleteAllAdmins();


}
