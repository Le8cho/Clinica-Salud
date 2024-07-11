package com.clinicasalud.Clinica.Salud;

import com.clinicasalud.Clinica.Salud.model.cita.*;
import com.clinicasalud.Clinica.Salud.model.medico.RegistrarMedico;
import com.clinicasalud.Clinica.Salud.model.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;
@Component
public class Principal {
    @Autowired
    private RegistrarMedico registrarMedico;
    @Autowired
    private ConsultasCorrelacionadas consultasCorrelacionadas;
    @Autowired
    private RegistrarCita registrarCita;
    @Autowired
    private ModificarCancelarCita modificarCancelarCita;
    @Autowired
    private ObtenerCitasProgramadas obtenerCitasProgramadas;
    @Autowired
    private ObtenerCitasPorPaciente obtenerCitasPorPaciente;
    @Autowired
    private ObtenerReporteCitas obtenerReporteCitas;
    @Autowired
    private ConsultarDatosNuevoPaciente consultarDatosNuevoPaciente;
    private final Scanner input = new Scanner(System.in);
    public void menu() {
        System.out.println("Bienvenido al sistema de gestión de la Clínica de Salud");
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
                1.- Registrar médicos
                2.- Registrar Paciente
                3.- Registrar cita
                4.- Modificar/cancelar cita
                5.- Obtener reporte de citas
                6.- Obtener citas programadas
                7.- Obtener citas por paciente
                8.- Consultas Correlacionadas
                0.- Saliste del Sistema lo lamento papu
                """);
        System.out.print("Opción: ");
    }
    public void evaluandoOpcion(int opcion) {
        switch (opcion) {
            case 1 -> registrarMedico.run();
            case 2 -> consultarDatosNuevoPaciente.run();
            case 3 -> registrarCita.run();
            case 4 -> modificarCancelarCita.run();
            case 5 -> obtenerReporteCitas.run();
            case 6 -> obtenerCitasProgramadas.run();
            case 7 -> obtenerCitasPorPaciente.run();
            case 8 -> consultasCorrelacionadas.run();
            case 0 -> System.out.println("Saliendo del sistema...");
            default -> System.out.println("Opción no válida. Intente de nuevo.");
        }
    }
}