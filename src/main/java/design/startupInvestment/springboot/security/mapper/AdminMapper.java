package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Admin;
import design.startupInvestment.springboot.security.dto.request.AdminRequest;
import design.startupInvestment.springboot.security.dto.response.AdminResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {


    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    AdminResponse convertToAdminResponse(Admin admin);

    Admin convertToAdmin(AdminRequest adminRequest);

}
