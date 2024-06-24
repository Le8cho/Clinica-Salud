package com.clinicasalud.Clinica.Salud.model.persona;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DatosPersona {

    @Column(name = "Nombres")
    private String nombres;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "Telefono")
    private String dni;

    @Column(name = "Sexo")
    private char sexo;

    @Column(name = "Telefono")
    private String telefono;
}
