package com.kbc.apartmentsapp.apartment;

import com.kbc.apartmentsapp.owner.OwnerId;
import org.junit.jupiter.api.Test;

class ApartmentServiceTest {
    private final ApartmentService service = null;

    @Test
    void shouldFoundExistingApartment() {
        OwnerId ownerId = givenExistingOwner();
        ApartmentDto apartmentDto = givenValidExistingApartmentDto();

        ApartmentId id = service.add(ownerId, apartmentDto);

        thenReturnedIdOfExistingApartment(id, ownerId, apartmentDto);
        thenRecognizedApartmentAlreadyExists(ownerId, apartmentDto);
        thenApartmentWasNotCreated(apartmentDto);
    }

    private ApartmentDto givenValidExistingApartmentDto() {
        return null;
    }

    private void thenReturnedIdOfExistingApartment(ApartmentId id, OwnerId ownerId, ApartmentDto apartmentDto) {

    }

    private void thenRecognizedApartmentAlreadyExists(OwnerId ownerId, ApartmentDto apartmentDto) {

    }

    @Test
    void shouldCreateAnApartment() {
        OwnerId ownerId = givenExistingOwner();
        ApartmentDto apartmentDto = givenValidNotExistingApartmentDto();

        ApartmentId id = service.add(ownerId, apartmentDto);

        thenApartmentWasCreated(id, ownerId, apartmentDto);
    }

    private void thenApartmentWasCreated(ApartmentId id, OwnerId ownerId, ApartmentDto apartmentDto) {

    }

    @Test
    void shouldRecognizeInvalidAddress() {
        OwnerId ownerId = givenExistingOwner();
        ApartmentDto apartmentDto = givenApartmentDtoWithInvalidAddress();

        ApartmentId id = service.add(ownerId, apartmentDto);

        thenInvalidApartmentIdReturned(id);
        thenRecognizedInvalidAddress(apartmentDto);
        thenApartmentWasNotCreated(apartmentDto);
    }

    private OwnerId givenExistingOwner() {
        return null;
    }

    private ApartmentDto givenApartmentDtoWithInvalidAddress() {
        return null;
    }

    private void thenRecognizedInvalidAddress(ApartmentDto apartmentDto) {

    }

    @Test
    void shouldRecognizeOwnerWasNotFound() {
        OwnerId ownerId = givenNotExistingOwner();
        ApartmentDto apartmentDto = givenValidNotExistingApartmentDto();

        ApartmentId id = service.add(ownerId, apartmentDto);

        thenInvalidApartmentIdReturned(id);
        thenRecognizedOwnerWasNotFound(ownerId);
        thenApartmentWasNotCreated(apartmentDto);
    }

    private OwnerId givenNotExistingOwner() {
        return null;
    }

    private ApartmentDto givenValidNotExistingApartmentDto() {
        return null;
    }

    private void thenInvalidApartmentIdReturned(ApartmentId id) {

    }

    private void thenRecognizedOwnerWasNotFound(OwnerId ownerId) {

    }

    private void thenApartmentWasNotCreated(ApartmentDto apartmentDto) {

    }
}