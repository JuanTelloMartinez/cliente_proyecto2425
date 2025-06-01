package es.ieslavereda.baseoficios.activities;

import static es.ieslavereda.baseoficios.base.Parameters.URL_IMAGE_BASE;
import static es.ieslavereda.baseoficios.base.Parameters.URL_IMG_OFICIO;

import android.os.Bundle;
// import android.content.Context;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.ieslavereda.baseoficios.API.CallMethods;
import es.ieslavereda.baseoficios.API.Connector;
import es.ieslavereda.baseoficios.R;
import es.ieslavereda.baseoficios.activities.model.Usuario;
import es.ieslavereda.baseoficios.base.BaseActivity;
import es.ieslavereda.baseoficios.base.CallInterface;
import es.ieslavereda.baseoficios.base.ImageDownloader;
import es.ieslavereda.baseoficios.base.UsuarioAdapter;

public class MainActivity extends BaseActivity implements CallInterface<List<Usuario>> {

    private List<Usuario> usuarios;
    private UsuarioAdapter usuarioAdapter;
    private RecyclerView recyclerViewUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewUsuarios = findViewById(R.id.recyclerView);
        usuarios = new ArrayList<>();

        usuarioAdapter = new UsuarioAdapter(usuarios, this);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUsuarios.setAdapter(usuarioAdapter);

        // Ejecutamos una llamada para obtener datos de la API
        executeCall(this);
    }

    // Realizamos la llamada en segundo plano y devolvemos el objeto obtenido
    @Override
    public List<Usuario> doInBackground() throws Exception {
        List<Usuario> usuariosObtenidos = Connector.getConector().getAsList(Usuario.class, "usuarios/");
        for (Usuario usuario : usuariosObtenidos) {
            usuario.setImage(ImageDownloader.getImage(URL_IMAGE_BASE +
                    CallMethods.getCallMethodsObject().get(URL_IMG_OFICIO + usuario.getOficio_idOficio())));
        }
        return usuariosObtenidos;
    }

    // Una vez finalizada la tarea en segundo plano, ejecutamos en primer plano (UI).
    // Recibimos por parametro lo obtenido en segundo plano.
    @Override
    public void doInUI(List<Usuario> usuariosObtenidos) {
        if (usuariosObtenidos != null && !usuariosObtenidos.isEmpty()) {
            usuarios.clear();
            usuarios.addAll(usuariosObtenidos);
            usuarioAdapter.notifyDataSetChanged();
        }
    }
        // ImageDownloader.downloadImage(URL_IMAGE_BASE + "albañil.png", findViewById(R.id.imageView));

//    Si queremos variar que hacer si se produce un error descomentar
//    @Override
//    public void doInError(Context context, Exception e) {
//
//    }

}