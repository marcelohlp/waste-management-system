package br.com.fiap.wastemanagementsystem.model;

import br.com.fiap.wastemanagementsystem.dto.resident.ResidentDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.resident.ResidentDtoUpdate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tbl_residents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Resident {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqc_resident_id")
    @SequenceGenerator(name = "sqc_resident_id", sequenceName = "sqc_resident_id", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_district")
    private District district;

    public Resident(ResidentDtoAdd dto, District district) {
        this.firstName = dto.firstName();
        this.lastName = dto.lastName();
        this.email = dto.email();
        this.district = district;
    }

    public void update(ResidentDtoUpdate dto, District district) {
        this.firstName = dto.firstName();
        this.lastName = dto.lastName();
        this.district = district;
    }
}
