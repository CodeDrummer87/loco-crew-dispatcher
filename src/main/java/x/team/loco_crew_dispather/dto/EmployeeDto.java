package x.team.loco_crew_dispather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    private String fullName;
    private Integer personnelNumber;
    private String positionTitle;
    private String positionAbbreviate;
    private Boolean isAtWork;
    private String statusTitle;
}
