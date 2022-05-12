package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Contact;
import design.startupInvestment.springboot.security.dto.request.ContactRequest;
import design.startupInvestment.springboot.security.dto.response.ContactResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactResponse convertToContactResponse(Contact contact);

    Contact convertToContact(ContactRequest contactRequest);


}
