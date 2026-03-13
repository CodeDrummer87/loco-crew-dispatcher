package x.team.loco_crew_dispather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import x.team.loco_crew_dispather.dto.LocomotiveCrewDto;
import x.team.loco_crew_dispather.entity.LocomotiveCrew;

import java.util.List;

@Repository
public interface LocomotiveCrewRepository extends JpaRepository<LocomotiveCrew, Long> {

    long count();

    @Query("""
    SELECT NEW x.team.loco_crew_dispather.dto.LocomotiveCrewDto(
        lc.id,
        lc.driver.fullName,
        lc.hasAssistant,
        CASE WHEN lc.hasAssistant = true THEN lc.driverAssistant.fullName ELSE null END,
        lc.hasUnderstudy,
        CASE WHEN lc.hasUnderstudy = true THEN lc.understudy.fullName ELSE null END,
        lc.tractionKind.title,
        lc.serviceType.title,
        lc.notes
    )
    FROM LocomotiveCrew lc
    LEFT JOIN lc.driverAssistant
    LEFT JOIN lc.understudy
""")
    List<LocomotiveCrewDto> getAllLocomotiveCrews();
}