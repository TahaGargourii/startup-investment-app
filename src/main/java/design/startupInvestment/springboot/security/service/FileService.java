package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.File;
import design.startupInvestment.springboot.security.dto.request.FileRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public interface FileService {

    ApiResponse getAllFiles();

    ApiResponse getFileById(long id);



    ApiResponse findByStartupper();


}
