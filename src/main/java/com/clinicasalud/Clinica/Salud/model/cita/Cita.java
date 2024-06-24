package com.clinicasalud.Clinica.Salud.model.cita;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="Cita")
@Entity(name="Cita")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_Cita")
    private Long idCita;

    @ManyToOne
    @JoinColumn(name = "ID_Paciente") //join column == el nombre de la foreign key en la tabla
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "ID_Medico")
    private Medico medico;

    @Column(name = "Hora_Inicio")
    private LocalDateTime horaInicio;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name= "Motivo_Consulta")
    private String motivoConsulta;

    @Column(name = "Estado_Cita")
    @Enumerated(EnumType.STRING) //indicamos que es un enum de tipo STRING
    private EstadoCita estadoCita;
}