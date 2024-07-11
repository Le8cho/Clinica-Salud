package com.clinicasalud.Clinica.Salud.model.horariomedico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
@Service
public class HorarioMedicoService {
    private final Scanner input = new Scanner(System.in);
    @Autowired
    private HorarioMedicoJpaRepository horarioMedicoJpaRepository;
    public HorarioMedico registrarHorarioMedico(LocalTime horadeInicio, LocalTime horaFin){
        HorarioMedico horarioMedico = new HorarioMedico();
        horarioMedico.setHoraInicio(Time.valueOf(horadeInicio));
        horarioMedico.setHoraFin(Time.valueOf(horaFin));
        return horarioMedicoJpaRepository.save(horarioMedico);
    }


}
