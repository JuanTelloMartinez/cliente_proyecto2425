package es.ieslavereda.baseoficios.activities.model;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private double oficio_idOficio;

    public Usuario(int idUsuario, String nombre, String apellidos, double oficio_idOficio) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.oficio_idOficio = oficio_idOficio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public double getOficio_idOficio() {
        return oficio_idOficio;
    }
}