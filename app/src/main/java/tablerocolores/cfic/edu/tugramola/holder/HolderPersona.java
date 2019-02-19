package tablerocolores.cfic.edu.tugramola.holder;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tablerocolores.cfic.edu.tugramola.BuildConfig;
import tablerocolores.cfic.edu.tugramola.R;
import tablerocolores.cfic.edu.tugramola.dto.PersonaFila;


public class HolderPersona extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView caja_idNombre;
    private ImageView caja_idFoto;
    private ImageView caja_idLinIco;
    private ImageView caja_idGitIco;
    private TextView caja_idLin;
    private TextView caja_idGit;
    private String urlLin ="https://www.linkedin.com/in/";
    private String urlGit ="https://github.com/";

    public HolderPersona(View itemView) {
        super(itemView);
        caja_idNombre = (TextView) itemView.findViewById(R.id.idNombre);
        caja_idFoto = (ImageView) itemView.findViewById(R.id.idFoto);
        caja_idLinIco = (ImageView) itemView.findViewById(R.id.idLinIco);
        caja_idGitIco = (ImageView) itemView.findViewById(R.id.idGitIco);
        caja_idLin = (TextView) itemView.findViewById(R.id.idLin);
        caja_idGit = (TextView) itemView.findViewById(R.id.idGit);

        caja_idLinIco.setOnClickListener(this);
        caja_idGitIco.setOnClickListener(this);
        caja_idNombre.setOnClickListener(this);
    }


    public void cargarPersona(PersonaFila p) {
        Resources resources = caja_idGitIco.getResources();
        int resourceId;

        caja_idNombre.setText(p.getNombre());

        resourceId = resources.getIdentifier(p.getFoto(), "drawable", BuildConfig.APPLICATION_ID );
        caja_idFoto.setImageResource(resourceId);

        resourceId = resources.getIdentifier(p.getLinIco(), "drawable", BuildConfig.APPLICATION_ID );
        caja_idLinIco.setImageResource(resourceId);

        resourceId = resources.getIdentifier(p.getGitIco(), "drawable", BuildConfig.APPLICATION_ID );
        caja_idGitIco.setImageResource(resourceId);

        caja_idLin.setText(p.getLin());
        caja_idGit.setText(p.getGit());
        Log.d("MIAPP","HolderPers#a: "+caja_idLin.getText()+"#"+p.getLin());
        Log.d("MIAPP","HolderPers#b: "+caja_idGit.getText()+"#"+p.getGit());
    }

    @Override
    public void onClick(View view) {
        String url;
        Intent i;
        switch (view.getId())
        {   case R.id.idLinIco:
            if ( caja_idLin.getText().toString().isEmpty() ) {
                view.setClickable(false);
            } else{
                view.setClickable(true);
                url = urlLin + caja_idLin.getText();
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                (view.getContext()).startActivity(i);
                Log.d("MIAPP", "HolderPers#1 :" + url);
            }
            break;

            case R.id.idGitIco:
            if ( caja_idGit.getText().toString().isEmpty() ) {
                view.setClickable(false);
            } else {
                view.setClickable(true);
                url = urlGit + caja_idGit.getText();
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                (view.getContext()).startActivity(i);
                Log.d("MIAPP", "HolderPers#2 :" + url);
            }
            break;
/*
            case R.id.idNombre:
            Log.d("MIAPP","HolderPers#3");
            Log.d("MIAPP","VA-Nombre:"+((TextView)view.findViewById(R.id.idNombre)).getText().toString());
*/
        }
    }

}