package tablerocolores.cfic.edu.tugramola.miscelaneo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import tablerocolores.cfic.edu.tugramola.fragments.Busqueda;

public class DescargaCanciones extends AsyncTask<String,Void,ResultadoCanciones > {
    //https://itunes.apple.com/search/?media=music&term=chiquetete

    //private static final String URI_ITUNES="https://itunes.apple.com/search/?media=music&term=";
    private Context c;

    public DescargaCanciones(Context c) {
        this.c = c;
    }

    //En este método hacemos la conexión y recuperamos los datos
    @Override
    protected ResultadoCanciones doInBackground(String[] canciones) {
        ResultadoCanciones rc=null;
        URL url=null;
        HttpURLConnection huc=null;
        InputStreamReader is=null;
        Gson g=null;

        try{
            url=new URL(canciones[0]);
            Log.d("LLLL","URLXXXXXXXXX: "+canciones[0]);

            huc= (HttpURLConnection) url.openConnection();
            if(huc.getResponseCode()==HttpURLConnection.HTTP_OK){
                //repuesta exitosa

                String ct=huc.getContentType(); //Devuelve el tipo MIME
                Log.e("LLLL","TIPO MIME Rx: "+ct);

                is=new InputStreamReader(huc.getInputStream());//accedo al cuerpo del mensaje Http
                g=new Gson();
                rc=g.fromJson(is,ResultadoCanciones.class);
            }

        }catch (Exception e){
            Log.e("LLLL","Error no ha ido bien el tema");

        }finally {
            if(is!=null) {
                try {
                    is.close();
                    Log.e("LLLL","InputStreamReader cerrado");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(huc!=null){
                huc.disconnect();
                Log.e("LLLL","HttpURLConnection cerrado");
            }
        }
        return rc;
    }

    //Este método será invocado cuando acabe doInBackground
    @Override
    protected void onPostExecute(ResultadoCanciones rc) {
        super.onPostExecute(rc);
        Gson g=new GsonBuilder().setPrettyPrinting().create();
        String canciones=g.toJson(rc);
        //Log.d("LLLL","CANCIONES: "+canciones);
        if(c instanceof Busqueda) {
            Busqueda ma = (Busqueda) c;
            ma.mostrarResultados(rc);
        }
    }
}
