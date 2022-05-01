package design.boilerplate.springboot.security.mapper;

import design.boilerplate.springboot.model.Startupper;
import design.boilerplate.springboot.security.dto.request.StartupperRequest;
import design.boilerplate.springboot.security.dto.response.StartupperResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface StartupperMapper {


    StartupperMapper INSTANCE = Mappers.getMapper(StartupperMapper.class);

    StartupperResponse convertToStartupperResponse(Startupper startupper);

    Startupper convertToStartupper(StartupperRequest startupperRequest);

}
