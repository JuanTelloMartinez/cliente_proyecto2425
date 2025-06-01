package es.ieslavereda.baseoficios.activities.model;

import static es.ieslavereda.baseoficios.base.Parameters.URL_IMAGE_BASE;
import static es.ieslavereda.baseoficios.base.Parameters.URL_IMG_OFICIO;

import android.graphics.Bitmap;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private int oficio_idOficio;
    private Bitmap image;

    public Usuario(int idUsuario, String nombre, String apellidos, int oficio_idOficio) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.oficio_idOficio = oficio_idOficio;
        this.image = null;
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

    public int getOficio_idOficio() {
        return oficio_idOficio;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage() {
        this.image = Oficio.getOficio(oficio_idOficio).getImageBitmap();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", oficio_idOficio=" + oficio_idOficio +
                ", image=" + image +
                '}';
    }
}