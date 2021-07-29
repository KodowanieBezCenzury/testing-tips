package com.kbc.apartmentsapp.apartment;

import com.kbc.apartmentsapp.address.Address;

import java.util.Optional;

public interface ApartmentRepository {
    Optional<Apartment> findBy(Address address);

    ApartmentId save(Apartment apartment);
}
