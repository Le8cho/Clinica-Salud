package com.clinicasalud.Clinica.Salud;

import com.clinicasalud.Clinica.Salud.model.cita.EstadoCita;
import com.clinicasalud.Clinica.Salud.model.diaatencion.DiaAtencion;
import com.clinicasalud.Clinica.Salud.model.diaatencion.DiaAtencionService;
import com.clinicasalud.Clinica.Salud.model.medico.Especialidad;
import com.clinicasalud.Clinica.Salud.model.medico.Medico;
import com.clinicasalud.Clinica.Salud.model.medico.MedicoService;
import com.clinicasalud.Clinica.Salud.model.paciente.Paciente;
import com.clinicasalud.Clinica.Salud.model.paciente.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsultasCorrelacionadas {
    private final Scanner input = new Scanner(System.in);
    @Autowired
    MedicoService medicoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    DiaAtencionService diaAtencionService;
    public void run(){
        System.out.println("Consultas Disponibles: ");
        int opcion=-1;
        do {
            menuOpciones();
            opcion = input.nextInt();
            evaluandoOpcion(opcion);
        } while (opcion != 0);
    }
    public void menuOpciones() {
        System.out.println("""
                Elija la opción a través de su número:
                1.- Consulta de médicos con citas programadas
                2.- Consultar todos los pacientes que tienen al menos una cita programada:
                3.- Obtener todos los médicos con su horario correspondiente
                4.- Obtener todos los pacientes con sus citas y los nombres completos de los médicos
                0.- Saliste del Sistema lo lamento papu
                """);
        System.out.print("Opción: ");
    }
    public void evaluandoOpcion(int opcion) {
        switch (opcion) {
            case 1 -> consultarMedicosCitasProgramadas();
            case 2 -> consultarPacientesCitasProgramadas();
            case 3 -> obtenerTodosMedicosHorario();
            case 4 ->obtenerTodosPacientesConCitasYMedicos();
            case 0 -> System.out.println("Volviendo al menu principal.");
            default -> System.out.println("Opción no válida. Intente de nuevo.");
        }
    }
    public void obtenerTodosPacientesConCitasYMedicos(){
        List<Object[]> pacientesConCitasYMedicos = pacienteService.obtenerTodosPacientesConCitasYMedicos();

        // Imprimir los resultados en la consola
        for (Object[] paciente : pacientesConCitasYMedicos) {
            Long idPaciente = (Long) paciente[0];
            String nombrePaciente = (String) paciente[1];
            String apellidoPaciente = (String) paciente[2];
            String nombreMedico = (String) paciente[3];
            Long idCita = (Long) paciente[4];
            LocalTime horaInicio = (LocalTime) paciente[5];
            LocalDate fecha = (LocalDate) paciente[6];
            String motivoConsulta = (String) paciente[7];
            EstadoCita estadoCita = (EstadoCita) paciente[8]; // Asumiendo que EstadoCita es un enum

            System.out.println("ID Paciente: " + idPaciente +
                    ", Nombre Paciente: " + nombrePaciente +
                    " " + apellidoPaciente +
                    ", Nombre Médico: " + nombreMedico +
                    ", ID Cita: " + idCita +
                    ", Hora Inicio: " + horaInicio.toString() +
                    ", Fecha: " + fecha.toString() +
                    ", Motivo Consulta: " + motivoConsulta +
                    ", Estado Cita: " + estadoCita.toString()); // Convertir enum a String si es necesario
        }
    }
    public void obtenerTodosMedicosHorario(){
        List<Object[]> medicosConHorario = medicoService.consultarTodosMedicosHorario();

        // Imprimir los resultados en la consola
        for (Object[] medico : medicosConHorario) {
            Long id = (Long) medico[0];
            String nombre = (String) medico[1];
            String apellido = (String) medico[2];
            Especialidad especialidad = (Especialidad) medico[3]; // Manejar correctamente el tipo Enum
            String horario = (String) medico[4];

            System.out.println("ID: " + id +
                    ", Nombre: " + nombre +
                    ", Apellido: " + apellido +
                    ", Especialidad: " + especialidad.toString() + // Convertir enum a String si es necesario
                    ", Horario: " + horario);
        }
    }
    public void consultarMedicosCitasProgramadas(){
        List<Medico> medicosConCitas = medicoService.encontrarMedicosConCitas();
        System.out.println("Los siguientes Medicos tienen al menos una cita programada:");
        for (Medico medico : medicosConCitas) {
            System.out.println(medico);
        }
    }
    public void consultarPacientesCitasProgramadas(){
        List<Paciente> pacientesConCitas = pacienteService.encontrarPacientesConCitas();
        System.out.println("Los siguientes pacientes tienen al menos una cita programada:");
        for (Paciente paciente : pacientesConCitas) {
            System.out.println(paciente);
        }
    }

}
