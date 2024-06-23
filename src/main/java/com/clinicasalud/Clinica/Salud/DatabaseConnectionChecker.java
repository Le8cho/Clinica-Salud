package com.clinicasalud.Clinica.Salud;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionChecker
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseConnectionChecker(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void checkDatabaseConnection() {
        try {
            String sqlQuery = "SELECT * FROM Medico"; // Ejemplo: selecciona los primeros 5 registros de la tabla Customers
            jdbcTemplate.queryForList(sqlQuery).forEach(row -> {
                System.out.println(row.toString());
            });
            System.out.println("Conexión a la base de datos establecida correctamente.");
        } catch (Exception e) {
            System.err.println("Error al conectar o consultar la base de datos: " + e.getMessage());
        }
    }
}