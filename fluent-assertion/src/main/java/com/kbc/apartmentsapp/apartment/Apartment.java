package com.kbc.apartmentsapp.apartment;

import com.kbc.apartmentsapp.address.Address;
import com.kbc.apartmentsapp.owner.OwnerId;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
public class Apartment {
    private final ApartmentId id = ApartmentId.create();
    private final OwnerId ownerId;
    private final Address address;

    Apartment(OwnerId ownerId, Address address) {
        this.ownerId = ownerId;
        this.address = address;
    }
}
