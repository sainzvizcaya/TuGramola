package tablerocolores.cfic.edu.tugramola.holder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.activities.DetalleCancionActivity;
import tablerocolores.cfic.edu.tugramola.activities.MainActivity;
import tablerocolores.cfic.edu.tugramola.dao.BaseDatosCanciones;
import tablerocolores.cfic.edu.tugramola.dto.Cancion;
import tablerocolores.cfic.edu.tugramola.fragments.Fragment_favoritos;


public class FavoritosHolder extends RecyclerView.ViewHolder
{
    private TextView id, artista, cancion;
    private ImageView imageView,imagenMedia,imagenPlaying,imagenFavorito;
    private Context contexto;
    private String cancionURL;
    private boolean playingThis,play;
    private Cancion cancionObj;
    private MediaPlayer mp;
    Context context ;
    Fragment_favoritos fav;
    MainActivity mainActivity;


    //CONSTRUCTOR


    public FavoritosHolder(final View itemView, final Context contexto) {
        super(itemView);
        this.id = itemView.findViewById(R.id.id);
        this.artista = itemView.findViewById(R.id.artista);
        this.cancion = itemView.findViewById(R.id.cancion);
        this.imageView = itemView.findViewById(R.id.imageView);
        this.imagenMedia = itemView.findViewById(R.id.imagenMedia);
        this.imagenFavorito = itemView.findViewById(R.id.imagenFavorito);
        this.contexto=contexto;
        this.cancionURL="";
        this.play=false;
        this.playingThis=false;
        this.context = contexto;

        // ocultamos la estrella de favoritos
        this.imagenFavorito.setVisibility(View.INVISIBLE);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //irDetalle();
            }});

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                Log.d("AMS","HE PULSADO EL ITEM: " + id.getText());
                // ahora borramos el registro de la bbdd preguntando primero
                AlertDialog.Builder dialogo2 = new AlertDialog.Builder(context);
                dialogo2.setTitle("A T E N C I O N");
                dialogo2.setMessage("¿ Realmente desea eliminar la cancion de los favoritos ?");
                dialogo2.setCancelable(false);
                dialogo2.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // conectamos con la bbdd y eliminamos el registro solicitado
                        BaseDatosCanciones bd = fav.getBaseDatosCanciones();
                        bd.eliminarCancion(id.getText().toString());
                        // refrescamos la ventana
                        ((MainActivity)context).favoritos(v);
                    }
                });
                dialogo2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // no hacemos nada en este caso
                    }
                });
                dialogo2.show();

                return false;
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irDetalle();
            }});

        imagenMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(contexto,"En el PLAY",Toast.LENGTH_SHORT).show();

                   mp= Fragment_favoritos.getReproductor();
                   imagenPlaying= Fragment_favoritos.getPlayButton();
                Log.d("MIAPP","Entra en la reproduccion");
                   play=playingThis;


                   if (Fragment_favoritos.isPlay())
                    {   pararCancion(v);
                        if (!play)
                            {//Pulso play en cancion nueva
                                reproducirCancion(v);
                            }
                    }
                   else {
                        reproducirCancion(v);
                    }

                }
        });
    }
private void pararCancion(View v)
{   //Esta sonando y hay que pararlo
    //Toast.makeText(contexto,"NO",Toast.LENGTH_SHORT).show();
   try {
       mp.stop();
       mp.reset();
       playingThis = false;
       Fragment_favoritos.setPlay(false);

       if (imagenPlaying != null)
           Picasso.with(contexto).load(android.R.drawable.ic_media_play).into(imagenPlaying);

       //Picasso.with(contexto).load(android.R.drawable.ic_media_play).into((ImageView) v);
   }catch (Exception e)
        {
            Log.e("Error" , "EXCEPCION al reproducir", e);
        }
}
private void reproducirCancion (View v)
{
    //No esta reproduciendo -->play
    //Toast.makeText(contexto,"SI",Toast.LENGTH_SHORT).show();

   try {
       mp.setDataSource(cancionURL);

       mp.prepare();
       mp.setVolume(100, 100);
       playingThis = true;
       Fragment_favoritos.setPlay(true);
       mp.start();

       Fragment_favoritos.setPlayButton((ImageView) v);
       Picasso.with(contexto).load(android.R.drawable.ic_media_pause).into((ImageView) v);
   }
   catch (IllegalStateException e)
   {
       Log.e("Error" , "ILLEGAL al reproducir", e);
   }
   catch (Exception e)
   {
       Log.e("Error" , "EXCEPCION al reproducir", e);
   }
}

//ACTUALIZAR HOLDER
public void actualizarHolder (FavoritosHolder favoritosHolder, Cancion c)
    {   //TODO ACTUALIZAR TODOS LOS CAMPOS
        String imagenTxt=c.getArtworkUrl100();
        favoritosHolder.id.setText(c.getTrackId());
        favoritosHolder.artista.setText(c.getArtistName());
        favoritosHolder.cancion.setText(c.getTrackName());
        favoritosHolder.cancionURL=c.getPreviewUrl();
        favoritosHolder.cancionObj=c;
        Picasso.with(contexto).load(imagenTxt).into(imageView);

    }
    private void irDetalle()
    {   //Toast.makeText(contexto,"Ir DETALLE",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(contexto, DetalleCancionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cancion",cancionObj);
        intent.putExtras(bundle);
        contexto.startActivity(intent);

    }

}//FIN CLASE CANCIONHOLDER
