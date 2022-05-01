package design.boilerplate.springboot.security.mapper;

import design.boilerplate.springboot.model.File;
import design.boilerplate.springboot.security.dto.request.FileRequest;
import design.boilerplate.springboot.security.dto.response.FileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileMapper {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    FileResponse convertToFileResponse(File file);

    File convertToFile(FileRequest fileRequest);

}
