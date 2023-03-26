package com.SoftUni.DriverServiceProject.Service;

import com.SoftUni.DriverServiceProject.Models.DTO.DistanceAndDurationDto;
import com.SoftUni.DriverServiceProject.Models.DTO.DistanceDto;
import com.SoftUni.DriverServiceProject.Models.DTO.DurationDto;

import java.io.IOException;
import java.util.ArrayList;

public interface DistanceAndDurationService {

    DistanceAndDurationDto Route(String AdrressFrom, String AddressTo) throws IOException, InterruptedException;
}
