package tablerocolores.cfic.edu.tugramola.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import tablerocolores.cfic.edu.tugramola.R;


public class Acercade extends AppCompatActivity {

    private Object tag;
    private int t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
        t=0;

    }

    public void animacion(View view) {

        tag = view.getTag();
        if (tag == null) {
            this.t++;
        }

        if(t == 5)
        {
            Intent mint = new Intent(this,AnimacionActivity.class);
            startActivity(mint);
        }
    }
}
