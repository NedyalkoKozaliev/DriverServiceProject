package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.Entity.User;

public interface ClientService {


    User findClientById(Long id);

    User findClientByEmail(String email);
}
