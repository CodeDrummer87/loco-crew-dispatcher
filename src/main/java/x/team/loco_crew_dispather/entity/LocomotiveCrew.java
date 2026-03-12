package x.team.loco_crew_dispather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locomotive_crews")
public class LocomotiveCrew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long driver;
    @Column(name = "has_assistant")
    private Boolean hasAssistant;
    @Column(name = "driver_assistant")
    private Long driverAssistant;
    @Column(name = "has_understudy")
    private Boolean hasUnderstudy;
    private Long understudy;
    @Column(name = "traction_kind")
    private Long tractionKind;
    @Column(name = "service_type")
    private Long serviceType;
    private String notes;
}
