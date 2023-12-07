package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.DistanceDurationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
public class DistanceRestController {

    private static final Object API_KEY="";

    @RequestMapping(value = "/api/getDistanceAndDuration",produces = "application/json",
    method = {RequestMethod.GET})
    public DistanceDurationResponse getDistanceAndDuration(@RequestParam String addressFrom,
                                                           @RequestParam String addressTo){
//        UriComponents uri = UriComponentsBuilder.newInstance()
//                .scheme("https")
//                .host("maps.googleapis.com")
//                .path("/maps/api/distancematrix")
//                .queryParam("key", API_KEY)
//                .queryParam("origins", AddressFrom)
//                .queryParam("destinations", AddressTo)
//                .build();
        ResponseEntity<DistanceDurationResponse> response= new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+"Plovdiv"+"&destinations="+"Sofia"+"&mode=car&language=fr-FR&key="+API_KEY, DistanceDurationResponse.class);

        //ResponseEntity<DistanceDurationResponse> response= new RestTemplate().getForEntity(uri.toString(), DistanceDurationResponse.class);
            return  response.getBody();
    }
}
