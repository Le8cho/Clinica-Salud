package com.clinicasalud.Clinica.Salud.model.paciente;

import com.clinicasalud.Clinica.Salud.model.cita.Cita;
import com.clinicasalud.Clinica.Salud.model.diagnostico.Diagnostico;
import com.clinicasalud.Clinica.Salud.model.persona.DatosPersona;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name="Paciente")
@Entity(name="Paciente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "idPaciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_Paciente")
    private Long idPaciente;

    @Embedded
    private DatosPersona persona;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Fecha_Nacimiento")
    private LocalDateTime fechaNacimiento;

    @Column(name = "Estado_Paciente")
    private boolean estadoPaciente;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas;

}
