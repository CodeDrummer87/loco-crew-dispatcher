package x.team.loco_crew_dispather.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import x.team.loco_crew_dispather.security.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
    boolean existsByUsername(String username);
}
