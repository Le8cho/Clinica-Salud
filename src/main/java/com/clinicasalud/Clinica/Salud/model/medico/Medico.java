package com.clinicasalud.Clinica.Salud.model.medico;

import com.clinicasalud.Clinica.Salud.model.cita.Cita;
import com.clinicasalud.Clinica.Salud.model.horariomedico.HorarioMedico;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Table(name = "Medico")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_Medico", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ID_Horario_Medico")
    private HorarioMedico idHorarioMedico;

    @Column(name= "Nombres",nullable = false)
    private String nombres;

    @Column(name= "Apellidos",nullable = false)
    private String apellidos;

    @Column(name= "DNI",length = 8,nullable = false, unique = true)
    private String dni;

    @Column(name= "Telefono")
    private String telefono;

    @Column(name= "Correo")
    private String correo;

    @Column(name= "Sexo",length = 1)
    private char sexo;

    @Column(name= "Estado",nullable = false)
    private Integer estado;

    @Column(name= "Especialidad",nullable = false)
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citaList;

    public void setHorarioMedico(HorarioMedico horario) {
    }
}
