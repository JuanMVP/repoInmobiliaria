package com.example.inmodroid.ui;

import android.content.Intent;
import android.net.Uri;
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
import com.example.inmodroid.responses.UploadPhotoResponse;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.CategoriesService;
import com.example.inmodroid.retrofit.services.PhotoService;
import com.example.inmodroid.retrofit.services.PropertiesService;
import com.example.inmodroid.util.Util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyAddActivity extends AppCompatActivity  {
    private String token;
    private EditText addTitulo,addDireccion,addDescripcion,addCodigoPostal,addHabitacion,addPrecio,provincia, ciudad;
    private Button btnSubirImagen, btnAddPropiedad;
    private Spinner categorias;
    private Uri uriSeleccionada;
    public static final int READ_REQUEST_CODE = 7;
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
        btnSubirImagen = findViewById(R.id.buttonAddPhoto);
        btnAddPropiedad = findViewById(R.id.buttonAddPropiedad);
        categorias = findViewById(R.id.spinnerCategoriasAddPropiedad);

        token = Util.getToken(this);
        cargarSpinerCategorias();



        btnSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
                // browser.
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                // Filter to only show results that can be "opened", such as a
                // file (as opposed to a list of contacts or timezones)
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                // Filter to show only images, using the image MIME data type.
                // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
                // To search for all documents available via installed storage providers,
                // it would be "*/*".
                intent.setType("image/*");

                startActivityForResult(intent, READ_REQUEST_CODE);


            }
        });

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


    public void subidaDeImagenes(final AddPropertyDto nuevaPropiedad){

        try {
            InputStream inputStream = getContentResolver().openInputStream(uriSeleccionada);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            int cantBytes;
            byte[] buffer = new byte[1024 * 4];

            while ((cantBytes = bufferedInputStream.read(buffer, 0, 1024 * 4)) != -1) {
                baos.write(buffer, 0, cantBytes);
            }

            RequestBody photoRequest = RequestBody.create(MediaType.parse(getContentResolver().getType(uriSeleccionada)), baos.toByteArray());

            MultipartBody.Part photoBody = MultipartBody.Part.createFormData("photo", "photo", photoRequest);

            RequestBody idPropiedad = RequestBody.create(MultipartBody.FORM, nuevaPropiedad.getId());

            PhotoService photoService = ServiceGenerator.createService(PhotoService.class, Util.getToken(PropertyAddActivity.this), TipoAutenticacion.JWT);
            Call<UploadPhotoResponse> photoCall = photoService.uploadPhoto(photoBody, idPropiedad);
            photoCall.enqueue(new Callback<UploadPhotoResponse>() {
                @Override
                public void onResponse(Call<UploadPhotoResponse> call, Response<UploadPhotoResponse> response) {
                    if (response.isSuccessful()) {
                        nuevaPropiedad.getPhotos().add(response.body().getId());
                        Toast.makeText(PropertyAddActivity.this, "Imagen Subida con éxito!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PropertyAddActivity.this, "Error al Subir la imagen", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UploadPhotoResponse> call, Throwable t) {
                    Log.e("UploadError", t.getMessage());
                    Toast.makeText(PropertyAddActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (IOException e){
            e.printStackTrace();
        }


    }

}
