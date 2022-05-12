package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.MemberRequest;

public interface MemberService {

    ApiResponse getAllMembers();

    ApiResponse getMemberById(long id);

    ApiResponse createMember(MemberRequest memberRequest);

    ApiResponse updateMember(long id, MemberRequest memberRequest);

    ApiResponse deleteMember(long id);

    ApiResponse deleteAllMembers();


}
