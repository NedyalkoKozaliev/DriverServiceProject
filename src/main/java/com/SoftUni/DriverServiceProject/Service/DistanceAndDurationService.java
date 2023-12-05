package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.DTO.DistanceDurationResponse;

import java.io.IOException;

public interface DistanceAndDurationService {

    DistanceDurationResponse Route(String AdrressFrom, String AddressTo) throws IOException, InterruptedException;
}
