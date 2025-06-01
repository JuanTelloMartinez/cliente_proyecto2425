package es.ieslavereda.baseoficios.activities.model;

import static es.ieslavereda.baseoficios.base.Parameters.URL_IMAGE_BASE;
import static es.ieslavereda.baseoficios.base.Parameters.URL_IMG_OFICIO;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import es.ieslavereda.baseoficios.API.CallMethods;
import es.ieslavereda.baseoficios.API.Connector;
import es.ieslavereda.baseoficios.base.ImageDownloader;

public final class Oficio {
    private static final Map<Integer, Oficio> LISTA_OFICIOS = new HashMap<>();
    private static final String TAG = "OficioDinamico";
    private static boolean isLoaded = false;
    private final int idOficio;
    private final String descripcion;
    private final String image;
    private final Bitmap imageBitmap;
    private Oficio(int idOficio, String descripcion, String image) {
        this.idOficio = idOficio;
        this.descripcion = descripcion;
        this.image = image;
        this.imageBitmap = ImageDownloader.getImage(URL_IMAGE_BASE +
                CallMethods.getCallMethodsObject().get(URL_IMG_OFICIO + idOficio));
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

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public static Oficio getOficio(int idOficioBuscado) {
        return LISTA_OFICIOS.get(idOficioBuscado);
    }

    public static void cargarOficiosDesdeDataSource() throws Exception {
        LISTA_OFICIOS.clear();

        List<Oficio> oficiosCargados = Connector.getConector().getAsList(Oficio.class, "/oficios/");
        for (Oficio oficio : oficiosCargados) {
            registrarOficioInterno(oficio.getIdOficio(), oficio.getDescripcion(), oficio.getImage());
        }

    }
    private static void registrarOficioInterno(int id, String descripcion, String nombreImagen) {
        if (LISTA_OFICIOS.containsKey(id))
            return;
        Oficio nuevoOficio = new Oficio(id, descripcion, nombreImagen);
        LISTA_OFICIOS.put(id, nuevoOficio);
    }

    // Es importante sobrescribir equals() y hashCode() si vas a usar objetos Oficio
    // en colecciones como Sets o como claves en Maps, o si necesitas comparar instancias.
    // Aquí, dos objetos Oficio son iguales si sus idOficio son iguales.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oficio oficio = (Oficio) o;
        return idOficio == oficio.idOficio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOficio);
    }

    // Opcional: toString() para facilitar la depuración o si se usa en Spinners.
    @Override
    public String toString() {
        return descripcion + " (ID: " + idOficio + ", Img: " + image + ")";
    }

}