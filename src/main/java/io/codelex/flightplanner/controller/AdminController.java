package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.requests.FlightRequest;
import io.codelex.flightplanner.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/admin-api")
public class AdminController {

    private final FlightService service;

    public AdminController(FlightService service) {
        this.service = service;
    }

    @PutMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public Flight add(@RequestBody @Valid FlightRequest flightRequest) {
        return service.add(flightRequest);

    }

    @GetMapping("/flights/{id}")
    public Flight fetchById(@PathVariable("id") int id) {

        return service.fetch(id);
    }

    @DeleteMapping("/flights/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlightById(@PathVariable("id") int id) {
        service.delete(id);
    }


}
