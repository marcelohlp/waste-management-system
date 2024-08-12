package br.com.fiap.wastemanagementsystem.repository;

import br.com.fiap.wastemanagementsystem.model.TrashCan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrashCanRepository extends JpaRepository <TrashCan, Long> {

    @Query("SELECT t FROM TrashCan t WHERE t.district.id = :id")
    List<TrashCan> findAllByDistrictId(@Param("id") Long id);

}
