package design.boilerplate.springboot.security.mapper;

import design.boilerplate.springboot.model.Fond;
import design.boilerplate.springboot.security.dto.request.FondRequest;
import design.boilerplate.springboot.security.dto.response.FondResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FondMapper {

    FondMapper INSTANCE = Mappers.getMapper(FondMapper.class);

    FondResponse convertToFondResponse(Fond fond);

    Fond convertToFond(FondRequest fondRequest);

}
