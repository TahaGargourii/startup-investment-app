package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Income;
import design.startupInvestment.springboot.security.dto.request.IncomeRequest;
import design.startupInvestment.springboot.security.dto.response.IncomeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncomeMapper {
    IncomeMapper INSTANCE = Mappers.getMapper(IncomeMapper.class);

    IncomeResponse convertToIncomeResponse(Income income);

    Income convertToIncome(IncomeRequest incomeRequest);

}
