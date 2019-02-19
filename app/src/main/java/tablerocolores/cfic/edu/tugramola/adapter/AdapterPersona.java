package tablerocolores.cfic.edu.tugramola.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.dto.PersonaFila;
import tablerocolores.cfic.edu.tugramola.holder.HolderPersona;


public class AdapterPersona extends RecyclerView.Adapter<HolderPersona> {

    private List<PersonaFila> datos;

    public AdapterPersona(List<PersonaFila> lista_participantes)
    {
        this.datos = lista_participantes;
    }

    @Override
    public HolderPersona onCreateViewHolder(ViewGroup parent, int viewType) {
        HolderPersona holderPersona = null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.fila_persona, parent, false);
        holderPersona = new HolderPersona(itemView);

        return holderPersona;
    }

    //Al holder, le meto la info de item que toca
    @Override
    public void onBindViewHolder(HolderPersona holder, int position) {
        PersonaFila persona = datos.get(position);
        holder.cargarPersona(persona);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public long getItemId(int position){
        return position;
    }

}
