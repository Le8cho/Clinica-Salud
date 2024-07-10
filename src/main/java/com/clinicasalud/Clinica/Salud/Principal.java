package com.clinicasalud.Clinica.Salud;

import com.clinicasalud.Clinica.Salud.model.cita.*;
import com.clinicasalud.Clinica.Salud.model.horariomedico.HorarioMedico;
import com.clinicasalud.Clinica.Salud.model.horariomedico.HorarioMedicoJpaRepository;
import com.clinicasalud.Clinica.Salud.model.horariomedico.HorarioMedicoService;
import com.clinicasalud.Clinica.Salud.model.medico.Especialidad;
import com.clinicasalud.Clinica.Salud.model.medico.EspecialidadService;
import com.clinicasalud.Clinica.Salud.model.medico.MedicoService;
import com.clinicasalud.Clinica.Salud.model.paciente.DatosCrearPaciente;
import com.clinicasalud.Clinica.Salud.model.paciente.Paciente;
import com.clinicasalud.Clinica.Salud.model.paciente.PacienteRepository;
import com.clinicasalud.Clinica.Salud.model.paciente.PacienteService;
//import com.clinicasalud.Clinica.Salud.model.medico.Especialidad;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;

import com.clinicasalud.Clinica.Salud.model.cita.Cita;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Principal {

    private PacienteRepository pacienteRepository;
    private PacienteService pacienteService;
    private EspecialidadService especialidadService;
    private MedicoService medicoService;
    private CitaRepository citaRepository;
    @Autowired
    private HorarioMedicoService horarioMedicoService;
    @Autowired
    private RegistrarMedico registrarMedico;
    @Autowired
    private RegistrarCita registrarCita;
    private CitaService citaService;
    private final Scanner input = new Scanner(System.in);

    @Autowired
    public Principal(PacienteRepository pacienteRepository, PacienteService pacienteService,
                     EspecialidadService especialidadService, MedicoService medicoService, CitaService citaService){
        this.pacienteRepository = pacienteRepository;
        this.pacienteService = pacienteService;
        this.especialidadService = especialidadService;
        this.medicoService = medicoService;
        this.citaService = citaService;
    }

    public void menu() {
        System.out.println("Bienvenido al sistema de gestión de la Clínica de Salud");

        int opcion;
        do {
            menuOpciones();
            while (!input.hasNextInt()) {
                System.out.println("Por favor ingrese un número válido.");
                input.next(); // Limpiar entrada inválida
            }
            opcion = input.nextInt();
            input.nextLine(); // Consumir el salto de línea
            evaluandoOpcion(opcion);
        } while (opcion != 0);
    }

    public void menuOpciones() {
        System.out.println("""
                Elija la opción a través de su número:
                1.- Registrar médicos y horario en el sistema
                2.- Registrar horario de médico
                3.- Registrar Paciente
                4.- Registrar cita
                5.- Modificar/cancelar cita
                6.- Obtener reporte de citas
                7.- Obtener citas programadas
                8.- Obtener citas por paciente
                0.- Saliste del Sistema lo lamento papu
                """);
        System.out.print("Opción: ");
    }

    public void evaluandoOpcion(int opcion) {
        switch (opcion) {
            case 1 -> registrarMedico.run();
            case 2 -> registrarMedico.registrarHorarioMedico();
            case 3 -> consultarDatosNuevoPaciente();
            case 4 -> registrarCita.run();
            case 5 -> modificarCancelarCita();
            case 6 -> obtenerReporteCitas();
            case 7 -> obtenerCitasProgramadas();
            case 8 -> obtenerCitasPorPaciente();
            case 0 -> System.out.println("Saliendo del sistema...");
            default -> System.out.println("Opción no válida. Intente de nuevo.");
        }
    }



    public void modificarCancelarCita() {
                System.out.println("1.- Modificar cita");
                System.out.println("2.- Cancelar cita");
                System.out.print("Seleccione una opción: ");
                int subOpcion = input.nextInt();
                input.skip("\n");

                switch (subOpcion) {
                    case 1 -> modificarCita();
                    case 2 -> cancelarCita();
                    default -> System.out.println("Opción inválida.");
                }
    }
                           
    public void modificarCita() {
        System.out.print("Ingrese el ID de la cita a modificar: ");
        Long idCita = input.nextLong();
        input.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese la nueva fecha (YYYY-MM-DD): ");
        String nuevaFecha = input.nextLine();

        System.out.print("Ingrese la nueva hora (HH:MM): ");
        String nuevaHora = input.nextLine();

        if (!validarFecha(nuevaFecha)) {
            System.out.println("Fecha inválida.");
            return;
        }

        if (!validarHora(nuevaHora)) {
            System.out.println("Hora inválida.");
            return;
        }

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaIngresada = LocalDate.parse(nuevaFecha, DateTimeFormatter.ISO_LOCAL_DATE);

        if (fechaIngresada.isBefore(fechaActual)) {
            System.out.println("No se puede programar una cita en fechas anteriores a la actual.");
            return;
        }

        Cita cita = citaService.modificarCita(idCita, nuevaFecha, nuevaHora);
        if (cita != null) {
            System.out.println("Cita modificada exitosamente: " + cita);
        } else {
            System.out.println("No se encontró una cita con ID " + idCita);
        }
    }
                           
        public boolean validarFecha(String fecha) {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(fecha);
        return matcher.matches();
    }

    public boolean validarHora(String hora) {
        Pattern pattern = Pattern.compile("\\d{2}:\\d{2}");
        Matcher matcher = pattern.matcher(hora);
        return matcher.matches();
    }

    public void cancelarCita() {
        System.out.print("Ingrese el ID de la cita a cancelar: ");
        Long idCita = input.nextLong();
        input.nextLine(); // Consumir el salto de línea

        System.out.print("¿Está seguro de cancelar esta cita? (S/N): ");
        String confirmacion = input.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            boolean cancelada = citaService.cancelarCita(idCita);
            if (cancelada) {
                System.out.println("Cita cancelada correctamente.");
            } else {
                System.out.println("No se encontró una cita con ID " + idCita);
            }
        } else {
            System.out.println("Operación cancelada por el usuario.");
        }
    }
                           
    public void obtenerCitasProgramadas() {
        boolean novalido=false;
        String contrasena;
        do{
            System.out.println("Ingrese la contraseña");
            contrasena = input.nextLine();
            if (!medicoService.medicoExiste(contrasena)) {
                System.out.println("El medico no existe.");
                novalido=true;
            }
        }while(novalido);
        Long id=medicoService.obtenerIdMedicoPorDni(contrasena);
        String fecha;
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaIngresada;
        do{
            System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
            fecha = input.nextLine();
            fechaIngresada = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
            if (fechaIngresada.isBefore(fechaActual)) {
                System.out.println("Ingrese una fecha válida.");
                novalido=true;
            }else{
                novalido=false;
            }
        }while(novalido);

        List <Cita> citas = citaService.obtenerCitasPorMedicoyFecha(id, fechaIngresada);
        if (citas != null) {
            imprimirReporte(citas);
        } else {
            System.out.println("No hay citas en esta fecha. ");
        }
    }

    public void obtenerCitasPorPaciente() {
        System.out.print("Ingrese el ID del paciente: ");
        Long idPaciente = input.nextLong();
        input.nextLine(); // Consumir el salto de línea

        System.out.println("Citas del paciente con ID " + idPaciente + ":");
        citaService.obtenerCitasPorPaciente(idPaciente).forEach(System.out::println);
    }

    public void obtenerReporteCitas() {
        System.out.println("Generar reporte de citas:");
        System.out.println("Seleccione el criterio de filtrado:");
        System.out.println("1. Rango de fechas");
        System.out.println("2. Profesional de salud");
        System.out.println("3. Estado de la cita");

        int criterio = input.nextInt();
        input.nextLine(); // Consumir el salto de línea

        switch (criterio) {
            case 1 -> {
                System.out.println("Ingrese la fecha de inicio (yyyy-MM-dd HH:mm): ");
                String startDateStr = input.nextLine();
                System.out.println("Ingrese la fecha de fin (yyyy-MM-dd HH:mm): ");
                String endDateStr = input.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime startDate = LocalDateTime.parse(startDateStr, formatter);
                LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);

                List<Cita> citas = citaService.obtenerCitasPorRangoDeFechas(startDate, endDate);
                imprimirReporte(citas);
            }
            case 2 -> {
                System.out.println("Ingrese el ID del profesional de salud: ");
                Long medicoId = input.nextLong();
                input.nextLine(); // Consumir el salto de línea

                List<Cita> citas = citaService.obtenerCitasPorMedico(medicoId);
                imprimirReporte(citas);
            }
            case 3 -> {
                System.out.println("Ingrese el estado de la cita (Programada, Atendida, Cancelada): ");
                String estadoStr = input.nextLine();
                System.out.println("Estado ingresado: " + estadoStr); // Añadir esta línea
                EstadoCita estado = EstadoCita.valueOf(estadoStr);
                List<Cita> citas = citaService.obtenerCitasPorEstado(estado);
                imprimirReporte(citas);
            }
            default -> System.out.println("Criterio no válido.");
        }
    }

    private void imprimirReporte(List<Cita> citas) {
        if (citas.isEmpty()) {
            System.out.println("No se encontraron citas con los criterios seleccionados.");
        } else {
            for (Cita cita : citas) {
                Hibernate.initialize(cita.getPaciente()); // Inicializar Paciente
                Hibernate.initialize(cita.getMedico()); // Inicializar Medico
                System.out.printf("Cita ID: %d, Paciente: %s %s, Médico: %s %s, Fecha: %s,Hora: %s, Estado: %s%n",
                        cita.getIdCita(),
                        cita.getPaciente().getPersona().getNombres(),
                        cita.getPaciente().getPersona().getApellidos(),
                        cita.getMedico().getNombres(),
                        cita.getMedico().getApellidos(),
                        cita.getFecha().toString(),
                        cita.getHoraInicio().toString(),
                        cita.getEstadoCita().toString());
            }
        }
    }

    //Registrar Paciente
    public void consultarDatosNuevoPaciente() {
        System.out.println("Ingresar Nombres por favor:");
        var nombresPac = input.nextLine();
        System.out.println("Ingresar apellidos por favor:");
        var apellidosPac = input.nextLine();
        System.out.println("Ingresar Numero de DNI por favor:");
        var dniPac = input.nextLine();
        System.out.println("Ingresar Sexo (M/F) por favor:");
        var sexoPac = input.nextLine().toUpperCase();
        System.out.println("Ingresar Numero de Telefono por favor:");
        var tlfPac = input.nextLine();
        System.out.println("Ingresar direccion por favor:");
        var direccion = input.nextLine();
        System.out.println("Ingresar Fecha de Nacimiento (AAAA-MM-DD) por favor:");
        var fechaNacPac = LocalDate.parse(input.nextLine());

        //Validamos los campos ingresado 
        DatosCrearPaciente datosCrearPaciente = new DatosCrearPaciente(
                nombresPac,
                apellidosPac,
                dniPac,
                sexoPac,
                tlfPac,
                direccion,
                fechaNacPac
        );

        Paciente paciente = pacienteService.validarDatos(datosCrearPaciente);

        pacienteRepository.save(paciente);
    }
}