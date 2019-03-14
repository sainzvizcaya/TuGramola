package tablerocolores.cfic.edu.tugramola.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import tablerocolores.cfic.edu.tugramola.dto.Cancion;

public class BaseDatosCanciones extends SQLiteOpenHelper {

    private final String sql = "CREATE TABLE FAVORITOS ( ID_ARTISTA INTEGER, ARTISTNAME TEXT,TRACKNAME TEXT,TRACKID TEXT,ARTWORKURL100 TEXT, PREVIEWURL TEXT,COLLECTIONNAME TEXT)";

    public BaseDatosCanciones(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version); //el método padre, llamará a Oncreate o OnUpgrade, segn corresponda)
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creamos la tabla de la bbdd
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // cerramos la bbdd
    private void cerrarBaseDatos(SQLiteDatabase db){
        db.close();
    }

    // cogemos el objeto cancion pasado como parametro y lo insertamos en la tabla
    public void insertarCancion(Cancion cancion)
    {
        // buscamos que no este ya el registro en la tabla
        boolean encontrado = false;

        String trackId = cancion.getTrackId();
        encontrado = buscarCancion(trackId);
        // si no lo hemos encontrado insertamos , en caso contrario no hacemos nada ya que ya estaria en la tabla de favoritos ( y se supone que los datos no han variado)
        if (encontrado == false) {
            SQLiteDatabase db = this.getWritableDatabase();
            String nombre_cancion = "";
            String nombre_artista= "";
            String nombre_disco = "";
            try {
                // sustituimos los caracteres extraños de los nombres de cancion y de artista para que no nos de error al insertar
                nombre_cancion = URLEncoder.encode(cancion.getTrackName(),"UTF-8");
                nombre_artista = URLEncoder.encode(cancion.getArtistName(),"UTF-8");
                nombre_disco = URLEncoder.encode(cancion.getCollectionName(),"UTF-8");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            db.execSQL("INSERT INTO FAVORITOS (ID_ARTISTA, ARTISTNAME,TRACKNAME,TRACKID,ARTWORKURL100,PREVIEWURL, COLLECTIONNAME) " +
                    "VALUES (" + cancion.getArtistId() + ", '" + nombre_artista + "' , '" + nombre_cancion + "', '" + cancion.getTrackId() + "','" + cancion.getArtworkUrl100() + "', '"
                    + cancion.getPreviewUrl() + "', '" + nombre_disco + "')");
            this.cerrarBaseDatos(db);
            Log.d("MIAPP","se ha insertado el registro con clave " + cancion.getArtistId());
        }
        else {
            Log.d("MIAPP", "el registro ya estaba en la tabla");
        }
    }

    // eliminamos la cancion solicitada
    public void eliminarCancion(String id_trackID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM FAVORITOS WHERE TRACKID = " + id_trackID);
        this.cerrarBaseDatos(db);
        Log.d("MIAPP","registro eliminado clave: " + id_trackID);
    }

    // borramos todos los datos de la tabla
    public  void eliminarTodos()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM FAVORITOS");
        this.cerrarBaseDatos(db);
        Log.d("MIAPP","tabla borrada");
    }

    // buscamos la cancion solicitada, si esta en favoritos devuelve true
    public boolean buscarCancion(String id_trackID)
    {
        String sql = "SELECT * FROM FAVORITOS WHERE TRACKID = " + id_trackID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        boolean resultado = false;

        if( cursor != null && cursor.getCount() >0)
        {
            resultado = true;
            Log.d("MIAPP","registro encontrado");
        }
        else
        {
            resultado = false;
            Log.d("MIAPP","registro no encontrado");
        }
        cursor.close();
        this.cerrarBaseDatos(db);
        return resultado;

    }

    // devolvemos una lista de objetos cancion con todos los registros de la tabla
    public List<Cancion> cargarFavoritos()
    {
        List<Cancion> lista = null;
        String sql = "SELECT * FROM FAVORITOS";
        String artistName;
        String trackName;
        String trackId;
        int artistId;
        String artworkUrl100;
        String previewUrl;
        String collectionName;
        Cancion cancion_aux = null;
        String nombre_cancion;
        String nombre_artista;
        String nombre_disco;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        nombre_artista = "";
        nombre_cancion = "";
        nombre_disco = "";
        if (cursor != null && cursor.getCount() > 0)
        {
            lista = new ArrayList<Cancion>(cursor.getCount());
            cursor.moveToFirst();

            do {

                artistId = cursor.getInt(0);
                artistName = cursor.getString(1);
                trackName = cursor.getString(2);
                trackId = cursor.getString(3);
                artworkUrl100 = cursor.getString(4);
                previewUrl = cursor.getString(5);
                collectionName = cursor.getString(6);

                // ponemos otra vez los caracteres extraños al devolver el registro de la tabla
                try {
                    nombre_artista = URLDecoder.decode(artistName,"UTF-8");
                    nombre_cancion = URLDecoder.decode(trackName,"UTF-8");
                    nombre_disco = URLDecoder.decode(collectionName,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                cancion_aux = new Cancion(nombre_artista,nombre_cancion,artistId,artworkUrl100,previewUrl,trackId,nombre_disco);

                lista.add(cancion_aux);



            } while (cursor.moveToNext());


        }
        cursor.close();
        cerrarBaseDatos(db);
        return lista;

    }
}
