package br.com.fiap.wastemanagementsystem.model;

import lombok.Getter;

@Getter
public enum WasteType {

    GREEN("green"),
    RECYCLABLE("recyclable"),
    COMMON("common");

    private final String wasteType;

    WasteType(String wasteType) {
        this.wasteType = wasteType;
    }

}
