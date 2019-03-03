package com.example.inmodroid.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inmodroid.R;
import com.example.inmodroid.models.AddPropertyDto;
import com.example.inmodroid.models.Category;
import com.example.inmodroid.models.TipoAutenticacion;
import com.example.inmodroid.responses.AddPropertyResponse;
import com.example.inmodroid.responses.ResponseContainer;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.CategoriesService;
import com.example.inmodroid.retrofit.services.PropertiesService;
import com.example.inmodroid.util.Util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyAddActivity extends AppCompatActivity  {
    private String token;
    private EditText addTitulo,addDireccion,addDescripcion,addCodigoPostal,addHabitacion,addPrecio,provincia, ciudad;
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

        provincia = findViewById(R.id.provinciaAddPropiedad);
        ciudad = findViewById(R.id.ciudadAddPropiedad);

        btnAddPropiedad = findViewById(R.id.buttonAddPropiedad);
        categorias = findViewById(R.id.spinnerCategoriasAddPropiedad);

        token = Util.getToken(this);
        cargarSpinerCategorias();




        btnAddPropiedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Category category = (Category) categorias.getSelectedItem();

                AddPropertyDto nuevaPropiedad = new AddPropertyDto(
                        addTitulo.getText().toString(),
                        addDescripcion.getText().toString(),
                        Integer.valueOf(addPrecio.getText().toString()),
                        Integer.valueOf(addHabitacion.getText().toString()),
                        category.getId(),
                        addDireccion.getText().toString(),
                        addCodigoPostal.getText().toString(),
                        ciudad.getText().toString(),
                        provincia.getText().toString(),
                        "07, 07");

                PropertiesService service = ServiceGenerator.createService(PropertiesService.class, Util.getToken(PropertyAddActivity.this), TipoAutenticacion.JWT);
                Call<AddPropertyResponse> call = service.addProperty(nuevaPropiedad);

                call.enqueue(new Callback<AddPropertyResponse>() {
                    @Override
                    public void onResponse(Call<AddPropertyResponse> call, Response<AddPropertyResponse> response) {
                        if (response.code() != 201) {
                            Toast.makeText(PropertyAddActivity.this, "Fallo al crear inmueble", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PropertyAddActivity.this, "Inmueble creado!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddPropertyResponse> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                        Toast.makeText(PropertyAddActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });




                //datosParaCrearUnaPropiedad();




              /*  AddPropertyResponse nuevaPropiedad = new AddPropertyResponse();
                nuevaPropiedad.setTitle(addTitulo.getText().toString());
                nuevaPropiedad.setAddress(addDireccion.getText().toString());
                nuevaPropiedad.setDescription(addDescripcion.getText().toString());
                nuevaPropiedad.setZipcode(addCodigoPostal.getText().toString());
                nuevaPropiedad.setRooms(Long.parseLong(addHabitacion.getText().toString()));
                nuevaPropiedad.setPrice(Long.parseLong(addPrecio.getText().toString()));
                nuevaPropiedad.setProvince(provincia.getText().toString());
                nuevaPropiedad.setCity(ciudad.getText().toString());
                Category categoriaSeleccionada = (Category) categorias.getSelectedItem();
                nuevaPropiedad.setCategoryId(categoriaSeleccionada.getName());




                PropertiesService propertiesService = ServiceGenerator.createService(PropertiesService.class,token, TipoAutenticacion.JWT);
                Call<AddPropertyResponse> callAddProperty = propertiesService.addProperty( new AddPropertyResponse(nuevaPropiedad));
                callAddProperty.enqueue(new Callback<AddPropertyResponse>() {
                    @Override
                    public void onResponse(Call<AddPropertyResponse> call, Response<AddPropertyResponse> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(PropertyAddActivity.this, "Propiedad Creada con éxito", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(PropertyAddActivity.this, "Error al crear la propiedad", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddPropertyResponse> call, Throwable t) {

                        Toast.makeText(PropertyAddActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });*/



    }




    /*private void datosParaCrearUnaPropiedad(){
        AddPropertyResponse nuevaPropiedad = new AddPropertyResponse();
        nuevaPropiedad.setTitle(addTitulo.getText().toString());
        nuevaPropiedad.setAddress(addDireccion.getText().toString());
        nuevaPropiedad.setDescription(addDescripcion.getText().toString());
        nuevaPropiedad.setZipcode(addCodigoPostal.getText().toString());
        nuevaPropiedad.setRooms(Long.parseLong(addHabitacion.getText().toString()));
        nuevaPropiedad.setPrice(Long.parseLong(addPrecio.getText().toString()));
        nuevaPropiedad.setProvince(provincia.getText().toString());
        nuevaPropiedad.setCity(ciudad.getText().toString());
       Category categoriaSeleccionada = (Category) categorias.getSelectedItem();
       nuevaPropiedad.setCategoryId(categoriaSeleccionada.getName());

       crearNuevaPropiedad(nuevaPropiedad);
    }

    private void crearNuevaPropiedad(AddPropertyResponse nuevaPropiedad) {

        PropertiesService propertiesService = ServiceGenerator.createService(PropertiesService.class,token, TipoAutenticacion.JWT);
        Call<AddPropertyResponse> callAddProperty = propertiesService.addProperty( new AddPropertyResponse(nuevaPropiedad));
        callAddProperty.enqueue(new Callback<AddPropertyResponse>() {
            @Override
            public void onResponse(Call<AddPropertyResponse> call, Response<AddPropertyResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(PropertyAddActivity.this, "Propiedad Creada con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PropertyAddActivity.this, "Error al crear la propiedad", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddPropertyResponse> call, Throwable t) {

                Toast.makeText(PropertyAddActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

            }
        });
*/
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


}
