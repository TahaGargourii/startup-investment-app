package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.model.*;
import design.boilerplate.springboot.security.dto.response.FileResponse;
import design.boilerplate.springboot.security.mapper.FileMapper;
import design.boilerplate.springboot.security.mapper.FondMapper;
import design.boilerplate.springboot.repository.FondRepository;
import design.boilerplate.springboot.repository.InvestorRepository;
import design.boilerplate.springboot.repository.StartupRepository;
import design.boilerplate.springboot.repository.UserRepository;
import design.boilerplate.springboot.security.dto.request.FondRequest;
import design.boilerplate.springboot.security.dto.response.FondResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FondController {

    @Autowired
    FondRepository fondRepository;
    @Autowired
    StartupRepository startupRepository;
    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/fonds")
    public ResponseEntity<List<FondResponse>> getAllFonds(@RequestParam(required = false) String type, @RequestParam(required = false) String capTable) {
        try {
            Fond fond = new Fond();
            fond.setCapTable(capTable);
            fond.setType(type);
            /*file.setInvestor(inv);
            file.setStartup(field);*/
            ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
            Example<Fond> example = Example.of(fond, matcher);
            List<Fond> fonds = fondRepository.findAll(example);
            List<FondResponse> fondResponses = new ArrayList<>();
            for (Fond fond1 : fonds) {
                FondResponse fondResponse = FondMapper.INSTANCE.convertToFondResponse(fond1);
                //fondResponse.s
                fondResponses.add(fondResponse);
            }
            return new ResponseEntity<>(fondResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fonds/{id}")
    public ResponseEntity<FondResponse> getFondById(@PathVariable("id") long id) {
        Optional<Fond> fond = fondRepository.findById(id);

        if (fond.isPresent()) {
            FondResponse fondResponse = FondMapper.INSTANCE.convertToFondResponse(fond.get());
            return new ResponseEntity<>(fondResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/fonds/{startupId}/startup")
    public ResponseEntity<Fond> createFondByStartup(@RequestBody FondRequest fondRequest, @PathVariable("startupId") long startupId) {
        try {
            Startup startup = startupRepository.findById(startupId).get();
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (startup != null) {
                Fond fond = FondMapper.INSTANCE.convertToFond(fondRequest);
                fond.setStartup(startup);
                fond.setInvestor(user.getInvestor());
                fondRepository.save(fond);
                return new ResponseEntity<>(fond, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/startups/{startupId}/fonds")
    public ResponseEntity<List<FondResponse>> getAllFondsByStartup(@PathVariable(value = "startupId") Long startupId) {
        Optional<Startup> startup = startupRepository.findById(startupId);
        if (!startup.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Fond> fonds = fondRepository.findFondByStartup(startup.get());
        List<FondResponse> fondsResponses = new ArrayList<>();
        for (Fond fond : fonds) {
            FondResponse fondResponse = FondMapper.INSTANCE.convertToFondResponse(fond);
            fondsResponses.add(fondResponse);
        }
        return new ResponseEntity<>(fondsResponses, HttpStatus.OK);
    }

    @GetMapping("/investors/{investorId}/fonds")
    public ResponseEntity<List<FondResponse>> getAllFondsByInvestor(@PathVariable(value = "investorId") Long investorId) {
        Optional<Investor> investor = investorRepository.findById(investorId);
        if (!investor.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Fond> fonds = fondRepository.findFondByInvestor(investor.get());
        List<FondResponse> fondsResponses = new ArrayList<>();
        for (Fond fond : fonds) {
            FondResponse fondResponse = FondMapper.INSTANCE.convertToFondResponse(fond);
            fondsResponses.add(fondResponse);
        }
        return new ResponseEntity<>(fondsResponses, HttpStatus.OK);
    }

}



