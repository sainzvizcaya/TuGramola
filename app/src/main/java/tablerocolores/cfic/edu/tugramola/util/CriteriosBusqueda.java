package tablerocolores.cfic.edu.tugramola.util;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PublicKey;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.activities.MainActivity;
import tablerocolores.cfic.edu.tugramola.fragments.Busqueda;
import tablerocolores.cfic.edu.tugramola.miscelaneo.DescargaCanciones;

public class CriteriosBusqueda {
    private static final String URI_ITUNES="https://itunes.apple.com/search/?media=music&entity=";
    private static final String URI_ITUNES_TODAS="https://itunes.apple.com/search/?media=music&term=";
    private static final String URI_CANCION="song&attribute=songTerm&term=";
    private static final String URI_ARTISTA="musicArtist&attribute=artistTerm&term=";
    private static final String URI_ALBUM="album&attribute=albumTerm&term=";
    private String dato_parceado;
    private Context context;

    public CriteriosBusqueda(Busqueda ma){
        EditText dato=ma.findViewById(R.id.dato);
        try {
            dato_parceado=URLEncoder.encode(dato.getText().toString(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.d("LLLL","No se parceo los datos introducido por el user");
            e.printStackTrace();
        }
        context=ma;
    }

    public void todas(){
        new DescargaCanciones(context).execute(URI_ITUNES_TODAS+dato_parceado);
    }

    public void porCancion(){
        new DescargaCanciones(context).execute(URI_ITUNES+URI_CANCION+dato_parceado);
    }

    public void porArtista(){
        new DescargaCanciones(context).execute(URI_ITUNES+URI_ARTISTA+dato_parceado);
    }

    public void porAlbum(){
        new DescargaCanciones(context).execute(URI_ITUNES+URI_ALBUM+dato_parceado);
    }
}
