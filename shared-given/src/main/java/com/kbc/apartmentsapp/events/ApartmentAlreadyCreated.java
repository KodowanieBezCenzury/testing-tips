package com.kbc.apartmentsapp.events;

import com.kbc.apartmentsapp.apartment.ApartmentId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApartmentAlreadyCreated {
    private final ApartmentId apartmentId;
    private final String street;
    private final String houseNumber;
    private final String apartmentNumber;
    private final String city;
    private final String country;
}
