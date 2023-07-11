package io.codelex.flightplanner.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class Airport {
    @NotBlank
    @NotNull
    private String country;

    @NotBlank
    @NotNull
    private String city;

    @NotBlank
    @NotNull
    private String airport;

    public Airport(@NotNull String country, @NotNull String city, @NotNull String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    public @NotNull String getCountry() {
        return country;
    }

    public void setCountry(@NotNull String country) {
        this.country = country;
    }

    public @NotNull String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    public @NotNull String getAirport() {
        return airport;
    }

    public void setAirport(@NotNull String airport) {
        this.airport = airport;
    }


}
