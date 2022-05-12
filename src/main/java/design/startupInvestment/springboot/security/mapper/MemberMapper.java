package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Member;
import design.startupInvestment.springboot.security.dto.request.MemberRequest;
import design.startupInvestment.springboot.security.dto.response.MemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberResponse convertToMemberResponse(Member member);

    Member convertToMember(MemberRequest memberRequest);


}
