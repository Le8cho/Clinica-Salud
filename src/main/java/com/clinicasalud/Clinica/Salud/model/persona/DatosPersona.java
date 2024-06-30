package com.clinicasalud.Clinica.Salud.model.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosPersona(

        @NotBlank
        String nombres,

        @NotBlank
        String apellidos,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String dni,

        @NotBlank
        @Pattern(regexp="[MF]")
        String sexo,

        @Pattern(regexp = "9\\d{8}")
        String telefono
) {
}
