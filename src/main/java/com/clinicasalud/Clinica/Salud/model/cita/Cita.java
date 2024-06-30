package com.clinicasalud.Clinica.Salud.model.cita;

import com.clinicasalud.Clinica.Salud.model.diagnostico.Diagnostico;
import com.clinicasalud.Clinica.Salud.model.medico.Medico;
import com.clinicasalud.Clinica.Salud.model.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name="Cita")
@Entity(name="Cita")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "idCita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_Cita")
    private Long idCita;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Paciente")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Medico")
    private Medico medico;

    @Column(name = "Hora_Inicio")
    private LocalDateTime horaInicio;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name= "Motivo_Consulta")
    private String motivoConsulta;

    @Column(name = "Estado_Cita")
    @Enumerated(EnumType.STRING)
    private EstadoCita estadoCita;

    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Diagnostico> diagnosticos;
}
