package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Investor;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.security.dto.request.InvestorRequest;
import design.startupInvestment.springboot.security.dto.request.RegistrationRequest;
import design.startupInvestment.springboot.security.dto.response.InvestorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvestorMapper {

    InvestorMapper INSTANCE = Mappers.getMapper(InvestorMapper.class);

    InvestorResponse convertToInvestorResponse(Investor investor);

    Investor convertToInvestor(InvestorRequest investorRequest);
    Investor convertToInvestor(InvestorRequest investorRequest, User user);

    RegistrationRequest convertUserToRegistrationRequest(User user);


}
