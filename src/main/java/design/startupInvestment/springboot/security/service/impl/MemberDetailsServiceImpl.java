package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.Member;
import design.startupInvestment.springboot.model.Team;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.repository.InvestorRepository;
import design.startupInvestment.springboot.repository.MemberRepository;
import design.startupInvestment.springboot.repository.TeamRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.MemberRequest;
import design.startupInvestment.springboot.security.dto.response.MemberResponse;
import design.startupInvestment.springboot.security.mapper.MemberMapper;
import design.startupInvestment.springboot.security.service.MemberService;
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
public class MemberDetailsServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserService userService;

    @Override
    public ApiResponse getAllMembers() {
        try {
            List<MemberResponse> memberResponses = new ArrayList<>();
            User user = userService.getConnectedStartupper();
            if (user != null) {
            List<Member> members = memberRepository.findAll();
            if (!members.isEmpty()) {
                for (Member member : members) {
                    MemberResponse memberResponse = MemberMapper.INSTANCE.convertToMemberResponse(member);
                    memberResponses.add(memberResponse);
                }
            }
            return new ApiResponse(memberResponses, null, HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }

        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse getMemberById(long id) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<Member> member = memberRepository.findById(id);
                if (member.isPresent()) {
                    return new ApiResponse(member.get(), null, HttpStatus.OK, LocalDateTime.now());
                } else {
                    return new ApiResponse(null, "MEMBER DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN Startupper", HttpStatus.BAD_REQUEST, LocalDateTime.now());

            }

        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }
    }

    @Override
    public ApiResponse createMember(MemberRequest memberRequest) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<Team> team = teamRepository.findById(memberRequest.getTeamId());
                if (team.isPresent()) {
                    Member member = MemberMapper.INSTANCE.convertToMember(memberRequest);
                    member.setTeam(team.get());
                    memberRepository.save(member);
                    return new ApiResponse(member, "MEMBER CREATED", HttpStatus.OK, LocalDateTime.now());
                } else {
                    return new ApiResponse(null, "TEAM DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN Startupper", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse updateMember(long id, MemberRequest memberRequest) {
        try {
            User user = userService.getConnectedInvestor();
            if (user != null) {
                Optional<Member> memberData = memberRepository.findById(id);
                if (memberData.isPresent()) {
                    Member _member = memberData.get();
                    memberRepository.save(_member);
                    return new ApiResponse(_member, "MEMBERS IS UPDATED", HttpStatus.OK, LocalDateTime.now());
                } else {
                    return new ApiResponse(null, "MEMBER DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }
            return new ApiResponse(null, "USER IS NOT AN Startupper", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }


    @Override
    public ApiResponse deleteMember(long id) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<Member> member = memberRepository.findById(id);
                if (member.isPresent()) {
                    memberRepository.deleteById(id);
                    return new ApiResponse(null, "MEMBER IS DELETED", HttpStatus.OK, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN Startupper", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }

            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }

    }

    @Override
    public ApiResponse deleteAllMembers() {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                List<Member> members = memberRepository.findAll();
                if (!members.isEmpty()) {
                    memberRepository.deleteAll();
                    return new ApiResponse(null, "ALL MEMBERS ARE DELETED", HttpStatus.OK, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN Startupper", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }

            return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        }
    }
}
