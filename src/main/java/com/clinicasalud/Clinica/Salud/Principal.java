package com.clinicasalud.Clinica.Salud;

import java.sql.*;
import java.util.Scanner;

public class Principal {
    private final Scanner input = new Scanner(System.in);

    // Conexión a la base de datos
    private Connection connect() {
        // URL de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/clinica_salud";
        String user = "root";
        String password = "root";
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void menu() {
        System.out.println("Hola");
        int opcion = 0;
        do {
            menuOpciones();
            opcion = input.nextInt();
            input.skip("\n");
            evaluandoOpcion(opcion);
        } while (opcion != 0);
    }

    public void menuOpciones() {
        System.out.println("""
                Elija la opción a través de su número:
                1.- Registrar médicos en el sistema
                2.- Registrar horario de médico
                3.- Registrar Paciente
                4.- Registrar cita
                5.- Modificar cita
                6.- Obtener reporte de citas
                7. Obtener citas programadas
                8.- Obtener citas por paciente
                0.- Saliste del Sistema, lo lamento papu
                """);
        System.out.print("Opcion: ");
    }

    public void evaluandoOpcion(int opcion) {
        switch (opcion) {
            case 1 -> nada();
            case 2 -> nada();
            case 3 -> nada();
            case 4 -> nada();
            case 5 -> { //FABRIZIO
                //EXPLICACIÓN:
                /*Solicita el DNI del paciente.
                  Consulta y muestra las citas del paciente.
                  Permite seleccionar una cita para modificar y elegir entre postergar o cancelar.
                  Actualiza la cita en la base de datos según la elección del usuario.*/
                System.out.print("Ingrese el DNI del paciente: ");
                String dni = input.nextLine();

                try (Connection conn = connect()) {
                    String query = "SELECT id, fecha_hora, profesional, estado FROM citas WHERE dni_paciente = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, dni);
                    ResultSet rs = pstmt.executeQuery();

                    if (!rs.next()) {
                        System.out.println("No se encontraron citas para este paciente.");
                        return;
                    }

                    do {
                        System.out.println("ID Cita: " + rs.getInt("id") + ", Fecha y Hora: " + rs.getTimestamp("fecha_hora") +
                                ", Profesional: " + rs.getString("profesional") + ", Estado: " + rs.getString("estado"));
                    } while (rs.next());

                    System.out.print("Ingrese el ID de la cita a modificar: ");
                    int idCita = input.nextInt();
                    input.skip("\n");

                    System.out.println("Seleccione la acción a realizar: ");
                    System.out.println("1. Postergar Cita");
                    System.out.println("2. Cancelar Cita");
                    int accion = input.nextInt();
                    input.skip("\n");

                    if (accion == 1) {
                        System.out.print("Ingrese la nueva fecha y hora (YYYY-MM-DD HH:MM:SS): ");
                        String nuevaFechaHora = input.nextLine();
                        String updateQuery = "UPDATE citas SET fecha_hora = ? WHERE id = ?";
                        PreparedStatement updatePstmt = conn.prepareStatement(updateQuery);
                        updatePstmt.setTimestamp(1, Timestamp.valueOf(nuevaFechaHora));
                        updatePstmt.setInt(2, idCita);
                        updatePstmt.executeUpdate();
                        System.out.println("Cita postergada exitosamente.");
                    } else if (accion == 2) {
                        String updateQuery = "UPDATE citas SET estado = 'Cancelada' WHERE id = ?";
                        PreparedStatement updatePstmt = conn.prepareStatement(updateQuery);
                        updatePstmt.setInt(1, idCita);
                        updatePstmt.executeUpdate();
                        System.out.println("Cita cancelada exitosamente.");
                    } else {
                        System.out.println("Acción inválida.");
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 6 -> nada();
            case 7 -> nada();
            case 8 -> { //FABRIZIO
                //EXPLICACION:
                /*Solicita el DNI del paciente.
                  Consulta y muestra las citas del paciente con detalles como fecha y hora, profesional, especialidad y estado.*/
                System.out.print("Ingrese el DNI del paciente: ");
                String dni = input.nextLine();

                try (Connection conn = connect()) {
                    String query = "SELECT fecha_hora, profesional, especialidad, estado FROM citas WHERE dni_paciente = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, dni);
                    ResultSet rs = pstmt.executeQuery();

                    if (!rs.next()) {
                        System.out.println("No se encontraron citas para este paciente.");
                        return;
                    }

                    do {
                        System.out.println("Fecha y Hora: " + rs.getTimestamp("fecha_hora") +
                                ", Profesional: " + rs.getString("profesional") + ", Especialidad: " + rs.getString("especialidad") +
                                ", Estado: " + rs.getString("estado"));
                    } while (rs.next());

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            default -> nada();
        }
    }

    public void nada() {
        System.out.println("nada");
    }

    public static void main(String[] args) {
        new Principal().menu();
    }
}
