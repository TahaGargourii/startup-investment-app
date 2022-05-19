package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Member;
import design.startupInvestment.springboot.model.Team;
import design.startupInvestment.springboot.security.dto.request.MemberRequest;
import design.startupInvestment.springboot.security.dto.response.MemberResponse;
import design.startupInvestment.springboot.security.dto.response.TeamResponse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-15T22:26:48+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberResponse convertToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponse memberResponse = new MemberResponse();

        memberResponse.setId( member.getId() );
        memberResponse.setFirstName( member.getFirstName() );
        memberResponse.setLastName( member.getLastName() );
        memberResponse.setPhoneNumber( member.getPhoneNumber() );
        memberResponse.setEmail( member.getEmail() );
        memberResponse.setPoste( member.getPoste() );
        memberResponse.setTeam( teamToTeamResponse( member.getTeam() ) );

        return memberResponse;
    }

    @Override
    public Member convertToMember(MemberRequest memberRequest) {
        if ( memberRequest == null ) {
            return null;
        }

        Member member = new Member();

        member.setFirstName( memberRequest.getFirstName() );
        member.setLastName( memberRequest.getLastName() );
        member.setPhoneNumber( memberRequest.getPhoneNumber() );
        member.setEmail( memberRequest.getEmail() );
        member.setPoste( memberRequest.getPoste() );

        return member;
    }

    protected TeamResponse teamToTeamResponse(Team team) {
        if ( team == null ) {
            return null;
        }

        TeamResponse teamResponse = new TeamResponse();

        teamResponse.setId( team.getId() );
        teamResponse.setName( team.getName() );
        teamResponse.setField( team.getField() );

        return teamResponse;
    }
}
