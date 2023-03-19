package com.SoftUni.DriverServiceProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestPart implements CommandLineRunner {
    private static final String API_URL = "http://localhost:8080/api/driverService";

    private static final Logger LOGGER = LoggerFactory.getLogger(RestPart.API_URL);

    private final RestTemplate restTemplate;

    public RestPart(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
//        ResponseEntity<BookDTO[]> allBooksResponse =
//                restTemplate.getForEntity(API_URL, BookDTO[].class);
//              To change it
//        if (allBooksResponse.hasBody()) {
//            BookDTO[] books  = allBooksResponse.getBody();
//            for (BookDTO aBook : books) {
//                LOGGER.info("Retrieved a book: {}", aBook);
//            }
        }
    }

