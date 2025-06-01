package es.ieslavereda.baseoficios.base;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import es.ieslavereda.baseoficios.R;
import es.ieslavereda.baseoficios.activities.model.Usuario;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private List<Usuario> listaUsuarios;
    private Context context;
    private LayoutInflater inflater;

    public UsuarioAdapter(List<Usuario> listaUsuarios, @NonNull Context context) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public UsuarioAdapter.UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapter.UsuarioViewHolder holder, int position) {

        Usuario UsuarioActual = listaUsuarios.get(position);

        holder.textViewNombre.setText(UsuarioActual.getNombre());
        holder.textViewOficio.setText(String.valueOf(UsuarioActual.getOficio_idOficio()));
        holder.imageViewPerfil.setImageBitmap(UsuarioActual.getImage());
    }

    @Override
    public int getItemCount() {
        if (listaUsuarios == null) {
            return 0;
        }
        return listaUsuarios.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewPerfil;
        public TextView textViewNombre;
        public TextView textViewOficio;
        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPerfil = itemView.findViewById(R.id.imagenPerfil);
            textViewNombre = itemView.findViewById(R.id.nombre);
            textViewOficio = itemView.findViewById(R.id.oficio);
        }
    }
}