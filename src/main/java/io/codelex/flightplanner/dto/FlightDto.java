package io.codelex.flightplanner.dto;

import io.codelex.flightplanner.domain.Airport;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class FlightDto {
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

    public FlightDto(@NotNull Airport from, @NotNull Airport to, @NotNull String carrier, String departureTime, String arrivalTime) {
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

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightDto flightDto = (FlightDto) o;

        if (!getFrom().equals(flightDto.getFrom())) return false;
        if (!getTo().equals(flightDto.getTo())) return false;
        if (getCarrier() != null ? !getCarrier().equals(flightDto.getCarrier()) : flightDto.getCarrier() != null)
            return false;
        if (getDepartureTime() != null ? !getDepartureTime().equals(flightDto.getDepartureTime()) : flightDto.getDepartureTime() != null)
            return false;
        return getArrivalTime() != null ? getArrivalTime().equals(flightDto.getArrivalTime()) : flightDto.getArrivalTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getFrom().hashCode();
        result = 31 * result + getTo().hashCode();
        result = 31 * result + (getCarrier() != null ? getCarrier().hashCode() : 0);
        result = 31 * result + (getDepartureTime() != null ? getDepartureTime().hashCode() : 0);
        result = 31 * result + (getArrivalTime() != null ? getArrivalTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FlightDto{" +
                "from=" + from +
                ", to=" + to +
                ", carrier='" + carrier + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }
}
