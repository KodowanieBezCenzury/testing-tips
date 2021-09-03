package services.apartment;

import com.kbc.apartmentsapp.address.AddressCatalogue;
import com.kbc.apartmentsapp.apartment.*;
import com.kbc.apartmentsapp.events.EventRegistry;
import com.kbc.apartmentsapp.events.InvalidAddressRecognized;
import com.kbc.apartmentsapp.events.OwnerNotFound;
import com.kbc.apartmentsapp.owner.GivenOwner;
import com.kbc.apartmentsapp.owner.OwnerId;
import com.kbc.apartmentsapp.owner.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

class ApartmentServiceTest {
    private final OwnerRepository ownerRepository = mock(OwnerRepository.class);
    private final ApartmentRepository apartmentRepository = mock(ApartmentRepository.class);
    private final AddressCatalogue addressCatalogue = mock(AddressCatalogue.class);
    private final EventRegistry eventRegistry = mock(EventRegistry.class);
    private final ApartmentService service = new ApartmentService(ownerRepository, apartmentRepository, addressCatalogue, eventRegistry);

    private final GivenOwner givenOwner = new GivenOwner(ownerRepository);
    private final GivenApartment givenApartment = new GivenApartment(addressCatalogue, apartmentRepository);

    @Test
    void shouldNotAddApartmentWhenOwnerDoesNotExist() {
        OwnerId ownerId = givenOwner.notExisting();
        ApartmentDto apartmentDto = givenApartment.validApartmentDtoForNotExisting();

        ApartmentId apartmentId = service.add(ownerId, apartmentDto);

        assertThat(apartmentId).isEqualTo(ApartmentId.nullObject());
        then(apartmentRepository).should(never()).save(any());
        ArgumentCaptor<OwnerNotFound> captor = ArgumentCaptor.forClass(OwnerNotFound.class);
        then(eventRegistry).should().publish(captor.capture());
        assertThat(captor.getValue().getOwnerId()).isEqualTo(ownerId);
    }

    @Test
    void shouldRecognizeInvalidAddress() {
        OwnerId ownerId = givenOwner.existing();
        ApartmentDto apartmentDto = givenApartment.apartmentDtoWithInvalidAddress();

        ApartmentId apartmentId = service.add(ownerId, apartmentDto);

        assertThat(apartmentId).isEqualTo(ApartmentId.nullObject());
        then(apartmentRepository).should(never()).save(any());
        ArgumentCaptor<InvalidAddressRecognized> captor = ArgumentCaptor.forClass(InvalidAddressRecognized.class);
        then(eventRegistry).should().publish(captor.capture());
        assertThat(captor.getValue().getStreet()).isEqualTo("Rynek Główny");
        assertThat(captor.getValue().getHouseNumber()).isEqualTo("43");
        assertThat(captor.getValue().getApartmentNumber()).isEqualTo("2");
        assertThat(captor.getValue().getCity()).isEqualTo("Kraków");
        assertThat(captor.getValue().getCountry()).isEqualTo("Polska");
    }

    @Test
    void shouldReturnIdOfExistingApartment() {
        OwnerId ownerId = givenOwner.existing();
        ApartmentDto apartmentDto = givenApartment.apartmentDtoForExisting(ownerId);

        ApartmentId apartmentId = service.add(ownerId, apartmentDto);

        assertThat(apartmentId).isNotEqualTo(ApartmentId.nullObject());
        assertThat(apartmentId).isNotNull();
        then(apartmentRepository).should(never()).save(any());
    }

    @Test
    void shouldCreateNewApartment() {
        OwnerId ownerId = givenOwner.existing();
        ApartmentDto apartmentDto = givenApartment.validApartmentDtoForNotExisting();

        ApartmentId apartmentId = service.add(ownerId, apartmentDto);

        assertThat(apartmentId).isNotEqualTo(ApartmentId.nullObject());
        Apartment actual = thenApartmentWasCreated();
        new ApartmentAssertion(actual)
            .hasIdEqualTo(apartmentId)
            .hasOwnerIdEqualTo(ownerId)
            .hasStreetEqualTo("Rynek Główny")
            .hasHouseNumberEqualTo("43")
            .hasApartmentNumberEqualTo("2")
            .hasCityEqualTo("Kraków")
            .hasCountryEqualTo("Polska");
    }

    private Apartment thenApartmentWasCreated() {
        ArgumentCaptor<Apartment> captor = ArgumentCaptor.forClass(Apartment.class);
        then(apartmentRepository).should().save(captor.capture());
        Apartment actual = captor.getValue();
        return actual;
    }
}