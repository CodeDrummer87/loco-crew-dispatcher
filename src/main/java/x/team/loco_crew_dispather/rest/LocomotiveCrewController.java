package x.team.loco_crew_dispather.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import x.team.loco_crew_dispather.dto.LocomotiveCrewDto;
import x.team.loco_crew_dispather.service.LocomotiveCrewService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locomotive-crews")
public class LocomotiveCrewController {

    private final LocomotiveCrewService locomotiveCrewService;

    public LocomotiveCrewController(LocomotiveCrewService locomotiveCrewService) {
        this.locomotiveCrewService = locomotiveCrewService;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getLocomotiveCrewCount() {
        return ResponseEntity.ok(locomotiveCrewService.getLocomotiveCrewCount());
    }

    @GetMapping
    public ResponseEntity<List<LocomotiveCrewDto>> findAllLocomotiveCrews(){
        return ResponseEntity.ok(locomotiveCrewService.getLocomotiveCrewList());
    }

    @GetMapping("/count/single-driver")
    public ResponseEntity<Long> getSingleDriverCount() {
        return ResponseEntity.ok(locomotiveCrewService.getSingleDriverCount());
    }
}