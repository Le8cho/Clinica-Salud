package com.clinicasalud.Clinica.Salud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ClinicaSaludApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ClinicaSaludApplication.class, args);

		// Iniciar el menú principal
		Principal principal = context.getBean(Principal.class);
		principal.menu();
	}
}
