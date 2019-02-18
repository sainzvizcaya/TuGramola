package tablerocolores.cfic.edu.tugramola.activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.util.SplashPreferences;

public class Ajustes extends AppCompatActivity {


    private SplashPreferences splashPreferences;
    private static final String clave = "CHECKMARCADO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        splashPreferences = new SplashPreferences(this);

        //TODO Comprobar el valor que tiene el switch para pasarselo al boton
        Log.d("MIAPP","Cogemos el valor de preferences");

        boolean checkmarcado = splashPreferences.dameCheck();

        final Switch sw_animacion = (Switch) findViewById(R.id.animacion);

        sw_animacion.setChecked(checkmarcado);
        sw_animacion.setChecked(true);

        sw_animacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw_animacion.isChecked())
                {
                    //TODO Cambiar en preferences el valor
                    Log.d("MIAPP","Checked");
                    splashPreferences.ponCheck(true);

                }else
                {
                    //TODO no checked
                    Log.d("MIAPP","NoChecked");
                    splashPreferences.ponCheck(false);
                }
            }
        });
    }
}
