package com.kbc.apartmentsapp.apartment;

import com.kbc.apartmentsapp.address.Address;
import com.kbc.apartmentsapp.address.AddressCatalogue;
import com.kbc.apartmentsapp.owner.OwnerId;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class GivenApartment {
    private final AddressCatalogue addressCatalogue;
    private final ApartmentRepository apartmentRepository;

    public GivenApartment(AddressCatalogue addressCatalogue, ApartmentRepository apartmentRepository) {
        this.addressCatalogue = addressCatalogue;
        this.apartmentRepository = apartmentRepository;
    }

    public ApartmentDto validApartmentDtoForNotExisting() {
        ApartmentDto apartmentDto = new ApartmentDto("Rynek Główny", "43", "2", "Kraków", "Polska");
        Address address = new Address("Rynek Główny", "43", "2", "Kraków", "Polska");
        given(addressCatalogue.check("Rynek Główny", "43", "2", "Kraków", "Polska")).willReturn(Optional.of(address));
        given(apartmentRepository.findBy(address)).willReturn(Optional.empty());
        given(apartmentRepository.save(any())).will(invocation -> {
            return ((Apartment) invocation.getArgument(0)).getId();
        });
        return apartmentDto;
    }

    public ApartmentDto apartmentDtoForExisting(OwnerId ownerId) {
        ApartmentDto apartmentDto = new ApartmentDto("Rynek Główny", "43", "2", "Kraków", "Polska");
        Address address = new Address("Rynek Główny", "43", "2", "Kraków", "Polska");
        given(addressCatalogue.check("Rynek Główny", "43", "2", "Kraków", "Polska")).willReturn(Optional.of(address));
        Apartment apartment = new Apartment(ownerId, address);
        given(apartmentRepository.findBy(address)).willReturn(Optional.of(apartment));
        return apartmentDto;
    }

    public ApartmentDto apartmentDtoWithInvalidAddress() {
        ApartmentDto apartmentDto = new ApartmentDto("Rynek Główny", "43", "2", "Kraków", "Polska");
        given(addressCatalogue.check("Rynek Główny", "43", "2", "Kraków", "Polska")).willReturn(Optional.empty());
        return apartmentDto;
    }
}
