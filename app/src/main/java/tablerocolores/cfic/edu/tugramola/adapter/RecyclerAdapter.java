package tablerocolores.cfic.edu.tugramola.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.dto.Cancion;
import tablerocolores.cfic.edu.tugramola.holder.CancionHolder;

public class RecyclerAdapter extends android.support.v7.widget.RecyclerView.Adapter<android.support.v7.widget.RecyclerView.ViewHolder> {
    private List<Cancion> arrayCanciones;
    private LayoutInflater mInflater;
    private Context contexto;
    private View view;

    //Constructor
    public RecyclerAdapter(Context context, List<Cancion> cancionList) {
        this.mInflater = LayoutInflater.from(context);
        this.contexto = context;
        this.arrayCanciones = cancionList;
    }

    @Override
    public CancionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new CancionHolder(view, contexto);
        //return new CancionHolder(view, contexto);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Cancion cancion = arrayCanciones.get(i);
        CancionHolder ch = (CancionHolder) viewHolder;
        ch.actualizarHolder(ch, cancion);
    }
    @Override
    public int getItemCount() {
        return arrayCanciones.size();
    }
}
