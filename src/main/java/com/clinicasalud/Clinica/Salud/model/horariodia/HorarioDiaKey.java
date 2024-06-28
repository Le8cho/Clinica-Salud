package com.clinicasalud.Clinica.Salud.model.horariodia;

import com.clinicasalud.Clinica.Salud.model.diaatencion.DiaAtencion;
import com.clinicasalud.Clinica.Salud.model.horariomedico.HorarioMedico;
import jakarta.persistence.*;

@Embeddable
public class HorarioDiaKey {

    @ManyToOne
    @JoinColumn(name = "ID_Horario_Medico")
    private HorarioMedico horarioMedico;

    @ManyToOne
    @JoinColumn(name = "ID_Dia")
    private DiaAtencion diaAtencion;

}
