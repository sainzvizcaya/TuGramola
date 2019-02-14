package tablerocolores.cfic.edu.tugramola.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.dto.Persona;

public class HolderPersona extends RecyclerView.ViewHolder {

    private TextView caja_nombre_jugador;

    public HolderPersona(View itemView) {

        super(itemView);
        caja_nombre_jugador = (TextView) itemView.findViewById(R.id.idNombre);

    }


    public void cargarPuntuacion(Persona p) {
        caja_nombre_jugador.setText(p.getNombre());
    }

}