package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.repository.FileRepository;
import design.startupInvestment.springboot.repository.StartupperRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.FileRequest;
import design.startupInvestment.springboot.security.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


/**
 *
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class FileController {

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
    @Autowired
    FileService fileService;

    /**
     * @param fileName
     * @param field
     * @param dateOfUpload
     * @return
     */
    @GetMapping("/files")
    public ResponseEntity<ApiResponse> getAllFiles(@RequestParam(required = false) String fileName, @RequestParam(required = false) String field, @RequestParam(required = false) Date dateOfUpload) {
        ApiResponse response = fileService.getAllFiles(fileName, field, dateOfUpload);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<ApiResponse> getFileById(@PathVariable("id") long id) {
        ApiResponse response = fileService.getFileById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/files")
    public ResponseEntity<ApiResponse> createFile(@RequestBody FileRequest fileRequest) {
        ApiResponse response = fileService.createFile(fileRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/files/{id}")
    public ResponseEntity<ApiResponse> updateFile(@PathVariable("id") long id, @RequestBody FileRequest fileRequest) {
        ApiResponse response = fileService.updateFile(id, fileRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<ApiResponse> deleteFileByIdAndStartupper(@PathVariable("id") long id) {
        ApiResponse response = fileService.deleteFileByIdAndStartupper(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/files")
    public ResponseEntity<ApiResponse> deleteAllFilesByStartupper() {
        ApiResponse response = fileService.deleteAllFilesByStartupper();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/files/startupper")
    public ResponseEntity<ApiResponse> findByStartupper() {
        ApiResponse response = fileService.findByStartupper();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}



