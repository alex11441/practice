package com.conaxgames.practice.kit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum KitMask {

    ENABLED(1, "Enabled", "enabled"),
    RANKED(2, "Ranked", "ranked"),
    ALLOW_BUILDING(4, "Allow Building", "allow_building"),
    EDITABLE(8, "Is Editable", "editable");

    private final int mask;
    private final String name;
    private final String id;

    public static final KitMask[] VALUES = values();

    public static KitMask getById(String id) {
        return Arrays.stream(VALUES).filter(mask -> mask.id.equals(id)).findFirst().orElse(null);
    }

}
