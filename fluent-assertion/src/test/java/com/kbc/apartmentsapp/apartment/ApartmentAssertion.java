package com.kbc.apartmentsapp.apartment;

import com.kbc.apartmentsapp.owner.OwnerId;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class ApartmentAssertion {
    private final Apartment actual;

    public ApartmentAssertion(Apartment actual) {
        this.actual = actual;
    }

    public ApartmentAssertion hasIdEqualTo(ApartmentId expected) {
        assertThat(actual.getId()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasOwnerIdEqualTo(OwnerId expected) {
        assertThat(actual.getOwnerId()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasStreetEqualTo(String expected) {
        assertThat(actual.getAddress().getStreet()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasHouseNumberEqualTo(String expected) {
        assertThat(actual.getAddress().getHouseNumber()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasApartmentNumberEqualTo(String expected) {
        assertThat(actual.getAddress().getApartmentNumber()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasCityEqualTo(String expected) {
        assertThat(actual.getAddress().getCity()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasCountryEqualTo(String expected) {
        assertThat(actual.getAddress().getCountry()).isEqualTo(expected);
        return this;
    }
}
