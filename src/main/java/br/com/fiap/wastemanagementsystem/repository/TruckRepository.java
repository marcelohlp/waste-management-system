package br.com.fiap.wastemanagementsystem.repository;

import br.com.fiap.wastemanagementsystem.model.District;
import br.com.fiap.wastemanagementsystem.model.Truck;
import br.com.fiap.wastemanagementsystem.model.WasteType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TruckRepository extends JpaRepository <Truck, Long> {

    List<Truck> findAllByDistrict(District district);

    boolean existsByLicensePlate(String licensePlate);

}
