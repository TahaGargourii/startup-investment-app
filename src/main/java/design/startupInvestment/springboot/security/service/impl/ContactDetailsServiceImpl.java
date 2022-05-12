package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.Contact;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.repository.ContactRepository;
import design.startupInvestment.springboot.repository.InvestorRepository;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.request.ContactRequest;
import design.startupInvestment.springboot.security.dto.response.ContactResponse;
import design.startupInvestment.springboot.security.mapper.ContactMapper;
import design.startupInvestment.springboot.security.service.ContactService;
import design.startupInvestment.springboot.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class ContactDetailsServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    ContactService contactService;
    @Autowired
    UserService userService;

    @Override
    public ApiResponse getAllContacts() {
        try {
            List<ContactResponse> contactResponses = new ArrayList<>();
/*            User userInvestor = userService.getConnectedInvestor();
            User userStartupper = userService.getConnectedStartupper();
            List<Contact> contacts = new ArrayList<>();
            if (userInvestor != null) {
                contacts = contactRepository.findAllByInvestor(userInvestor.getInvestor());
            } else if (userStartupper != null) {
                contacts = contactRepository.findAllByStartupper(userStartupper.getStartupper());
            } else {
                return new ApiResponse(null, "UNEXISTANT USER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }*/

            List<Contact> contacts = contactRepository.findAll();
            if (!contacts.isEmpty()) {
                for (Contact contact : contacts) {
                    ContactResponse contactResponse = ContactMapper.INSTANCE.convertToContactResponse(contact);
                    contactResponses.add(contactResponse);
                }
            }
            return new ApiResponse(contactResponses, null, HttpStatus.OK, LocalDateTime.now());


        } catch (Exception e) {
            return new ApiResponse(null, null, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse getContactById(long id) {
        try {
           /* User user = userService.getConnectedStartupper();
            if (user != null) {*/
            Optional<Contact> contact = contactRepository.findById(id);
            if (contact.isPresent()) {
                ContactResponse contactResponse = ContactMapper.INSTANCE.convertToContactResponse(contact.get());
                return new ApiResponse(contactResponse, null, HttpStatus.OK, LocalDateTime.now());
            } /*else {
                    return new ApiResponse(null, "CONTACT DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            } else {
                return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());

            }*/
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ApiResponse createContact(ContactRequest contactRequest) {
        try {
         /* User userInvestor = userService.getConnectedInvestor();
            User userStartupper = userService.getConnectedStartupper();
         */
            Contact contact = ContactMapper.INSTANCE.convertToContact(contactRequest);
        /*    if (userInvestor != null) {
                contact.setInvestor(userInvestor.getInvestor());
            } else if (userStartupper != null) {
                contact.setStartupper(userStartupper.getStartupper());
            } else {
                return new ApiResponse(null, "UNEXISTANT USER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }*/
            contactRepository.save(contact);
            return new ApiResponse(null, "CONTACT CREATED", HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }

    @Override
    public ApiResponse updateContact(long id, ContactRequest contactRequest) {
        try {
            /*User user = userService.getConnectedInvestor();
            if (user != null) {*/
            Optional<Contact> contactData = contactRepository.findById(id);
            if (contactData.isPresent()) {
                Contact _contact = contactData.get();
                contactRepository.save(_contact);
                return new ApiResponse(_contact, "CONTACTS IS UPDATED", HttpStatus.OK, LocalDateTime.now());
            } /*else {
                    return new ApiResponse(null, "CONTACT DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                }
            }*/
            return new ApiResponse(null, "USER IS NOT AN INVESTOR", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "NO UPDATE", HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
    }


    @Override
    public ApiResponse deleteContact(long id) {
        try {
            /*User user = userService.getConnectedStartupper();
            if (user != null) {*/
            Optional<Contact> contact = contactRepository.findById(id);
            if (contact.isPresent()) {
                contactRepository.deleteById(id);
                return new ApiResponse(null, "CONTACT IS DELETED", HttpStatus.OK, LocalDateTime.now());
            }
          /*  } else {
                return new ApiResponse(null, "USER IS NOT AN CONTACT", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
*/
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, "USER IS NOT AN CONTACT", HttpStatus.BAD_REQUEST, LocalDateTime.now());

        }

    }

    @Override
    public ApiResponse deleteAllContacts() {
        try {
            User userInvestor = userService.getConnectedInvestor();
            User userStartupper = userService.getConnectedStartupper();
            if (userInvestor != null) {
                contactRepository.deleteAllByInvestor(userInvestor.getInvestor());
            } else if (userStartupper != null) {
                contactRepository.deleteAllByStartupper(userStartupper.getStartupper());
            } else {
                return new ApiResponse(null, "UNEXISTANT USER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
            }
            return new ApiResponse(null, null, HttpStatus.OK, LocalDateTime.now());
        } catch (Exception e) {
            return new ApiResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

        }
    }


}
