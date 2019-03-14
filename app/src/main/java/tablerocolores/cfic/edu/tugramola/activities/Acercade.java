package tablerocolores.cfic.edu.tugramola.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import tablerocolores.cfic.edu.tugramola.R;


public class Acercade extends AppCompatActivity {

    private Object tag;
    private static int t=0;
    private Acercade acercade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);

        //así dibujo la flecha de navegación estandar atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        t++;
        animacion();

    }

    public void animacion() {

        if(t > 4)
        {
            Intent mint = new Intent(this,AnimacionActivity.class);
            startActivity(mint);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Log.d("MIAPP", "Creditos#2.Atras");
                super.onBackPressed();
                break;
        }
        return true;
    }

}
