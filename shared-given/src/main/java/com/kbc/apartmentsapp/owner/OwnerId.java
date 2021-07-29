package com.kbc.apartmentsapp.owner;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class OwnerId {
    private final UUID id;
}
