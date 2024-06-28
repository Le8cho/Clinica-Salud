package com.clinicasalud.Clinica.Salud.model.tratamiento;

import com.clinicasalud.Clinica.Salud.model.diagnostico.Diagnostico;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "Tratamiento")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_Tratamiento", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ID_Diagnostico", nullable = false)
    private Diagnostico diagnostico;

    @Column(name= "Duracion")
    private Integer duracion;

    @Column(name= "Prescripcion")
    private String prescripcion;

    @Column(name= "Procedimiento")
    private String procedimiento;
}
