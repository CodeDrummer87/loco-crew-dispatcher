package x.team.loco_crew_dispather.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import x.team.loco_crew_dispather.dto.EmployeeDto;
import x.team.loco_crew_dispather.entity.Employee;
import x.team.loco_crew_dispather.entity.Status;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    long count();

    @Query("""
             SELECT NEW  x.team.loco_crew_dispather.dto.EmployeeDto(
                        e.id,
                        e.fullName,
                        e.personnelNumber,
                        p.title,
                        p.abbreviate,
                        e.status)
              FROM Employee e
              JOIN e.position p
              WHERE e.isActive = true
           """)
    Page<EmployeeDto> getAllEmployees(Pageable pageable);

    long countByPositionId(Long positionId);

    long countByPositionIdAndStatus(Long positionId, Status status);
}