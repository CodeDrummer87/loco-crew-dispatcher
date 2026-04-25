package x.team.loco_crew_dispather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import x.team.loco_crew_dispather.dto.EmployeeDto;
import x.team.loco_crew_dispather.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    long count();

    @Query("""
             SELECT NEW  x.team.loco_crew_dispather.dto.EmployeeDto(e.id, e.fullName, e.personnelNumber, p.title, 
                         p.abbreviate, e.isAtWork, s.title)
              FROM Employee e
              JOIN e.position p
              LEFT JOIN e.status s
           """)
    List<EmployeeDto> getAllEmployees();

    long countByPositionId(Long positionId);

    long countByPositionIdAndStatusId(Long positionId, Long statusId);
}