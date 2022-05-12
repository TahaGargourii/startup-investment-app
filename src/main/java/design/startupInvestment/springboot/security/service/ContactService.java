package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.ContactRequest;

public interface ContactService {

    ApiResponse getAllContacts();

    ApiResponse getContactById(long id);

    ApiResponse createContact(ContactRequest contactRequest);

    ApiResponse updateContact(long id, ContactRequest contactRequest);

    ApiResponse deleteContact(long id);

    ApiResponse deleteAllContacts();


}
