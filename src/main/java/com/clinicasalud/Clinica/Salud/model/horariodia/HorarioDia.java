package com.clinicasalud.Clinica.Salud.model.horariodia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="Horario_Dia")
@Entity(name="HorarioDia")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class HorarioDia {

    @EmbeddedId
    private HorarioDiaKey horarioDiaKey;

}
