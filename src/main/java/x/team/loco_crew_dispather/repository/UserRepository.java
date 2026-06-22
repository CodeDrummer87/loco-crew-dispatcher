package x.team.loco_crew_dispather.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import x.team.loco_crew_dispather.dto.UserDto;
import x.team.loco_crew_dispather.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("""
              SELECT NEW  x.team.loco_crew_dispather.dto.UserDto(u.id, u.fullName, 
                          u.personnelNumber, u.dateOfEmployment)
               FROM User u
               WHERE u.isActive = true
            """)
    Page<UserDto> getAllUsers(Pageable pageable);
}