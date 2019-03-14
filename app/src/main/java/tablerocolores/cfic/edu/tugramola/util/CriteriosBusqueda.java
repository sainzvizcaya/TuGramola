package tablerocolores.cfic.edu.tugramola.util;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.fragments.BuscarFragment;

public class


CriteriosBusqueda {
    private static final String URI_ITUNES="https://itunes.apple.com/search/?media=music&entity=";
    private static final String URI_ITUNES_TODAS="https://itunes.apple.com/search/?media=music&term=";
    private static final String URI_CANCION="song&attribute=songTerm&term=";
    private static final String URI_ARTISTA="musicArtist&attribute=artistTerm&term=";
    private static final String URI_ALBUM="album&attribute=albumTerm&term=";
    private String dato_parceado;

    // https://itunes.apple.com/search/?media=music&entity=song&attribute=songTerm&term="LA BAMBA"
    // https://itunes.apple.com/search/?media=music&entity=musicArtist&attribute=artistTerm&term="julio iglesias"
    // https://itunes.apple.com/search/?media=music&entity=album&attribute=albumTerm&term="bad"
    //https://itunes.apple.com/search/?media=music&term="cualquier cosa"
    public CriteriosBusqueda(){}

    public String parsear(String texto)
    {
        try {
            dato_parceado=URLEncoder.encode(texto,"utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.d("LLLL","No se parceo los datos introducido por el user");
            e.printStackTrace();
        }

        return dato_parceado;
    }

    public String  todas(String dato){

        String resultado = parsear(dato);
        return URI_ITUNES_TODAS+resultado;
    }

    public String porCancion(String dato){
        String resultado = parsear(dato);
        return URI_ITUNES+URI_CANCION+resultado;
    }

    public String porArtista(String dato)
    {
        String resultado = parsear(dato);
        return URI_ITUNES+URI_ARTISTA+resultado;
    }

    public String porAlbum(String dato)
    {
        String resultado = parsear(dato);
        return URI_ITUNES+URI_ALBUM+resultado;
    }
}
