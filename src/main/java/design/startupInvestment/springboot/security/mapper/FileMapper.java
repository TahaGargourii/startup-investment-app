package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.File;
import design.startupInvestment.springboot.security.dto.request.FileRequest;
import design.startupInvestment.springboot.security.dto.response.FileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileMapper {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    FileResponse convertToFileResponse(File file);

    File convertToFile(FileRequest fileRequest);

}
