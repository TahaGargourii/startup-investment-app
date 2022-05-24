package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Revenue;
import design.startupInvestment.springboot.security.dto.request.RevenueRequest;
import design.startupInvestment.springboot.security.dto.response.RevenueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RevenueMapper {
    RevenueMapper INSTANCE = Mappers.getMapper(RevenueMapper.class);

    RevenueResponse convertToRevenueResponse(Revenue revenue);

    Revenue convertToRevenue(RevenueRequest revenueRequest);

}
