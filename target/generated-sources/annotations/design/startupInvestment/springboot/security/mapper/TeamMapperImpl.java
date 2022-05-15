package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Team;
import design.startupInvestment.springboot.security.dto.request.TeamRequest;
import design.startupInvestment.springboot.security.dto.response.TeamResponse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-15T22:26:49+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class TeamMapperImpl implements TeamMapper {

    @Override
    public TeamResponse convertToTeamResponse(Team team) {
        if ( team == null ) {
            return null;
        }

        TeamResponse teamResponse = new TeamResponse();

        teamResponse.setId( team.getId() );
        teamResponse.setName( team.getName() );
        teamResponse.setField( team.getField() );

        return teamResponse;
    }

    @Override
    public Team convertToTeam(TeamRequest teamRequest) {
        if ( teamRequest == null ) {
            return null;
        }

        Team team = new Team();

        team.setName( teamRequest.getName() );
        team.setField( teamRequest.getField() );

        return team;
    }
}
