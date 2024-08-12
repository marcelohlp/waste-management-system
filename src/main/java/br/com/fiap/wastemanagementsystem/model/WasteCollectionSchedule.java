package br.com.fiap.wastemanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_waste_collection_schedules")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WasteCollectionSchedule {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqc_scheduled_id")
    @SequenceGenerator(name = "sqc_scheduled_id", sequenceName = "sqc_scheduled_id", allocationSize = 1)
    private Long id;

    @Column(name = "scheduled_collection_date")
    private LocalDate scheduledCollectionDate;

    @Column(name = "collection_date")
    private LocalDate collectionDate;

    @Column(name = "finished")
    private Boolean finished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trash_can")
    private TrashCan trashCan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_truck")
    private Truck truck;

    public void finish() {
        this.finished = true;
        this.collectionDate = LocalDate.now();
    }

}
