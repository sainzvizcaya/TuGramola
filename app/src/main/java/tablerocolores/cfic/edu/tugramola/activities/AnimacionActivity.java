package tablerocolores.cfic.edu.tugramola.activities;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.media.MediaPlayer;

import java.io.File;
import java.util.Random;

import tablerocolores.cfic.edu.tugramola.R;
import static android.os.Build.ID;

public class AnimacionActivity extends AppCompatActivity implements Animation.AnimationListener {
    public MediaPlayer player;
    private int activity_animacion;
    private int layoutResID;
    private int color1;
    private int color2;
    private int color3;

    private void ocultarStatusBar ()
    {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_animacion);
            ocultarStatusBar();

            View v = findViewById(R.id.marco_anim);
            FrameLayout f1 = findViewById(R.id.marco_anim1);
            FrameLayout f2 = findViewById(R.id.marco_anim2);
//            FrameLayout f3 = findViewById(R.id.marco_anim3);

            final Integer[] imagenes = { R.drawable.clasica, R.drawable.coro, R.drawable.danza,
                    R.drawable.disco, R.drawable.electronica,R.drawable.flamenco,R.drawable.folk,
                    R.drawable.jazz, R.drawable.joandylan, R.drawable.opera, R.drawable.pop,
                    R.drawable.punk, R.drawable.ranchera, R.drawable.randr
            };
            final Integer[] imagenes2 = { R.drawable.tiburon11, R.drawable.tiburon21
            };
            final Integer[] imagenes3 = { R.drawable.tiburon22, R.drawable.tiburon21
            };
            final Integer[] colores = { R.color.colorAma, R.color.colorAmaCla,
                    R.color.colorAmaOsc, R.color.colorAzu, R.color.colorAzuCla, R.color.colorAzuOsc,
                    R.color.colorGri, R.color.colorGriCla, R.color.colorGriOsc, R.color.colorMar,
                    R.color.colorMarCla, R.color.colorMarOsc, R.color.colorRoj, R.color.colorRojCla,
                    R.color.colorVer, R.color.colorVerCla, R.color.colorVerOsc
            };
            final Integer[] sonidos = { R.raw.bombazo,
                    R.raw.pocoapoco, R.raw.sleep_away, R.raw.heyjude
            };
            final Integer[] animaciones = { R.anim.animacion_view1, R.anim.animacion_view2,
                    R.anim.blink, R.anim.entry_animation, R.anim.exit_animation, R.anim.fade_in,
                    R.anim.fade_out, R.anim.move, R.anim.rotate,
                    R.anim.slide_down, R.anim.slide_left, R.anim.slide_right, R.anim.slide_up,
                    R.anim.zoom_in, R.anim.zoom_out, R.anim.bounce, R.anim.bound
            };
            Random rI1 = new Random();
            int nI1 = rI1.nextInt(imagenes.length);
            Random rI2 = new Random();
            int nI2 = rI2.nextInt(imagenes2.length);
            Random rC1 = new Random();
            int nC1 = rC1.nextInt(colores.length);
            Random rC2 = new Random();
            int nC2 = rC2.nextInt(colores.length);
            Random rC3 = new Random();
            int nC3 = rC3.nextInt(colores.length);
            Random rS = new Random();
            int nS = rS.nextInt(sonidos.length);
            Random rA = new Random();
            int nA = rA.nextInt(animaciones.length);
            // nA = 16;
            // nS = 1;
            if(nA == 2 || nA == 3 || nA == 4) {nS = 1;}
            if(nA == 13 || nA == 14) {nS = 0;}
            if(nI1 == 2|| nI1 == 0) {nS = 2;}
            player = MediaPlayer.create(this, sonidos[nS]); //select music file
            //            player.setLooping(true); //set looping
            player.start();

            final ImageView iv1 = (ImageView) findViewById(R.id.d1);
            iv1.setImageResource(imagenes[nI1]);

            this.color1 = ResourcesCompat.getColor(getResources(), colores[nC1], null);//obtengo el color
            iv1.setBackgroundColor(this.color1);
            final ImageView iv2 = (ImageView) findViewById(R.id.d2);
            iv2.setImageResource(imagenes2[nI2]);
            this.color2 = ResourcesCompat.getColor(getResources(), colores[nC2], null);//obtengo el color
            iv2.setBackgroundColor(this.color2);
            this.color3 = ResourcesCompat.getColor(getResources(), colores[nC3], null);//obtengo el color
            v.setBackgroundColor(this.color3);

            ImageView i2 = (ImageView)findViewById(R.id.d2);
            i2.setVisibility(View.INVISIBLE);

            Animation a = AnimationUtils.loadAnimation(this, animaciones[nA]);
            a.setAnimationListener(this);
            v.startAnimation(a);
        } catch (Exception e) {
            Log.e("ERROR", "Error en onCreate de AnimacionActivity: " + e );

        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.d("MENSAJE", "onAnimationEnd");
        try {

            ImageView i1 = (ImageView)findViewById(R.id.d1);
            i1.setVisibility(View.INVISIBLE);
            i1.setVisibility(FrameLayout.INVISIBLE);
            ImageView i2 = (ImageView)findViewById(R.id.d2);
            i2.setVisibility(View.VISIBLE);
            i2.setVisibility(FrameLayout.VISIBLE);

            player = MediaPlayer.create(this, R.raw.holaholahola); //select music file
//            player.setLooping(true); //set looping
            player.start();
            Random nR = new Random();
            int n = nR.nextInt(5);
            // n = 4;
            switch (n)
            {
                case 0:
                    i2.animate().scaleX(2.50f).scaleY(2.5f).rotationXBy(90.0f).setDuration(2000);
                    break;
                case 1:
                    i2.animate().scaleX(2.50f).scaleY(2.5f).rotationXBy(-90.0f).setDuration(2000);
                    break;
                case 2:
                    i2.animate().scaleX(2.50f).scaleY(2.5f).rotationYBy(90.0f).setDuration(2000);
                    break;
                case 3:
                    i2.animate().scaleX(2.50f).scaleY(2.5f).rotationYBy(-90.0f).setDuration(2000);
                    break;
                default:
                    i2.animate().scaleX(2.50f).scaleY(2.5f).setDuration(2000);
                    break;
            }
            //finish();
        } catch (Exception e) {
            Log.e("ERROR", "exception en onAnimationEmd AnimacionActivity: " + e);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Log.d("MENSAJE", "onAnimationStart");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.d("MENSAJE", "onAnimationRepeat");

    }
}
