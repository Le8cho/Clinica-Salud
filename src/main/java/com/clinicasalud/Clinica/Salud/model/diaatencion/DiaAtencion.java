package com.clinicasalud.Clinica.Salud.model.diaatencion;
import com.clinicasalud.Clinica.Salud.model.diagnostico.Diagnostico;
import com.clinicasalud.Clinica.Salud.model.horariodia.HorarioDia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="Dia_Atencion")
@Entity(name="DiaAtencion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "idDia")

public class DiaAtencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_Dia")
    private Long idDia;

    @Column(name = "Dia")
    @Enumerated(EnumType.STRING) //indicamos que es un enum de tipo STRING
    private DiaSemana diaSemana;

    @OneToMany(mappedBy = "horarioDiaKey.diaAtencion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HorarioDia> horarioDias;

    @Override
    public String toString() {
        return "DiaAtencion{" +
                "idDia=" + idDia +
                ", diaSemana=" + diaSemana +
                '}';
    }
}
