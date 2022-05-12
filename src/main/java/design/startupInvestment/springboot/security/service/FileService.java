package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.FileRequest;

import java.sql.Date;

public interface FileService {

    ApiResponse getAllFiles(String fileName, String field, Date dateOfUpload);

    ApiResponse getFileById(long id);

    ApiResponse createFile(FileRequest fileRequest);

    ApiResponse updateFile(long id, FileRequest fileRequest);

    ApiResponse findByStartupper();

    ApiResponse deleteFileByIdAndStartupper(Long startupperId);

    ApiResponse deleteAllFilesByStartupper();
}
