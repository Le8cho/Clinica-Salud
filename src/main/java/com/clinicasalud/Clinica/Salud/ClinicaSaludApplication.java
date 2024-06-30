package com.clinicasalud.Clinica.Salud;

import com.clinicasalud.Clinica.Salud.model.paciente.PacienteRepository;
import com.clinicasalud.Clinica.Salud.model.paciente.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClinicaSaludApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ClinicaSaludApplication.class, args);
	}

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private PacienteService pacienteService;

	//Ejecucion en Consola
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(pacienteRepository, pacienteService);
		principal.menu();
	}
}