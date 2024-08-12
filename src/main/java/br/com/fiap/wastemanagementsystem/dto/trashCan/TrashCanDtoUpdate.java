package br.com.fiap.wastemanagementsystem.dto.trashCan;

import jakarta.validation.constraints.NotNull;

public record TrashCanDtoUpdate(
        @NotNull(message = "Must have a valid volume to be add!")
        Double addedVolume
) {
}
