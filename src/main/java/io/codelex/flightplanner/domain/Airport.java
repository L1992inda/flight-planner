package io.codelex.flightplanner.domain;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport1 = (Airport) o;

        if (!getCountry().equals(airport1.getCountry())) return false;
        if (!getCity().equals(airport1.getCity())) return false;
        return getAirport().equals(airport1.getAirport());
    }

    @Override
    public int hashCode() {
        int result = getCountry().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getAirport().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", airport='" + airport + '\'' +
                '}';
    }
}
