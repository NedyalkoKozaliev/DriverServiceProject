package com.SoftUni.DriverServiceProject.Repository;

import com.SoftUni.DriverServiceProject.Models.Entity.Driver;
import com.SoftUni.DriverServiceProject.Models.Entity.DriverRole;
import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRoleRepository extends JpaRepository<DriverRole, Long> {

    Optional<DriverRole> findDriverRoleByRole(UserRoleEnum driver);

}