package br.com.fiap.wastemanagementsystem.repository;

import br.com.fiap.wastemanagementsystem.model.Resident;
import br.com.fiap.wastemanagementsystem.model.TrashCan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResidentRepository extends JpaRepository <Resident, Long> {

    @Query("SELECT r FROM Resident r WHERE r.district.id = :id")
    List<Resident> findAllByDistrictId(@Param("id") Long id);

    Boolean existsByEmail(String email);

}
