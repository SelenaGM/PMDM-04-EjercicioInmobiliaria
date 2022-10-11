package com.example.pmdm_04_ejercicioinmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pmdm_04_ejercicioinmobiliaria.databinding.ActivityAddPisoBinding;
import com.example.pmdm_04_ejercicioinmobiliaria.modelos.Piso;

public class AddPisoActivity extends AppCompatActivity {

    private ActivityAddPisoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddPisoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarAddPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso piso = crearPiso();
                if(piso!=null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PISO", piso);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    Toast.makeText(AddPisoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Piso crearPiso() {
        if(binding.txtDireccionAddPiso.getText().toString().isEmpty() || binding.txtNumeroAddPiso.getText().toString().isEmpty() ||
        binding.txtCiudadAddPiso.getText().toString().isEmpty() || binding.txtProvinciaAddPiso.getText().toString().isEmpty() || binding.txtCpAddPiso.getText().toString().isEmpty()) {
            return null;
        }
        if(binding.rbValoracionAddPiso.getRating() == 0){
            return null;
        
        }
        int numero = Integer.parseInt(binding.txtNumeroAddPiso.getText().toString());
        Float valoracion = binding.rbValoracionAddPiso.getRating() ;
        
        return new Piso(binding.txtDireccionAddPiso.getText().toString(),numero ,
                binding.txtCiudadAddPiso.getText().toString(), binding.txtProvinciaAddPiso.getText().toString(),binding.txtCpAddPiso.getText().toString(),valoracion);
    }
}