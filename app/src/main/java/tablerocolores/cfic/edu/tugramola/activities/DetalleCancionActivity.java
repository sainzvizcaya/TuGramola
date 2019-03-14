package tablerocolores.cfic.edu.tugramola.activities;

import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.dto.Cancion;

public class DetalleCancionActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = new MediaPlayer();
    private String url = null;
    private static Cancion cancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cancion);

        //así dibujo la flecha de navegación estandar atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cancion = null;
        //Cancion cancion = getCancionPrueba();

        Bundle loadInfo = getIntent().getExtras();
        cancion = (Cancion) loadInfo.getSerializable("cancion");

        //Imagen (foto)
        ImageView imageView = (ImageView)findViewById(R.id.imageView1);
        Bitmap  bitmap = null;
        try {
            Log.d("MIAPP", "Entro por TRY Image");
            String imageUri = cancion.getArtworkUrl100();
            Log.d("MIAPP", "URI: " + imageUri);
            Picasso p = Picasso.with(this);
                    //Picasso.get();
            p.load(imageUri).resize(200,200).into(imageView);
        } catch (Exception e) {
            Log.d("MIAPP", "IOException: " + e);
            e.printStackTrace();
        }

        //Cajas de texto
        TextView textView = findViewById(R.id.tv_artista);
        textView = findViewById(R.id.tv_artista);
        textView.setText(cancion.getArtistName());

        textView = findViewById(R.id.tv_cancion);
        textView.setText(cancion.getTrackName());

        textView = findViewById(R.id.tv_album);
        textView.setText(cancion.getCollectionName());

        //Iconos
        ImageView imageView2 = (ImageView)findViewById(R.id.btnStopMediaPlayer);
        imageView2.setImageResource(R.drawable.ic_stop_circle);
        ImageView imageView3 = (ImageView)findViewById(R.id.btnStartMediaPlayer);
        imageView3.setImageResource(R.drawable.ic_play_circle);
    }

    public void stopMediaPlayer(View view) {
        Log.d("MIAPP", "stopMediaPlayer");
        pararMediaPlayer();
    }

    public void startMediaPlayer(View view) {
        Log.d("MIAPP", "startMediaPlayer");
        pararMediaPlayer(); //se llama a parar por si acaso estaba reproduciendo y vuelven a dar al play


        String url = cancion.getPreviewUrl(); //recojo la URL
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            Log.d("MIAPP", "Entro por TRY Sound");
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.d("MIAPP", "IOException: " + e);
            e.printStackTrace();
        }

    }

    public void pararMediaPlayer() {
        Log.d("MIAPP", "pauseMediaPlayer");
        if(mediaPlayer.isPlaying()){
            Log.d("MIAPP", "mediaPlayer isPlaying, lo Pausamos");
            mediaPlayer.pause();
        }
        mediaPlayer.reset();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) mediaPlayer.reset();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Log.d("MIAPP", "DetalleCancion.atras");
                super.onBackPressed();
                break;
        }
        return true;
    }

}
