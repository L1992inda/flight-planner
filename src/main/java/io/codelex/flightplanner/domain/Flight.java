package io.codelex.flightplanner.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Validated
public class Flight {
    @NotBlank
    private int id;
    @Valid
    @NotNull
    private Airport fromAirport;
    @Valid
    @NotNull
    private Airport toAirport;
    @NotBlank
    private String carrier;

    @NotBlank
    private LocalDateTime departureTime;

    @NotBlank
    private LocalDateTime arrivalTime;

    public Flight(int id, @NotNull Airport fromAirport, @NotNull Airport toAirport, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull Airport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(@NotNull Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public @NotNull Airport getToAirport() {
        return toAirport;
    }

    public void setToAirport(@NotNull Airport toAirport) {
        this.toAirport = toAirport;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (!getFromAirport().equals(flight.getFromAirport())) return false;
        return getToAirport().equals(flight.getToAirport());
    }

    @Override
    public int hashCode() {
        int result = getFromAirport().hashCode();
        result = 31 * result + getToAirport().hashCode();
        return result;
    }
}
