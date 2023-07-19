package io.codelex.flightplanner.flightplanerTests;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.requests.SearchFlightRequest;
import io.codelex.flightplanner.service.FlightService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    FlightRepository repository;
    @InjectMocks
    FlightService service;

    @Test
    public void searchFlight() {
        Flight flight = new Flight(1, new Airport("Latvia", "Riga", "RIX"), new Airport("England", "London", "LHR"),
                "airBaltic",LocalDateTime.now(), LocalDateTime.now());

        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight);

        SearchFlightRequest searchFlightRequest = new SearchFlightRequest("RIX", "LHR", LocalDate.now());

        List<Flight> foundedFlight = flightList.stream().filter(c -> c.getFrom().getAirport().equalsIgnoreCase(searchFlightRequest.getFrom()) &
                c.getTo().getAirport().equalsIgnoreCase(searchFlightRequest.getTo()) &&
                c.getDepartureTime().toString().contains((searchFlightRequest.getDepartureDate()))).toList();

        PageResult<Flight> expectedPageResult = new PageResult<>(0, foundedFlight.size(), foundedFlight);

        Mockito.when(repository.searchFlights(searchFlightRequest)).thenReturn(expectedPageResult);

        PageResult<Flight> actualPageResul = service.search(searchFlightRequest);
        Mockito.verify(repository).searchFlights(searchFlightRequest);
        Assertions.assertEquals(expectedPageResult, actualPageResul);

    }
}
