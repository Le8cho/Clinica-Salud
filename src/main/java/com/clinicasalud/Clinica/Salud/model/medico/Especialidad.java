
package com.clinicasalud.Clinica.Salud.model.medico;


public enum Especialidad {
    CARDIOLOGIA ("cardiologia"),
    DERMATOLOGIA ("dermatologia"),
    NEUROLOGIA("neurologia"),
    PEDIATRIA("pediatria"),
    GASTROENTEROLOGIA("gastroenterologia");

    private String nombre;
    Especialidad(String nombre) {
        this.nombre = nombre;
    }

    public static Especialidad fromString(String text) {
        for (Especialidad b : Especialidad.values()) {
            if (b.nombre.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
    }




