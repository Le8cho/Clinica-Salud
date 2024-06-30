package com.clinicasalud.Clinica.Salud.model.paciente;

import com.clinicasalud.Clinica.Salud.model.persona.DatosPersona;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public record DatosCrearPaciente(

        @Valid
        DatosPersona datosPersona,

        @NotBlank
        String direccion,

        @NotNull
        @Past
        LocalDate fechaNacimiento

) {
    public DatosCrearPaciente(
            String nombresPac,
            String apellidosPac,
            String dniPac,
            String sexoPac,
            String tlfPac,
            String direccion,
            LocalDate fechaNacPac
    ) {
        this(
                new DatosPersona(nombresPac,apellidosPac,dniPac,sexoPac, tlfPac),
                direccion,
                fechaNacPac
                );
    }
}
