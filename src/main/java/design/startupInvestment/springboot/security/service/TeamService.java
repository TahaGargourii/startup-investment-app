package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.TeamRequest;

public interface TeamService {

    ApiResponse getAllTeams();

    ApiResponse getTeamById(long id);

    ApiResponse createTeam(TeamRequest teamRequest);

    ApiResponse updateTeam(long id, TeamRequest teamRequest);

    ApiResponse deleteTeam(long id);

    ApiResponse deleteAllTeams();

}
