package com.SoftUni.DriverServiceProject.Models.Entity;

import com.SoftUni.DriverServiceProject.Models.Enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "driverRole")
public class DriverRole extends BaseEntity{
    @Column
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public DriverRole setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
