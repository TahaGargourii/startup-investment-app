package design.startupInvestment.springboot.repository;

import design.startupInvestment.springboot.model.Contact;
import design.startupInvestment.springboot.model.Investor;
import design.startupInvestment.springboot.model.Startupper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByInvestor(Investor investor);

    List<Contact> findAllByStartupper(Startupper startupper);

    void deleteAllByStartupper(Startupper startupper);

    void deleteAllByInvestor(Investor investor);
}
