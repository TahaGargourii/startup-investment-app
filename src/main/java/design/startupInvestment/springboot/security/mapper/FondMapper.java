package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Fond;
import design.startupInvestment.springboot.security.dto.request.FondRequest;
import design.startupInvestment.springboot.security.dto.response.FondResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FondMapper {

    FondMapper INSTANCE = Mappers.getMapper(FondMapper.class);

    FondResponse convertToFondResponse(Fond fond);

    Fond convertToFond(FondRequest fondRequest);

}
