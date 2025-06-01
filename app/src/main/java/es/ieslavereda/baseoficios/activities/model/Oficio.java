package es.ieslavereda.baseoficios.activities.model;

import static es.ieslavereda.baseoficios.base.Parameters.URL_IMAGE_BASE;

import android.graphics.Bitmap;

import es.ieslavereda.baseoficios.base.ImageDownloader;

public class Oficio {
    private int idOficio;
    private String descripcion;
    private String image;

    public Oficio(int idOficio, String descripcion, String image) {
        this.idOficio = idOficio;
        this.descripcion = descripcion;
        this.image = image;
    }

    public int getIdOficio() {
        return idOficio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImage() {
        return image;
    }
}