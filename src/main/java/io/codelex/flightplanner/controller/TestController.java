package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/testing-api")
public class TestController {
    FlightService service;

    public TestController(FlightService service) {
        this.service = service;
    }

    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearAll() {
        service.clear();
    }
}

