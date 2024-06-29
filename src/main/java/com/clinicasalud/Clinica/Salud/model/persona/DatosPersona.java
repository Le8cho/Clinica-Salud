package com.clinicasalud.Clinica.Salud.model.persona;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@NoArgsConstructor
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

    public DatosPersona(String nombresPac, String apellidosPac, String dniPac, char sexoPac, String tlfPac) {
        this.nombres = nombresPac;
        this.apellidos = apellidosPac;
        this.dni = dniPac;
        this.sexo = sexoPac;
        this.telefono = tlfPac;
    }
}
