package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.File;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.repository.FileRepository;
import design.startupInvestment.springboot.repository.StartupperRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.FileRequest;
import design.startupInvestment.springboot.security.dto.response.FileResponse;
import design.startupInvestment.springboot.security.mapper.FileMapper;
import design.startupInvestment.springboot.security.service.FileService;
import design.startupInvestment.springboot.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
public class FileDetailsServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StartupperRepository startupperRepository;

    @Autowired
    UserService userService;

    public ApiResponse getAllFiles(String fileName, String field, Date dateOfUpload) {
        try {
            List<FileResponse> fileResponses = new ArrayList<>();
            User user = userService.getConnectedStartupper();
            if (user != null) {
                List<File> files = fileRepository.findAll();
                if (!files.isEmpty()) {
                    for (File file : files) {
                        FileResponse fileResponse = FileMapper.INSTANCE.convertToFileResponse(file);
                        fileResponses.add(fileResponse);
                    }
                    return new ApiResponse(fileResponses, null, HttpStatus.OK, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
        return null;
    }

    public ApiResponse getFileById(long id) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<File> file = fileRepository.findById(id);
                if (file.isPresent()) {
                    FileResponse fileResponse = FileMapper.INSTANCE.convertToFileResponse(file.get());
                    return new ApiResponse(fileResponse, null, HttpStatus.OK, LocalDateTime.now());
                } else {
                    return new ApiResponse(null, "FILE DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse createFile(FileRequest fileRequest) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                File file = FileMapper.INSTANCE.convertToFile(fileRequest);
                file.setStartupper(user.getStartupper());
                fileRepository.save(file);
                return new ApiResponse(null, "FILE CREATED", HttpStatus.OK, LocalDateTime.now());
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse updateFile(long id, FileRequest fileRequest) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                Optional<File> fileData = fileRepository.findById(id);
                if (fileData.isPresent()) {
                    File file = fileData.get();
                    file.setFileName(fileRequest.getFileName());
                    file.setField(fileRequest.getField());
                    file.setDateOfUpload(fileRequest.getDateOfUpload());
                    fileRepository.save(file);
                    return new ApiResponse(file, "FILES IS UPDATED", HttpStatus.OK, LocalDateTime.now());
                } else {
                    return new ApiResponse(null, "FILE DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }


    public ApiResponse deleteAllFilesByStartupper() {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                fileRepository.deleteAllFilesByStartupper(user.getStartupper());
                return new ApiResponse(null, "FILE IS DELETED", HttpStatus.OK, LocalDateTime.now());

            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse deleteFileByIdAndStartupper(Long id) {
        try {
            User user = userService.getConnectedStartupper();
            if (user != null) {
                fileRepository.deleteFileByIdAndStartupper(id, user.getStartupper());
                return new ApiResponse(null, "ALL FILES ARE DELETED", HttpStatus.OK, LocalDateTime.now());

            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
            }

        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    public ApiResponse findByStartupper() {
        try {

            List<FileResponse> fileResponses = new ArrayList<>();
            User user = userService.getConnectedStartupper();
            if (user != null) {
                List<File> files = fileRepository.findByStartupper(user.getStartupper());
                if (!files.isEmpty()) {
                    for (File file : files) {
                        FileResponse fileResponse = FileMapper.INSTANCE.convertToFileResponse(file);
                        fileResponses.add(fileResponse);
                    }
                    return new ApiResponse(fileResponses, null, HttpStatus.OK, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
            return new ApiResponse(null, "NO CREATION", HttpStatus.NO_CONTENT, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }


}
