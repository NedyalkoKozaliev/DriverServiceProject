package com.SoftUni.DriverServiceProject.Service.Impl;

import com.SoftUni.DriverServiceProject.Models.DTO.DistanceAndDurationDto;
import com.SoftUni.DriverServiceProject.Service.DistanceAndDurationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class DistanceAndDurationServiceImpl implements DistanceAndDurationService {
    private static final String API_KEY = "google matrix key";
    private Gson gson;


    @Autowired
    public DistanceAndDurationServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public DistanceAndDurationDto Route(String AddressFrom, String AddressTo) throws IOException, InterruptedException {
        var url="https://maps.googleapis.com/maps/api/distancematrix/json?origins="+AddressFrom+"&destinations="+AddressTo+"&mode=car&language=fr-FR&key="+API_KEY;
        var request= HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client= HttpClient.newBuilder().build();
        var response=client.send(request, HttpResponse.BodyHandlers.ofString());



        // parse JSON (response) and take distanse and time
        DistanceAndDurationDto distanceAndDurationDto=gson
                        .fromJson(response.toString(), DistanceAndDurationDto.class);




        return distanceAndDurationDto;


    }




}