package br.com.fiap.wastemanagementsystem.model;

import br.com.fiap.wastemanagementsystem.dto.district.DistrictDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.district.DistrictDtoUpdate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_districts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class District {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqc_district_id")
    @SequenceGenerator(name = "sqc_district_id", sequenceName = "sqc_district_id", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Resident> residents;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Truck> trucks;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<TrashCan> trashCans;

    public District(DistrictDtoAdd dto) {
        this.name = dto.name();
    }

    public void update(DistrictDtoUpdate dto) {
        this.name = dto.name();
    }

}
