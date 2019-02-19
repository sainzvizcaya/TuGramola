package tablerocolores.cfic.edu.tugramola.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import tablerocolores.cfic.edu.tugramola.dto.Persona;


public class BaseDatos extends SQLiteOpenHelper {


    private final String sqlCreacionTablaPersonas = "CREATE TABLE PERSONAS (id INTEGER PRIMARY KEY, nombre TEXT, foto TEXT, lin INTEGER, git INTEGER)";

    public BaseDatos(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version); //el método padre, llamará a Oncreate o OnUpgrade, segn corresponda
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sqlCreacionTablaPersonas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //En caso de que al constructor le pasemos un número de versión distinto a
        // la base de datos existente, este métdo es invocado. Esto sería necesario
        //cuando modificamos la estrucutura de la base de datos

        //Aquí, deberíamos
        // 1 - Extraer los datos de la vieja versión y copiarlos a la nueva instancia
        // 2 - Crear la nueva versión
        // 3 - Cargar los datos en las tablas de la nueva versión
    }


    private void cerrarBaseDatos (SQLiteDatabase database)
    {
        database.close();
    }

    public void insertPersona (Persona persona)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        //database.execSQL("INSERT INTO PERSONA (id, nombre, foto, git, lin) VALUES ("+ persona.getId()+" , '"+ persona.getNombre()+"')");

        Log.d("MIAPP", "INSERT: "+persona.getId()+" - "+persona.getNombre()+" - "+persona.getFoto()+" - "+persona.getLin()+" - "+persona.getGit());


        database.execSQL("INSERT INTO PERSONAS (id, nombre, foto, git, lin) VALUES ("+ persona.getId()+" , '"+
                                                                                      persona.getNombre()+"', '"+
                                                                                      persona.getFoto()+"', '"+
                                                                                      persona.getLin()+"', '"+
                                                                                      persona.getGit()+"')") ;
        this.cerrarBaseDatos(database);

    }


    public boolean existe ()
    {
        Cursor cursor=null;
        Log.d("MIAPP","BaseDatos#1");
        Persona persona = null;
        int aux_id = -1;
        String nombre_aux = null;

        Log.d("MIAPP","BaseDatos#2");
        //String consulta = "SELECT id, nombre FROM PERSONA WHERE nombre LIKE %"+nombre+"%;";
        String consulta = "SELECT id FROM PERSONAS WHERE id=1;";

        Log.d("MIAPP","BaseDatos#3");
        SQLiteDatabase basedatos = this.getReadableDatabase();

        Log.d("MIAPP","BaseDatos#4");
        //Cursor cursor = basedatos.rawQuery(consulta, null);
        cursor = basedatos.rawQuery(consulta,null);

        //if( cursor != null || cursor.getCount() >0)
        if( cursor.getCount() >0)
        {   Log.d("MIAPP","BaseDatos#5");
            cursor.moveToFirst();
            aux_id = cursor.getInt(0); //la posicion primera, el id

            cursor.close();
        }

        this.cerrarBaseDatos(basedatos);

        if (aux_id==-1){
            return false;
        } else{
            return true;
        }
    }


    public List<Persona> cargarPersonas ()
    {
        List<Persona> lista = null;
        Persona persona = null;
        int aux_id = -1;
        String aux_nombre = null;
        String aux_foto  = null;
        String aux_git = null;
        String aux_lin = null;


        String consulta = "SELECT id, nombre, foto, git, lin FROM PERSONAS";
        //order by cast(nombre as REAL) ASC

        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);


        if( cursor != null || cursor.getCount() <=0)
        {
            cursor.moveToFirst();
            lista = new ArrayList<Persona>(cursor.getCount());
            do
            {
                aux_id = cursor.getInt(0);          //la posicion primera, el id
                aux_nombre = cursor.getString(1);   //la posicion segunda, el nombre
                aux_foto = cursor.getString(2);
                aux_lin = cursor.getString(3);
                aux_git = cursor.getString(4);

                persona = new Persona(aux_id, aux_nombre, aux_foto, aux_lin, aux_git);
                lista.add(persona);

            }while (cursor.moveToNext());

            cursor.close();
        }

        this.cerrarBaseDatos(basedatos);
        return lista;
    }
}
