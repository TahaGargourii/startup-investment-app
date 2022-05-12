package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Startup;
import design.startupInvestment.springboot.security.dto.request.StartupRequest;
import design.startupInvestment.springboot.security.dto.response.StartupResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface StartupMapper {

    StartupMapper INSTANCE = Mappers.getMapper(StartupMapper.class);

    StartupResponse convertToStartupResponse(Startup startup);

    Startup convertToStartup(StartupRequest startupRequest);
}
