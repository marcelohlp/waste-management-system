package br.com.fiap.wastemanagementsystem.model;

import br.com.fiap.wastemanagementsystem.dto.trashCan.TrashCanDtoAdd;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_trash_cans")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TrashCan {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqc_trash_can_id")
    @SequenceGenerator(name = "sqc_trash_can_id", sequenceName = "sqc_trash_can_id", allocationSize = 1)
    private Long id;

    @Column(name = "maximum_capacity")
    private Double maximumCapacity;

    @Column(name = "used_capacity")
    private Double usedCapacity = 0.0;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WasteType wasteType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_district")
    private District district;

    @OneToMany(mappedBy = "trashCan", cascade = CascadeType.ALL)
    private List<WasteCollectionSchedule> wasteCollectionSchedules;

    public TrashCan(TrashCanDtoAdd dto, District district) {
        this.maximumCapacity = dto.maximumCapacity();
        this.wasteType = dto.wasteType();
        this.district = district;
    }

    public void addVolumeToUsedCapacity(Double addedVolume) {
        this.usedCapacity += addedVolume;
    }

}
