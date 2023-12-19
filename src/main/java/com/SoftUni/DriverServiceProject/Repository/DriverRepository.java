package com.SoftUni.DriverServiceProject.Repository;

import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
  Optional<Driver> findDriverByEmail(String email);

   Optional<Driver> findDriverById(Long id);


    Driver findDriverByCurrentTask(Long orderId);

}