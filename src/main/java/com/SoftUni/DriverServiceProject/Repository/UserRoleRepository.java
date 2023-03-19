package com.SoftUni.DriverServiceProject.Repository;

import com.SoftUni.DriverServiceProject.Models.Entity.UserRole;
import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findUserRoleByRole(UserRoleEnum user);

}