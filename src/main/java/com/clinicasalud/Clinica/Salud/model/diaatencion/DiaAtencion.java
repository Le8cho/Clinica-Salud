package com.clinicasalud.Clinica.Salud.model.diaatencion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="Dia_Atencion")
@Entity(name="DiaAtencion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")

public class DiaAtencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_Dia")
    private Long idDia;

    @Column(name = "Dia")
    @Enumerated(EnumType.STRING) //indicamos que es un enum de tipo STRING
    private DiaSemana diaSemana;
}
