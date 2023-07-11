package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.FlightDto;
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
        public Flight add(@RequestBody @Valid FlightDto dto){
            return service.add(dto);

        }

}
