package com.clinicasalud.Clinica.Salud.model.apoyodiagnostico;

import com.clinicasalud.Clinica.Salud.model.diagnostico.Diagnostico;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name ="Apoyo_Diagnostico")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ApoyoDiagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_Apoyo_Diagnostico", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ID_Diagnostico", nullable = false)
    private Diagnostico diagnostico;

    @Column(name= "Tipo_de_Apoyo")
    private String tipoDeApoyo;
}
