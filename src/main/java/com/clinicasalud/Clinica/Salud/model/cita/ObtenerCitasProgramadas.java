package com.clinicasalud.Clinica.Salud.model.cita;

import com.clinicasalud.Clinica.Salud.model.medico.MedicoService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class ObtenerCitasProgramadas {
    private final Scanner input = new Scanner(System.in);
    @Autowired
    MedicoService medicoService;
    @Autowired
    CitaService citaService;

    public void run() {
        boolean novalido=false;
        String contrasena;
        do{
            System.out.println("Ingrese el DNI del medico");
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

        List<Cita> citas = citaService.obtenerCitasPorMedicoyFecha(id, fechaIngresada);
        if (citas != null) {
            imprimirReporte(citas);
        } else {
            System.out.println("No hay citas en esta fecha. ");
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
}
