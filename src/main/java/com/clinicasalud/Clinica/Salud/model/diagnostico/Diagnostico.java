package com.clinicasalud.Clinica.Salud.model.diagnostico;
import com.clinicasalud.Clinica.Salud.model.apoyodiagnostico.ApoyoDiagnostico;
import com.clinicasalud.Clinica.Salud.model.cita.Cita;
import com.clinicasalud.Clinica.Salud.model.tratamiento.Tratamiento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name="Diagnostico")
@Entity(name="Diagnostico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "idDiagnostico")
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_Diagnostico")
    private Long idDiagnostico;

    @ManyToOne
    @JoinColumn(name = "ID_Cita")
    private Cita cita;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Sospecha_Diagnostica")
    private Boolean sopechaDiagnostica;

    @OneToMany(mappedBy = "diagnostico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Tratamiento> tratamientos;

    @OneToMany(mappedBy = "diagnostico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<ApoyoDiagnostico> apoyoDiagnosticos;
}
