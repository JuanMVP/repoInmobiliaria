package com.example.inmodroid.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inmodroid.R;
import com.example.inmodroid.models.Category;
import com.example.inmodroid.models.TipoAutenticacion;
import com.example.inmodroid.responses.AddPropertyResponse;
import com.example.inmodroid.responses.ResponseContainer;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.CategoriesService;
import com.example.inmodroid.retrofit.services.PropertiesService;
import com.example.inmodroid.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyAddActivity extends AppCompatActivity {
    private String token;
    private EditText addTitulo,addDireccion,addDescripcion,addCodigoPostal,addHabitacion,addPrecio;
    private TextView region,provincia,municipio;
    private Button btnSeleccionarCiudad, btnAddPropiedad;
    private Spinner categorias;
    private List<Category> listaCategorias;

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
        categorias = findViewById(R.id.spinnerCategoriasAddPropiedad);

        token = Util.getToken(this);
        cargarSpinerCategorias();




        btnAddPropiedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertiesService propertiesService = ServiceGenerator.createService(PropertiesService.class,token, TipoAutenticacion.JWT);
                Call<AddPropertyResponse> callAddProperty = propertiesService.addProperty( new AddPropertyResponse())
            }
        });



    }

    private void cargarSpinerCategorias() {

        CategoriesService serviceCategoria = ServiceGenerator.createService(CategoriesService.class);
        Call<ResponseContainer<Category>> callCategorias = serviceCategoria.getCategoryList();
        callCategorias.enqueue(new Callback<ResponseContainer<Category>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Category>> call, Response<ResponseContainer<Category>> response) {
                if(response.isSuccessful()){
                    listaCategorias = response.body().getRows();
                    ArrayAdapter<Category> adapter = new ArrayAdapter<>(PropertyAddActivity.this,android.R.layout.simple_spinner_dropdown_item,listaCategorias);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categorias.setAdapter(adapter);
                    categorias.setSelection(listaCategorias.size()-1);
                    
                    
                }else{
                    Toast.makeText(PropertyAddActivity.this, "Error al obtener categorias", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Category>> call, Throwable t) {
                Toast.makeText(PropertyAddActivity.this, "Error de red", Toast.LENGTH_SHORT).show();

            }
        });
            


    }


    private void setearPropiedad(){
        AddPropertyResponse nuevaPropiedad = new AddPropertyResponse();
        nuevaPropiedad.setTitle(addTitulo.getText().toString());
        nuevaPropiedad.setAddress(addDireccion.getText().toString());
        nuevaPropiedad.setDescription(addDescripcion.getText().toString());
        nuevaPropiedad.setZipcode(addCodigoPostal.getText().toString());
        nuevaPropiedad.setRooms(Long.parseLong(addHabitacion.getText().toString()));
        nuevaPropiedad.setPrice(Long.parseLong(addPrecio.getText().toString()));
    }

}
