package com.clinicasalud.Clinica.Salud.model.horariomedico;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Getter
@Setter
@Table(name = "Medico")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class HorarioMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_Horario_Medico", nullable = false)
    private Long id;

    @Column(name= "Hora_Inicio",nullable = false)
    private Time horaInicio;
    @Column(name= "Hora_Fin",nullable = false)
    private Time horaFin;
}
