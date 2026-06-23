package x.team.loco_crew_dispather.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Page<EmployeeDto>> findAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "fullName") String sortBy) {

        if (page < 0) page = 0;
        if (size <1 || size > 50) size = 8;

        return ResponseEntity.ok(employeeService.getEmployeePage(page, size, sortBy));
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
