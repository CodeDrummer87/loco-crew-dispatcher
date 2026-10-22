package x.team.loco_crew_dispatcher.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import x.team.loco_crew_dispatcher.dto.EmployeeDto;
import x.team.loco_crew_dispatcher.entity.Status;
import x.team.loco_crew_dispatcher.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public long getEmployeeCount() {
        return employeeRepository.count();
    }

    public Page<EmployeeDto> getEmployeePage(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return employeeRepository.getAllEmployees(pageable);
    }

    public long getEmployeesByPosition(Long positionId) {
        return employeeRepository.countByPositionId(positionId);
    }

    public long getEmployeesByPositionAndStatus(Long positionId, Status status) {
        return employeeRepository.countByPositionIdAndStatus(positionId, status);
    }
}
