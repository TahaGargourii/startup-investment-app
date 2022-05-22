package design.startupInvestment.springboot.controller;


import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.exceptions.MyFileNotFoundException;
import design.startupInvestment.springboot.model.File;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.repository.FileRepository;
import design.startupInvestment.springboot.repository.StartupperRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.response.FileResponse;
import design.startupInvestment.springboot.security.dto.response.UploadFileResponse;
import design.startupInvestment.springboot.security.mapper.FileMapper;
import design.startupInvestment.springboot.security.service.FileService;
import design.startupInvestment.springboot.security.service.UserService;
import design.startupInvestment.springboot.security.service.impl.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class FileController {


    @Autowired
    FileRepository fileRepository;


    @Autowired
    UserRepository userRepository;


    @Autowired
    StartupperRepository startupperRepository;
    @Autowired
    FileService fileService;




    @GetMapping("/files")
    public ResponseEntity<ApiResponse> getAllFiles() {
        ApiResponse response = fileService.getAllFiles();
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @GetMapping("/files/{id}")
    public ResponseEntity<ApiResponse> getFileById(@PathVariable("id") long id) {
        ApiResponse response = fileService.getFileById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @GetMapping("/files/startupper")
    public ResponseEntity<ApiResponse> findByStartupper() {
        ApiResponse response = fileService.findByStartupper();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    UserService userService;
    @Autowired
    private FileStorageService fileStorageService;
    @PostMapping("/uploadFile")
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        ApiResponse apiResponse = null;
        User user = userService.getConnectedStartupper();
        if (user != null) {
            String fileName = fileStorageService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
            File newFile = new File();
            newFile.setFileName(fileName);
            newFile.setFileDownloadUri(fileDownloadUri);
            newFile.setSize(file.getSize());
            newFile.setFileType(file.getContentType());
            newFile.setStartupper(user.getStartupper());
            fileRepository.save(newFile);
            FileResponse fileResponse = FileMapper.INSTANCE.convertToFileResponse(newFile);

            apiResponse = new ApiResponse(fileResponse, null, HttpStatus.OK, LocalDateTime.now());
            return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
          /*  return new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());*/
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity<ApiResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        ApiResponse apiResponse = null;
        List<FileResponse> fileResponses = new ArrayList<>();
        User user = userService.getConnectedStartupper();
        if (user != null) {
            for (MultipartFile file : files) {
                String fileName = fileStorageService.storeFile(file);

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
                File newFile = new File();
                newFile.setFileName(fileName);
                newFile.setFileDownloadUri(fileDownloadUri);
                newFile.setSize(file.getSize());
                newFile.setFileType(file.getContentType());
                newFile.setStartupper(user.getStartupper());
                FileResponse fileResponse = FileMapper.INSTANCE.convertToFileResponse(newFile);
                fileResponses.add(fileResponse);
                apiResponse = new ApiResponse(fileResponses, null, HttpStatus.OK, LocalDateTime.now());

            }
            return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws MyFileNotFoundException {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

}



