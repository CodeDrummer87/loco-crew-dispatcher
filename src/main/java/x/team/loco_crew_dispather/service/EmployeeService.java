package x.team.loco_crew_dispather.service;

import  org.springframework.stereotype.Service;
import x.team.loco_crew_dispather.dto.EmployeeDto;
import x.team.loco_crew_dispather.repository.EmployeeRepository;

import java.util.List;

@Service

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public long getEmployeeCount() {
        return employeeRepository.count();
    }

    public List<EmployeeDto> getEmployeeList() {
        return employeeRepository.getAllEmployees();
    }
}
