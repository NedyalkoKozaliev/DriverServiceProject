package com.SoftUni.DriverServiceProject.Web;

import com.SoftUni.DriverServiceProject.Models.DTO.DistanceDurationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
public class DistanceRestController {

    private static final Object API_KEY="";
//produces = "application/json",
    @RequestMapping(value = "/api/getDistanceAndDuration",
    method = {RequestMethod.GET})
    public ResponseEntity<DistanceDurationResponse> getDistanceAndDuration(@RequestParam String addressFrom,
                                                           @RequestParam String addressTo){


        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("maps.googleapis.com")
                .path("/maps/api/distancematrix")
                .queryParam("origins", addressFrom)
                .queryParam("destinations", addressTo)
                .queryParam("key", API_KEY)
                .build();
     //   ResponseEntity<DistanceDurationResponse> response= new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+"Plovdiv"+"&destinations="+"Sofia"+"&mode=car&language=fr-FR&key="+API_KEY, DistanceDurationResponse.class);

        return new RestTemplate().getForEntity(uri.toString(), DistanceDurationResponse.class);
    }
}
// return new ResponseEntity<String>("{\"test\": \"Hello with ResponseEntity\"}", httpHeaders, HttpStatus.OK);
//        }