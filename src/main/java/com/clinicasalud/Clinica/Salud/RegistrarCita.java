package com.clinicasalud.Clinica.Salud;

import com.clinicasalud.Clinica.Salud.model.cita.CitaService;
import com.clinicasalud.Clinica.Salud.model.cita.EstadoCita;
import com.clinicasalud.Clinica.Salud.model.medico.Especialidad;
import com.clinicasalud.Clinica.Salud.model.medico.EspecialidadService;
import com.clinicasalud.Clinica.Salud.model.medico.MedicoService;
import com.clinicasalud.Clinica.Salud.model.paciente.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

@Component
public class RegistrarCita {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private EspecialidadService especialidadService;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private CitaService citaService;
    private final Scanner input = new Scanner(System.in);
    public void run() {
        //Validar registro de lunes a sabado de 7 a 19 horas
        LocalTime horaSistema = LocalTime.now();
        LocalDate diaSistema = LocalDate.now();
        DayOfWeek diaSemana = diaSistema.getDayOfWeek();
        if (diaSemana == DayOfWeek.SUNDAY || horaSistema.isBefore(LocalTime.of(7, 0)) || horaSistema.isAfter(LocalTime.of(19, 0))) {
            System.out.println("Solo se pueden registrar citas de lunes a sábado entre las 7 y las 19 horas.");
            return;
        }

        try{

            System.out.print("Ingrese DNI del paciente: ");
            String dni = input.nextLine();
            // Validar si el paciente existe
            if (!pacienteService.pacienteExiste(dni)) {
                System.out.println("El paciente no está registrado en el sistema.");
                return;
            }

            System.out.println("Especialidades disponibles:");
            List<String> especialidadesDisponibles = especialidadService.obtenerEspecialidadesDisponibles();
            especialidadesDisponibles.forEach(System.out::println);

            System.out.print("\nIngrese la especialidad que desea ver:");
            String nombreEspecialidad = input.nextLine();
            Especialidad especialidad = Especialidad.fromString(nombreEspecialidad);

            System.out.println("\nMédicos en la especialidad '" + nombreEspecialidad + "':");
            List<String> medicosEspecialidad = especialidadService.obtenerMedicoEspecialidad(especialidad);
            medicosEspecialidad.forEach(System.out::println);

            System.out.println("Ingrese el medico para registrar su cita (Apellido, nombre):");
            String nombreMedico = input.nextLine().trim();
            String apellidoMedico = input.nextLine().trim();

            System.out.println("Horarios del médico "+ apellidoMedico+","+ nombreMedico+": " );
            List<String> horarioMedico = medicoService.obtenerHorarioMedico(apellidoMedico,nombreMedico);
            horarioMedico.forEach(System.out::println);

            System.out.println("Ingrese el horario (Fecha YYYY-MM-DD, hora HH:mm): ");
            String horarioDia = input.nextLine();
            String horarioHora = input.nextLine();

            // Validar que haya al menos 30 minutos de anticipación desde ahora hasta la cita
            LocalDate dia = LocalDate.parse(horarioDia);
            LocalTime hora = LocalTime.parse(horarioHora);
            LocalDateTime ahora = LocalDateTime.now();
            LocalDateTime horaCitaDateTime = LocalDateTime.of(dia, hora);
            long minutosAnticipacion = ChronoUnit.MINUTES.between(ahora, horaCitaDateTime);

            if (minutosAnticipacion < 30) {
                System.out.println("Debe programar la cita con al menos 30 minutos de anticipación.");
                return;
            }
            // Validar que la hora de la cita sea válida (8, 9, 10, etc.)
            if (hora.getMinute() != 0 || hora.getSecond() != 0) {
                System.out.println("La cita debe comenzar en una hora exacta (ejemplo: 8:00, 9:00, 10:00).");
                return;
            }
            System.out.println("¿Confirma el registro de la cita? (S/N): ");
            System.out.println("Especialidad: "+nombreEspecialidad);
            System.out.println("Medico: "+nombreMedico);
            System.out.println("Dia: "+horarioDia);
            System.out.println("Hora: "+horarioHora);
            String confirmacion = input.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                Long idPaciente = pacienteService.obtenerIdPacientePorDni(dni);
                Long idMedico = medicoService.obtenerIdMedicoPorNombreYApellido(nombreMedico, apellidoMedico);
                LocalDate fecha = LocalDate.parse(horarioDia);
                LocalTime horaInicio = LocalTime.parse(horarioHora);
                EstadoCita estadoCita = EstadoCita.Programada;

                // Validar si ya existe una cita con el médico en la fecha y hora especificadas
                if (citaService.existeCita(idMedico,fecha,horaInicio)) {
                    System.out.println("El médico ya tiene una cita programada en esta fecha y hora.");
                    return;
                }
                citaService.registrarCita(idPaciente, idMedico, horaInicio, fecha, "Motivo", estadoCita);
            } else {
                System.out.println("Registro de cita cancelado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
