package com.clinicasalud.Clinica.Salud.model.horariomedico;

import com.clinicasalud.Clinica.Salud.model.horariodia.HorarioDia;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.List;

@Getter
@Setter
@Table(name = "Horario_Medico")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class HorarioMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Horario_Medico", nullable = false)
    private Long id;

    @Column(name = "Hora_Inicio", nullable = false)
    private Time horaInicio;

    @Column(name = "Hora_Fin", nullable = false)
    private Time horaFin;

    @OneToMany(mappedBy = "horarioDiaKey.horarioMedico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HorarioDia> horarioDiaList;
}