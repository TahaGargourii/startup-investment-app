package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Team;
import design.startupInvestment.springboot.security.dto.request.TeamRequest;
import design.startupInvestment.springboot.security.dto.response.TeamResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamResponse convertToTeamResponse(Team team);

    Team convertToTeam(TeamRequest teamRequest);

}
