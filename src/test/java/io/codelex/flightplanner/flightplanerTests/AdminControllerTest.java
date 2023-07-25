package io.codelex.flightplanner.flightplanerTests;

import io.codelex.flightplanner.controller.AdminController;
import io.codelex.flightplanner.controller.TestController;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.requests.FlightRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class AdminControllerTest {
    @Autowired
    FlightRepository repository;
    @Autowired
    AdminController adminController;
    @Autowired
    TestController testController;


    @Test
    public void addFlightTest() {

        FlightRequest request = new FlightRequest(new Airport("Latvia", "Riga", "RIX"), new Airport("England", "London", "LHR"),
                "airBaltic", "2023-03-07 10:00", "2023-03-07 10:40");


        adminController.add(request);
        Assertions.assertEquals(1, repository.getFlights().get(0).getId());
        Assertions.assertEquals(request.getFrom(), repository.getFlights().get(0).getFrom());
        Assertions.assertEquals(request.getTo(), repository.getFlights().get(0).getTo());
        Assertions.assertEquals(request.getCarrier(), repository.getFlights().get(0).getCarrier());
        Assertions.assertEquals(LocalDateTime.parse(request.getDepartureTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), repository.getFlights().get(0).getDepartureTime());
        Assertions.assertEquals(LocalDateTime.parse(request.getArrivalTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), repository.getFlights().get(0).getArrivalTime());

        Assertions.assertFalse(repository.getFlights().isEmpty());


    }

    @Test
    public void deleteFlights() {
        testController.clearAll();
        Assertions.assertTrue(repository.getFlights().isEmpty());
    }
}
