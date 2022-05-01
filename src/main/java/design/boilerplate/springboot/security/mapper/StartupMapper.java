package design.boilerplate.springboot.security.mapper;

import design.boilerplate.springboot.model.Startup;
import design.boilerplate.springboot.security.dto.request.StartupRequest;
import design.boilerplate.springboot.security.dto.response.StartupResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface StartupMapper {

    StartupMapper INSTANCE = Mappers.getMapper(StartupMapper.class);

    StartupResponse convertToStartupResponse(Startup startup);

    Startup convertToStartup(StartupRequest startupRequest);
}
