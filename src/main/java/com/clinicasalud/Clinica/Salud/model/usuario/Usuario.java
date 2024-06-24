package com.clinicasalud.Clinica.Salud.model.usuario;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name ="Usuario")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_Usuario", nullable = false)
    private Long id;

    @Column(name= "Username", nullable=false,unique=true)
    private String username;

    @Column(name="Password", nullable=false)
    private String password;

    @Column(name="Rol_Usuario", nullable=false)
    private String rolUsuario;
}
