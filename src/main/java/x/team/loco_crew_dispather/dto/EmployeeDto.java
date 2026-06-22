package x.team.loco_crew_dispather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import x.team.loco_crew_dispather.entity.Status;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    private String fullName;
    private Integer personnelNumber;
    private String positionTitle;
    private String positionAbbreviate;
    //private Boolean isActive;
    private String statusTitle;

    public EmployeeDto(Long id, String fullName, Integer personnelNumber,
                       String positionTitle, String positionAbbreviate, //Boolean isActive,
                       Status statusTitle) {
        this.id = id;
        this.fullName = fullName;
        this.personnelNumber = personnelNumber;
        this.positionTitle = positionTitle;
        this.positionAbbreviate = positionAbbreviate;
        //this.isActive = isActive;
        this.statusTitle = statusTitle.getTitle();
    }
}
