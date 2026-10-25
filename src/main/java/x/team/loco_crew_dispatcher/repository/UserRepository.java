package x.team.loco_crew_dispatcher.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import x.team.loco_crew_dispatcher.dto.UserDto;
import x.team.loco_crew_dispatcher.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
              SELECT NEW  x.team.loco_crew_dispatcher.dto.UserDto(u.id, u.fullName, 
                          u.personnelNumber, u.dateOfEmployment, p.title)
               FROM User u
               LEFT JOIN u.position p
               WHERE u.isActive = true
            """)
    Page<UserDto> getAllUsers(Pageable pageable);
}