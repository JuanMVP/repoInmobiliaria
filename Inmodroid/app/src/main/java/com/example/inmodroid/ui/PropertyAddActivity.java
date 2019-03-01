package com.example.inmodroid.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inmodroid.R;

public class PropertyAddActivity extends AppCompatActivity {
    private EditText addTitulo,addDireccion,addDescripcion,addCodigoPostal,addHabitacion,addPrecio;
    private TextView region,provincia,municipio;
    private Button btnSeleccionarCiudad, btnAddPropiedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_add);

        addTitulo = findViewById(R.id.tituloAddPropiedad);
        addDireccion = findViewById(R.id.direccionAddPropiedad);
        addDescripcion = findViewById(R.id.descripcionAddPropiedad);
        addCodigoPostal = findViewById(R.id.codigoPostalAddPropiedad);
        addHabitacion = findViewById(R.id.habitacionesAddPropiedad);
        addPrecio = findViewById(R.id.precioAddPropiedad);
        region = findViewById(R.id.textRegiosAddPropiedad);
        provincia = findViewById(R.id.textProvinciaAddPropiedad);
        municipio = findViewById(R.id.textMunicipioAddPropiedad);
        btnSeleccionarCiudad = findViewById(R.id.buttonProvinciaAddPropiedad);
        btnAddPropiedad = findViewById(R.id.buttonAddPropiedad);


    }
}
