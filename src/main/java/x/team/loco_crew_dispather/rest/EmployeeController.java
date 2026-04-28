package x.team.loco_crew_dispather.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import x.team.loco_crew_dispather.entity.Status;
import x.team.loco_crew_dispather.service.EmployeeService;
import x.team.loco_crew_dispather.dto.EmployeeDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getEmployeeCount() {
        return ResponseEntity.ok(employeeService.getEmployeeCount());
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        return ResponseEntity.ok((employeeService.getEmployeeList()));
    }

    @GetMapping("/count/position/{id}")
    public ResponseEntity<Long> getEmployeeCountByPosition(@PathVariable("id") Long positionId) {
        return ResponseEntity.ok(employeeService.getEmployeesByPosition(positionId));
    }

    @GetMapping("/count/position/{id}/state/{statusId}")
    public ResponseEntity<Long> getEmployeeCountByPositionAndStatus(
            @PathVariable("id") Long positionId,
            @PathVariable("statusId") int statusId) {
        Status status = Status.values()[statusId];
        return ResponseEntity.ok(employeeService.getEmployeesByPositionAndStatus(positionId, status));
    }
}
