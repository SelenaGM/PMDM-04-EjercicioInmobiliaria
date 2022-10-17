package com.example.pmdm_04_ejercicioinmobiliaria;

import android.content.Intent;
import android.os.Bundle;

import com.example.pmdm_04_ejercicioinmobiliaria.modelos.Piso;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;


import com.example.pmdm_04_ejercicioinmobiliaria.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> addPisoLauncher;
    private ArrayList<Piso> pisosLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        pisosLista= new ArrayList<>();
        inicializaLauncher();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPisoLauncher.launch(new Intent(MainActivity.this, AddPisoActivity.class));
            }
        });
    }

    private void inicializaLauncher() {
        addPisoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            if(result.getData()!= null && result.getData().getExtras() != null){
                                Piso pisito = (Piso) result.getData().getExtras().getSerializable("PISO");
                                if(pisito !=null){
                                    pisosLista.add(pisito);
                                    mostrarPisos();
                                }
                            }
                        }
                    }
                }
        );
    }

    private void mostrarPisos() {

        binding.contentMain.contenedor.removeAllViews();
        for (int i = 0; i < pisosLista.size(); i++) {
            Piso piso = pisosLista.get(i);
            View inmuebleView = LayoutInflater.from(MainActivity.this).inflate(R.layout.piso_model_view,null);
            TextView lblDireccion = inmuebleView.findViewById(R.id.lblDireccionPisoView);
            TextView lblNumero = inmuebleView.findViewById(R.id.lblNumeroAddPiso);
            TextView lblProvincia = inmuebleView.findViewById(R.id.lblProvinciaPisoModel);
            TextView lblCiudad = inmuebleView.findViewById(R.id.lblCiudadPisoModel);
            RatingBar rbValoracion = inmuebleView.findViewById(R.id.rbValPisoModel);

            lblDireccion.setText(piso.getDireccion());
            lblCiudad.setText(piso.getCiudad());
            lblProvincia.setText(piso.getProvincia());
            lblNumero.setText(String.valueOf(piso.getNumero()));
            rbValoracion.setRating(piso.getValoracion());
            binding.contentMain.contenedor.addView(inmuebleView);
        }

    }


}