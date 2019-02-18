package tablerocolores.cfic.edu.tugramola.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tablerocolores.cfic.edu.tugramola.R;


public class Fragment_busqueda_sin_resultado extends Fragment {


    public Fragment_busqueda_sin_resultado() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_cancion, container, false);

    }
}
