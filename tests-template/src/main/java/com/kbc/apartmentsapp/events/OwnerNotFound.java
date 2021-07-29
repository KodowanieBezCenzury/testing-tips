package com.kbc.apartmentsapp.events;

import com.kbc.apartmentsapp.owner.OwnerId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OwnerNotFound {
    private final OwnerId ownerId;
}
