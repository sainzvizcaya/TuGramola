package tablerocolores.cfic.edu.tugramola.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SplashPreferences {

    private final String nombreFichPref = "fichsplashpreferences"; //nombre de ficheros siempre en minusculas
    private SharedPreferences preferences;

    public SplashPreferences(Context c) {

        //usamos el mismo fichero para las 2 preferences (tiempo record y nombre de usuario). Los métodos hay que duplicarlos por el tipo de datos (int, String)
        preferences = c.getSharedPreferences(nombreFichPref, MODE_PRIVATE);

    }

    public boolean dameCheck(String clave ) { //Va a funcionar igual para cualquier objeto, lo pongo static
        boolean valorDefecto=false;
        return(preferences.getBoolean(clave, valorDefecto));
    }

    public void ponCheck(String clave, boolean valor) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(clave, valor);
        editor.commit();
    }

}