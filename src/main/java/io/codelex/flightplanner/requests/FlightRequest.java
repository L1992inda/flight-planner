package io.codelex.flightplanner.requests;

import io.codelex.flightplanner.domain.Airport;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class FlightRequest {
    @Valid
    @NotNull
    Airport from;
    @Valid
    @NotNull
    Airport to;
    @NotBlank
    @NotNull
    String carrier;
    @NotBlank
    String departureTime;
    @NotBlank
    String arrivalTime;

    public FlightRequest(@NotNull Airport from, @NotNull Airport to, @NotNull String carrier, String departureTime, String arrivalTime) {
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public @NotNull Airport getFrom() {
        return from;
    }

    public void setFrom(@NotNull Airport from) {
        this.from = from;
    }

    public @NotNull Airport getTo() {
        return to;
    }

    public void setTo(@NotNull Airport to) {
        this.to = to;
    }

    public @NotNull String getCarrier() {
        return carrier;
    }

    public void setCarrier(@NotNull String carrier) {
        this.carrier = carrier;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}

