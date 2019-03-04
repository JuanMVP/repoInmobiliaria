package com.example.inmodroid.ui;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.inmodroid.R;
import com.example.inmodroid.models.Category;
import com.example.inmodroid.models.EditPropertyDto;
import com.example.inmodroid.models.TipoAutenticacion;
import com.example.inmodroid.responses.ResponseContainer;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.CategoriesService;
import com.example.inmodroid.retrofit.services.PropertiesService;
import com.example.inmodroid.util.Util;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyEditActivity extends AppCompatActivity {

    private EditText tituloEdit, descripcionEdit, precioEdit, sizeEdit, codigoPostalEdit, direccionEdit, habitacionEdit,ciudadEdit,provinciaEdit;
    private Button btnEditarPropiedad;
    private Spinner categorias;
    private List<Category> listaCategorias;
    private String direccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_edit);

        Intent i = getIntent();
        final EditPropertyDto editProperty = (EditPropertyDto) i.getSerializableExtra("property");


        tituloEdit = findViewById(R.id.tituloEditPropiedad);
        descripcionEdit = findViewById(R.id.descripcionEditPropiedad);
        precioEdit = findViewById(R.id.precioEditPropiedad);
        //sizeEdit = findViewById(R.id.);
        codigoPostalEdit = findViewById(R.id.codigoPostalEditPropiedad);
        direccionEdit = findViewById(R.id.direccionEditPropiedad);
        habitacionEdit = findViewById(R.id.habitacionesEditPropiedad);
        ciudadEdit = findViewById(R.id.ciudadEditPropiedad);
        provinciaEdit = findViewById(R.id.provinciaEditPropiedad);
        btnEditarPropiedad = findViewById(R.id.buttonEditPropiedad);
        cargarSpinerCategorias();

        tituloEdit.setText(editProperty.getTitle());
        descripcionEdit.setText(editProperty.getDescription());
        precioEdit.setText(String.valueOf(editProperty.getPrice()));
        direccionEdit.setText(editProperty.getAddress());
        //size.setText(String.valueOf(editProperty.getSize()));
        codigoPostalEdit.setText(String.valueOf(editProperty.getZipcode()));
        direccionEdit.setText(editProperty.getAddress());
        habitacionEdit.setText(String.valueOf(editProperty.getRooms()));
        ciudadEdit.setText(editProperty.getCity());
        provinciaEdit.setText(editProperty.getProvince());
        categorias = findViewById(R.id.spinnerCategoriasEditPropiedad);


        btnEditarPropiedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                EditPropertyDto editedProperty = editProperty;
                Category chosen = (Category) categorias.getSelectedItem();
                editedProperty.setTitle(tituloEdit.getText().toString());
                editedProperty.setRooms(Long.parseLong(habitacionEdit.getText().toString()));
                editedProperty.setDescription(descripcionEdit.getText().toString());
                editedProperty.setAddress(direccionEdit.getText().toString());
                editedProperty.setZipcode(codigoPostalEdit.getText().toString());
                editedProperty.setCity(ciudadEdit.getText().toString());
                editedProperty.setPrice(Long.parseLong(precioEdit.getText().toString()));
                //edited.setSize(Long.parseLong(size.getText().toString()));
                editedProperty.setProvince(provinciaEdit.getText().toString());
                editedProperty.setCategoryId(chosen.getId());
                editedProperty.setLoc("07, 07");

                PropertiesService propertiesService = ServiceGenerator.createService(PropertiesService.class, Util.getToken(PropertyEditActivity.this), TipoAutenticacion.JWT);
                Call<EditPropertyDto> callEditProperty = propertiesService.editProperty(editedProperty.getId(),editProperty);
                callEditProperty.enqueue(new Callback<EditPropertyDto>() {
                    @Override
                    public void onResponse(Call<EditPropertyDto> call, Response<EditPropertyDto> response) {
                        if(response.isSuccessful()){

                            Toast.makeText(PropertyEditActivity.this, "Editado", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(PropertyEditActivity.this, "Fallo al Editar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<EditPropertyDto> call, Throwable t) {

                    }
                });


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
                    ArrayAdapter<Category> adapter = new ArrayAdapter<>(PropertyEditActivity.this,android.R.layout.simple_spinner_dropdown_item,listaCategorias);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categorias.setAdapter(adapter);
                    categorias.setSelection(listaCategorias.size()-1);


                }else{
                    Toast.makeText(PropertyEditActivity.this, "Error al obtener categorias", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Category>> call, Throwable t) {
                Toast.makeText(PropertyEditActivity.this, "Error de red", Toast.LENGTH_SHORT).show();

            }
        });



    }

}
