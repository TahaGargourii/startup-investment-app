package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.File;
import design.startupInvestment.springboot.model.Startupper;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.security.dto.request.FileRequest;
import design.startupInvestment.springboot.security.dto.response.FileResponse;
import design.startupInvestment.springboot.security.dto.response.StartupperResponse;
import design.startupInvestment.springboot.security.dto.response.UserResponse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-15T22:26:48+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class FileMapperImpl implements FileMapper {

    @Override
    public FileResponse convertToFileResponse(File file) {
        if ( file == null ) {
            return null;
        }

        FileResponse fileResponse = new FileResponse();

        fileResponse.setId( file.getId() );
        fileResponse.setFileName( file.getFileName() );
        fileResponse.setDateOfUpload( file.getDateOfUpload() );
        fileResponse.setField( file.getField() );
        fileResponse.setStartupper( startupperToStartupperResponse( file.getStartupper() ) );

        return fileResponse;
    }

    @Override
    public File convertToFile(FileRequest fileRequest) {
        if ( fileRequest == null ) {
            return null;
        }

        File file = new File();

        file.setFileName( fileRequest.getFileName() );
        file.setDateOfUpload( fileRequest.getDateOfUpload() );
        file.setField( fileRequest.getField() );

        return file;
    }

    protected UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setName( user.getName() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setUserRole( user.getUserRole() );

        return userResponse;
    }

    protected StartupperResponse startupperToStartupperResponse(Startupper startupper) {
        if ( startupper == null ) {
            return null;
        }

        StartupperResponse startupperResponse = new StartupperResponse();

        startupperResponse.setId( startupper.getId() );
        startupperResponse.setUser( userToUserResponse( startupper.getUser() ) );

        return startupperResponse;
    }
}
