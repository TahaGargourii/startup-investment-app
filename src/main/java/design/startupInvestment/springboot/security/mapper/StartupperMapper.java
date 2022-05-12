package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Startupper;
import design.startupInvestment.springboot.security.dto.request.StartupperRequest;
import design.startupInvestment.springboot.security.dto.response.StartupperResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface StartupperMapper {


    StartupperMapper INSTANCE = Mappers.getMapper(StartupperMapper.class);

    StartupperResponse convertToStartupperResponse(Startupper startupper);

    Startupper convertToStartupper(StartupperRequest startupperRequest);

}
