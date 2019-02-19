package tablerocolores.cfic.edu.tugramola.util;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.fragments.BuscarFragment;

public class CriteriosBusqueda {
    private static final String URI_ITUNES="https://itunes.apple.com/search/?media=music&entity=";
    private static final String URI_ITUNES_TODAS="https://itunes.apple.com/search/?media=music&term=";
    private static final String URI_CANCION="song&attribute=songTerm&term=";
    private static final String URI_ARTISTA="musicArtist&attribute=artistTerm&term=";
    private static final String URI_ALBUM="album&attribute=albumTerm&term=";
    private String dato_parceado;
    //private Fragment_main context;

    public CriteriosBusqueda(BuscarFragment ma){
        EditText dato=ma.getView().findViewById(R.id.dato);
        try {
            dato_parceado=URLEncoder.encode(dato.getText().toString(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.d("LLLL","No se parceo los datos introducido por el user");
            e.printStackTrace();
        }
        //context=ma;
    }

    public String  todas(){
        return URI_ITUNES_TODAS+dato_parceado;
    }

    public String porCancion(){
        return URI_ITUNES+URI_CANCION+dato_parceado;
    }

    public String porArtista(){
        return URI_ITUNES+URI_ARTISTA+dato_parceado;
    }

    public String porAlbum(){
        return URI_ITUNES+URI_ALBUM+dato_parceado;
    }
}
