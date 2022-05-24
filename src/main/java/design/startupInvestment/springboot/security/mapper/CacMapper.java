package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.CAC;

import design.startupInvestment.springboot.security.dto.request.CacRequest;
import design.startupInvestment.springboot.security.dto.response.CacResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CacMapper {
    CacMapper INSTANCE = Mappers.getMapper(CacMapper.class);

    CacResponse convertToCacResponse(CAC cac);

    CAC convertToCac(CacRequest cacRequest);

}
