package design.startupInvestment.springboot.repository;

import design.startupInvestment.springboot.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
