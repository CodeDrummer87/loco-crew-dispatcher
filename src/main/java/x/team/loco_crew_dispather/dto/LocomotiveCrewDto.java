package x.team.loco_crew_dispather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocomotiveCrewDto {

    private Long id;
    private String driverFullName;
    private Boolean hasAssistant;
    private String driverAssistantFullName;
    private Boolean hasUnderstudy;
    private String understudyFullName;
    private String tractionKindTitle;
    private String serviceTypeTitle;
    private String notes;
}

