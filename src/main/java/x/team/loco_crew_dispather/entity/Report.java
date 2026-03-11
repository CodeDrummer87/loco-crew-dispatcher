package x.team.loco_crew_dispather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "report_to")
    private Timestamp reportTo;

    @Column(name = "reporting_point")
    private long reportingPoint;

    @Column(name = "district")
    private long district;

    @Column(name = "service_type")
    private long serviceType;

    @Column(name = "is_heavy_train")
    private boolean isHeavyTrain;

    @Column(name = "is_coupled_train")
    private boolean isCoupledTrain;

    @Column(name = "notes")
    private String notes;
}

