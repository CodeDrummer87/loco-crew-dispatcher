package x.team.loco_crew_dispatcher.service;

import org.springframework.stereotype.Service;
import x.team.loco_crew_dispatcher.dto.LocomotiveCrewDto;
import x.team.loco_crew_dispatcher.entity.CrewStatus;
import x.team.loco_crew_dispatcher.repository.LocomotiveCrewRepository;

import java.util.List;

@Service
public class LocomotiveCrewService {

    private final LocomotiveCrewRepository locomotiveCrewRepository;

    public LocomotiveCrewService(LocomotiveCrewRepository locomotiveCrewRepository) {
        this.locomotiveCrewRepository = locomotiveCrewRepository;
    }

    public long getLocomotiveCrewCount() {
        return locomotiveCrewRepository.count();
    }

    public List<LocomotiveCrewDto> getLocomotiveCrewList() {
        return locomotiveCrewRepository.getAllLocomotiveCrews();
    }

    public long getSingleDriverCount() {
        return locomotiveCrewRepository.getSinglePersonCrewCount();
    }

    public Long getCrewCountByStatus(CrewStatus status) {
        return locomotiveCrewRepository.countByCrewStatus(status);
    }
}

