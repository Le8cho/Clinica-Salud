package com.clinicasalud.Clinica.Salud.model.persona;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Persona {

    @Column(name = "Nombres")
    private String nombres;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "DNI")
    private String dni;

    @Column(name = "Sexo")
    private String sexo;

    @Column(name = "Telefono")
    private String telefono;

    public Persona(String nombresPac, String apellidosPac, String dniPac, String sexoPac, String tlfPac) {
        this.nombres = nombresPac;
        this.apellidos = apellidosPac;
        this.dni = dniPac;
        this.sexo = sexoPac;
        this.telefono = tlfPac;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombres='" + nombres + '\'' +
                "\napellidos='" + apellidos + '\'' +
                "\ndni='" + dni + '\'' +
                "\nsexo=" + sexo +
                "\ntelefono='" + telefono + '\'' +
                '}';
    }
}
