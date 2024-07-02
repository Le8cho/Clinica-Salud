package com.clinicasalud.Clinica.Salud.model.paciente;

import com.clinicasalud.Clinica.Salud.model.cita.Cita;
import com.clinicasalud.Clinica.Salud.model.persona.Persona;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name="Paciente")
@Entity(name="Paciente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "idPaciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_Paciente")
    private Long idPaciente;

    @Embedded
    private Persona persona;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Fecha_Nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "Estado_Paciente")
    private boolean estadoPaciente;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas;

    public Paciente(DatosCrearPaciente datosCrearPaciente) {
        this.persona = new Persona(
                datosCrearPaciente.datosPersona().nombres(),
                datosCrearPaciente.datosPersona().apellidos(),
                datosCrearPaciente.datosPersona().dni(),
                datosCrearPaciente.datosPersona().sexo(),
                datosCrearPaciente.datosPersona().telefono()
        );
        this.direccion = datosCrearPaciente.direccion();
        this.fechaNacimiento = datosCrearPaciente.fechaNacimiento();
        this.estadoPaciente = true;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                "\npersona=" + persona +
                "\ndireccion='" + direccion + '\'' +
                "\nfechaNacimiento=" + fechaNacimiento +
                "\nestadoPaciente=" + estadoPaciente +
                '}';
    }

}
