package com.clinicasalud.Clinica.Salud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class ClinicaSaludApplication implements CommandLineRunner {

	@Autowired
	private Principal principal; // Inject Principal component

	public static void main(String[] args) {
		SpringApplication.run(ClinicaSaludApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.menu();
	}
}
