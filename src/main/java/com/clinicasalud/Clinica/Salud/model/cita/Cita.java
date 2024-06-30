package com.clinicasalud.Clinica.Salud.model.cita;

import com.clinicasalud.Clinica.Salud.model.diagnostico.Diagnostico;
import com.clinicasalud.Clinica.Salud.model.medico.Medico;
import com.clinicasalud.Clinica.Salud.model.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Table(name="Cita")
@Entity(name="Cita")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "idCita")

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_Cita")
    private Long idCita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Paciente") //join column == el nombre de la foreign key en la tabla
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Medico")
    private Medico medico;

    @Column(name = "Hora_Inicio")
    private LocalTime horaInicio;

    @Column(name = "Fecha")
    private LocalDate fecha;

    @Column(name= "Motivo_Consulta")
    private String motivoConsulta;

    @Column(name = "Estado_Cita")
    @Enumerated(EnumType.STRING) //indicamos que es un enum de tipo STRING
    private EstadoCita estadoCita;

    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Diagnostico> diagnosticos;
}