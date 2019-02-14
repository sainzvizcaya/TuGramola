package tablerocolores.cfic.edu.tugramola.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import tablerocolores.cfic.edu.tugramola.dto.Persona;


public class BaseDatos extends SQLiteOpenHelper {


    private final String sqlCreacionTablaPersonas = "CREATE TABLE PERSONAS (id INTEGER PRIMARY KEY, nombre TEXT, foto TEXT, git INTEGER, lin INTEGER)";

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
        database.execSQL("INSERT INTO PERSONAS (id, nombre, foto, git, lin) VALUES ("+ persona.getId()+" , '"+
                                                                                      persona.getNombre()+"', '"+
                                                                                      persona.getFoto()+"', '"+
                                                                                      persona.getGit()+"', '"+
                                                                                      persona.getLin()+"')");
        this.cerrarBaseDatos(database);

    }

/*
    public Persona selectPersona (String nombre)
    {
        Persona persona = null;
        int aux_id = -1;
        String nombre_aux = null;

        String consulta = "SELECT id, nombre FROM PERSONA WHERE nombre LIKE %"+nombre+"%;";


        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);


        if( cursor != null || cursor.getCount() <=0)
        {
            cursor.moveToFirst();

            aux_id = cursor.getInt(0); //la posicion primera, el id
            nombre_aux = cursor.getString(1); //la posicion segunda, el id
            persona = new Persona(aux_id, nombre_aux);

            cursor.close();
        }

        this.cerrarBaseDatos(basedatos);

        return persona;
    }
*/



    public List<Persona> cargarPersonas ()
    {
        List<Persona> lista = null;
        Persona persona = null;
        int aux_id = -1;
        String aux_nombre = null;


        String consulta = "SELECT id, nombre FROM PERSONAS";

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
                persona = new Persona(aux_id, aux_nombre,"","","");
                lista.add(persona);

            }while (cursor.moveToNext());

            cursor.close();
        }

        this.cerrarBaseDatos(basedatos);
        return lista;
    }
}
