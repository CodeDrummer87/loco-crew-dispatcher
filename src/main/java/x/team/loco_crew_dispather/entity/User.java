package x.team.loco_crew_dispather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "personnel_number", unique = true, nullable = false)
    private Integer personnelNumber;

    @Column(name = "date_of_employment", nullable = false)
    private LocalDate dateOfEmployment;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
