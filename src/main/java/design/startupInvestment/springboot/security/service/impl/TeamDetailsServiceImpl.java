package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.Team;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.model.UserRole;
import design.startupInvestment.springboot.repository.InvestorRepository;
import design.startupInvestment.springboot.repository.TeamRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.TeamRequest;
import design.startupInvestment.springboot.security.dto.response.TeamResponse;
import design.startupInvestment.springboot.security.mapper.TeamMapper;
import design.startupInvestment.springboot.security.service.TeamService;
import design.startupInvestment.springboot.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TeamDetailsServiceImpl implements TeamService {


    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;

    @Override
    public ApiResponse getAllTeams() {
        try {
            List<TeamResponse> teamResponses = new ArrayList<>();
           /* User user = userService.getConnectedStartupper();
            if (user != null) {*/
            List<Team> teams = teamRepository.findAll();
            if (!teams.isEmpty()) {
                for (Team team : teams) {
                    TeamResponse teamResponse = TeamMapper.INSTANCE.convertToTeamResponse(team);
                    teamResponses.add(teamResponse);
                }
            }
            return new ApiResponse(teamResponses, null, HttpStatus.OK, LocalDateTime.now());
          /*   } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }*/
        } catch (Exception e) {
            return new ApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse getTeamById(long id) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            design.startupInvestment.springboot.model.User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    Optional<Team> team = teamRepository.findById(id);
                    if (team.isPresent()) {
                        return new ApiResponse(team.get(), null, HttpStatus.OK, LocalDateTime.now());
                    } else {
                        return new ApiResponse(null, "TEAM DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, "NO CREATION", HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ApiResponse createTeam(TeamRequest teamRequest) {
        try {
           /* User user = userService.getConnectedStartupper();
            if (user != null) {*/
            Team team = TeamMapper.INSTANCE.convertToTeam(teamRequest);
            teamRepository.save(team);
            return new ApiResponse(team, "TEAM CREATED", HttpStatus.OK, LocalDateTime.now());
            /*} else {
                return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }*/
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ApiResponse updateTeam(long id, TeamRequest teamRequest) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            design.startupInvestment.springboot.model.User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    Optional<Team> teamData = teamRepository.findById(id);
                    if (teamData.isPresent()) {
                        Team _team = teamData.get();
                        //_team.setSize(teamRequest.getSize());
                        teamRepository.save(_team);
                        return new ApiResponse(_team, "TEAMS IS UPDATED", HttpStatus.OK, LocalDateTime.now());
                    } else {
                        return new ApiResponse(null, "TEAM DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                    }
                }
                return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
            return new ApiResponse(null, "NO UPDATE", HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "NO UPDATE", HttpStatus.NO_CONTENT, LocalDateTime.now());
        }
    }


    @Override
    public ApiResponse deleteTeam(long id) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            design.startupInvestment.springboot.model.User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    Optional<Team> team = teamRepository.findById(id);
                    if (team.isPresent()) {
                        teamRepository.deleteById(id);
                        return new ApiResponse(null, "TEAM IS DELETED", HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN TEAM", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "USER IS NOT AN TEAM", HttpStatus.BAD_REQUEST, LocalDateTime.now());

        }

    }

    @Override
    public ApiResponse deleteAllTeams() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null) {
                if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
                    List<Team> teams = teamRepository.findAll();
                    if (!teams.isEmpty()) {
                        teamRepository.deleteAll();
                        return new ApiResponse(null, "ALL TEAMS ARE DELETED", HttpStatus.OK, LocalDateTime.now());
                    }
                } else {
                    return new ApiResponse(null, "USER IS NOT AN TEAM", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "USER IS NOT AN TEAM", HttpStatus.BAD_REQUEST, LocalDateTime.now());

        }
    }
}
