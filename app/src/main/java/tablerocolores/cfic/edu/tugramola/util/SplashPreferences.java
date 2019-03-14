package tablerocolores.cfic.edu.tugramola.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SplashPreferences {

    private final String nombreFichPref = "fichsplashpreferences"; //nombre de ficheros siempre en minusculas
    private static final String clave = "CHECKMARCADO";
    private SharedPreferences preferences;

    public SplashPreferences(Context c) {


        preferences = c.getSharedPreferences(nombreFichPref, MODE_PRIVATE);

    }

    public boolean dameCheck( ) {
        boolean valorDefecto=false;
        return(preferences.getBoolean(clave, valorDefecto));
    }

    public void ponCheck(boolean valor) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(clave, valor);
        editor.commit();
    }

}