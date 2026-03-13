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

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Employee driver;

    @Column(name = "has_assistant")
    private Boolean hasAssistant;

    @ManyToOne
    @JoinColumn(name = "driver_assistant_id")
    private Employee driverAssistant;

    @Column(name = "has_understudy")
    private Boolean hasUnderstudy;

    @ManyToOne
    @JoinColumn(name = "understudy_id")
    private Employee understudy;

    @ManyToOne
    @JoinColumn (name = "traction_kind_id")
    private TractionKind tractionKind;

    @ManyToOne
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceType;

    private String notes;


}
