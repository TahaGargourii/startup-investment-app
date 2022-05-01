package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.exceptions.ApiExceptionResponse;

import design.boilerplate.springboot.model.*;
import design.boilerplate.springboot.security.mapper.FileMapper;
import design.boilerplate.springboot.repository.FileRepository;
import design.boilerplate.springboot.repository.StartupperRepository;
import design.boilerplate.springboot.repository.UserRepository;
import design.boilerplate.springboot.security.dto.request.FileRequest;
import design.boilerplate.springboot.security.dto.response.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 *
 */
@RestController
@RequestMapping("/api")
public class FileController {

    /**
     *
     */
    @Autowired
    FileRepository fileRepository;
    /**
     *
     */
    @Autowired
    UserRepository userRepository;

    /**
     *
     */
    @Autowired
    StartupperRepository startupperRepository;

    /**
     * @param fileName
     * @param field
     * @param dateOfUpload
     * @return
     */
    @GetMapping("/files")
    public ResponseEntity<List<FileResponse>> getAllFiles(@RequestParam(required = false) String fileName, @RequestParam(required = false) String field, @RequestParam(required = false) Date dateOfUpload) {
        try {
            File file = new File();
            file.setFileName(fileName);
            file.setDateOfUpload(dateOfUpload);
            file.setField(field);
            ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
            Example<File> example = Example.of(file, matcher);
            List<File> files = fileRepository.findAll(example);
            List<FileResponse> fileResponses = new ArrayList<>();
            for (File file1 : files) {
                FileResponse fileResponse = FileMapper.INSTANCE.convertToFileResponse(file1);
                fileResponses.add(fileResponse);
            }
            return new ResponseEntity<>(fileResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/files/{id}")
    public ResponseEntity<FileResponse> getFileById(@PathVariable("id") long id) {
        Optional<File> fileData = fileRepository.findById(id);

        if (fileData.isPresent()) {
            FileResponse file = FileMapper.INSTANCE.convertToFileResponse(fileData.get());
            return new ResponseEntity<>(file, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * @param fileRequest
     * @return
     */
    @PostMapping("/files")
    public ResponseEntity<ApiExceptionResponse> createFile(@RequestBody FileRequest fileRequest) {
        final ApiExceptionResponse response = new ApiExceptionResponse(null,"FILE DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            if (user != null && user.getUserRole().equals(UserRole.STARTUPPER) && user.getStartupper() != null) {
                //fileRequest.setStartupper(user.getStartupper());
                File file = FileMapper.INSTANCE.convertToFile(fileRequest);
                file.setStartupper(user.getStartupper());
                File _file = fileRepository.save(file);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return ResponseEntity.status(response.getStatus()).body(response);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/files/{id}")
    public ResponseEntity<ApiExceptionResponse> updateFile(@PathVariable("id") long id, @RequestBody FileRequest fileRequest) {
        final ApiExceptionResponse response = new ApiExceptionResponse(null,"FILE DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        try {
            File file = fileRepository.findById(id).get();
            if (file != null) {
                file.setFileName(fileRequest.getFileName());
                file.setDateOfUpload(fileRequest.getDateOfUpload());
                file.setField(fileRequest.getField());
                fileRepository.save(file);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return ResponseEntity.status(response.getStatus()).body(response);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<ApiExceptionResponse> deleteFile(@PathVariable("id") long id) {
        try {
            final ApiExceptionResponse notExistResponse = new ApiExceptionResponse(null,"FILE DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
            final ApiExceptionResponse existResponse = new ApiExceptionResponse(null,"FILES IS DELETED", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            Optional<File> file = fileRepository.findById(id);
            if (file.isPresent()) {
                fileRepository.deleteById(id);
                return ResponseEntity.status(existResponse.getStatus()).body(notExistResponse);
            } else {
                return ResponseEntity.status(notExistResponse.getStatus()).body(notExistResponse);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/files")
    public ResponseEntity<ApiExceptionResponse> deleteAllFiles() {
        try {
            final ApiExceptionResponse notExistResponse = new ApiExceptionResponse(null,"FILE DOES NOT EXIST", HttpStatus.OK, LocalDateTime.now());
            final ApiExceptionResponse existResponse = new ApiExceptionResponse(null,"ALL FILES ARE DELETED", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            List<File> files = fileRepository.findAll();
            if (!files.isEmpty()) {
                fileRepository.deleteAll();
                return ResponseEntity.status(existResponse.getStatus()).body(notExistResponse);
            } else {
                return ResponseEntity.status(notExistResponse.getStatus()).body(notExistResponse);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/files/startupper/{startupperId}")
    public ResponseEntity<List<FileResponse>> findByStartupper(@PathVariable Long startupperId) {
        try {
            Optional<Startupper> startupper = startupperRepository.findById(startupperId);
            List<File> files = fileRepository.findByStartupper(startupper);
            List<FileResponse> fileResponses = new ArrayList<>();
            for (File file : files) {
                FileResponse fileResponse = FileMapper.INSTANCE.convertToFileResponse(file);
                fileResponses.add(fileResponse);
            }
            return new ResponseEntity<>(fileResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}



