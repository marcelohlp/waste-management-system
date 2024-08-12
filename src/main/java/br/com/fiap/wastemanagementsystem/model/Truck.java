package br.com.fiap.wastemanagementsystem.model;

import br.com.fiap.wastemanagementsystem.dto.truck.TruckDtoAdd;
import br.com.fiap.wastemanagementsystem.dto.truck.TruckDtoUpdate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_trucks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Truck {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqc_truck_id")
    @SequenceGenerator(name = "sqc_truck_id", sequenceName = "sqc_truck_id", allocationSize = 1)
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WasteType wasteType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_district")
    private District district;

    @OneToMany(mappedBy = "truck", cascade = CascadeType.ALL)
    private List<WasteCollectionSchedule> wasteCollectionSchedules;

    public Truck(TruckDtoAdd dto, District district) {
        this.licensePlate = dto.licensePlate();
        this.wasteType = dto.wasteType();
        this.district = district;
    }

    public void update(TruckDtoUpdate dto, District district) {
        this.wasteType = dto.wasteType();
        this.district = district;
    }

}
