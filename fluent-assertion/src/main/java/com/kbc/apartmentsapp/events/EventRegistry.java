package com.kbc.apartmentsapp.events;

public interface EventRegistry {
    void publish(ApartmentAlreadyCreated apartmentAlreadyCreated);

    void publish(InvalidAddressRecognized invalidAddressRecognized);

    void publish(OwnerNotFound ownerNotFound);
}
