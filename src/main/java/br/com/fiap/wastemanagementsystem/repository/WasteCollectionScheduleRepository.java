package br.com.fiap.wastemanagementsystem.repository;

import br.com.fiap.wastemanagementsystem.model.District;
import br.com.fiap.wastemanagementsystem.model.WasteCollectionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WasteCollectionScheduleRepository extends JpaRepository<WasteCollectionSchedule, Long> {


    @Query(
            "SELECT wcs " +
                    "FROM WasteCollectionSchedule wcs " +
                    "WHERE wcs.trashCan.district.id = :districtId " +
                    "AND wcs.scheduledCollectionDate " +
                    "BETWEEN :initialDate AND :finalDate"
    )
    List<WasteCollectionSchedule> findAllByDistrictAndByPeriod(
            @Param("districtId") Long districtId,
            @Param("initialDate") LocalDate initialDate,
            @Param("finalDate") LocalDate finalDate
    );

    @Query(
            "SELECT wcs " +
                    "FROM WasteCollectionSchedule wcs " +
                    "WHERE wcs.trashCan.district.id = :districtId " +
                    "AND wcs.finished = false " +
                    "AND wcs.scheduledCollectionDate " +
                    "BETWEEN :initialDate AND :finalDate"
    )
    List<WasteCollectionSchedule> findAllUnfinishedWasteCollectionByDistrictAndByPeriod(
            @Param("districtId") Long districtId,
            @Param("initialDate") LocalDate initialDate,
            @Param("finalDate") LocalDate finalDate
    );

    @Query(
            "SELECT wcs " +
                    "FROM WasteCollectionSchedule wcs " +
                    "WHERE wcs.trashCan.district.id = :districtId " +
                    "AND wcs.finished = false " +
                    "AND wcs.scheduledCollectionDate < :currentDate " +
                    "AND wcs.scheduledCollectionDate " +
                    "BETWEEN :initialDate AND :finalDate"
    )
    List<WasteCollectionSchedule> findAllDelayedWasteCollectionByDistrictIdAndByPeriod(
            @Param("districtId") Long districtId,
            @Param("currentDate") LocalDate currentDate,
            @Param("initialDate") LocalDate initialDate,
            @Param("finalDate") LocalDate finalDate
    );

    @Query(
            "SELECT COUNT(wcs) " +
                    "FROM WasteCollectionSchedule wcs " +
                    "WHERE wcs.trashCan.district.id = :districtId " +
                    "AND wcs.scheduledCollectionDate " +
                    "BETWEEN :initialDate AND :finalDate"
    )
    Integer getTotalScheduledWasteCollectionByDistrictAndByPeriod(
            @Param("districtId") Long districtId,
            @Param("initialDate") LocalDate initialDate,
            @Param("finalDate") LocalDate finalDate
    );

    @Query(
            "SELECT COUNT(wcs) " +
                    "FROM WasteCollectionSchedule wcs " +
                    "WHERE wcs.trashCan.district.id = :districtId " +
                    "AND wcs.finished = true " +
                    "AND wcs.scheduledCollectionDate " +
                    "BETWEEN :initialDate AND :finalDate"
    )
    Integer getTotalFinishedWasteCollectionByDistrictAndByPeriod(
            @Param("districtId") Long districtId,
            @Param("initialDate") LocalDate initialDate,
            @Param("finalDate") LocalDate finalDate
    );

    @Query(
            "SELECT COUNT(wcs) " +
                    "FROM WasteCollectionSchedule wcs " +
                    "WHERE wcs.trashCan.district.id = :districtId " +
                    "AND wcs.finished = true " +
                    "AND wcs.collectionDate > wcs.scheduledCollectionDate " +
                    "AND wcs.scheduledCollectionDate " +
                    "BETWEEN :initialDate AND :finalDate"
    )
    Integer getTotalDelayedWasteCollectionByDistrictAndByPeriod(
            @Param("districtId") Long districtId,
            @Param("initialDate") LocalDate initialDate,
            @Param("finalDate") LocalDate finalDate
    );

    @Query(
            "SELECT wcs " +
                    "FROM WasteCollectionSchedule wcs " +
                    "WHERE wcs.trashCan.district.id = :districtId " +
                    "AND wcs.finished = false"
    )
    List<WasteCollectionSchedule> findAllWasteCollectionScheduleByDistrictAndNotFinished(
            @Param("districtId") Long districtId
    );

}
