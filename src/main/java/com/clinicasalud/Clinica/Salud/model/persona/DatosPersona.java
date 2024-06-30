package com.clinicasalud.Clinica.Salud.model.persona;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class DatosPersona {

    @Column(name = "Nombres")
    private String nombres;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "DNI")
    private String dni;

    @Column(name = "Sexo")
    private char sexo;

    @Column(name = "Telefono")
    private String telefono;
}
