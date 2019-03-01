package com.example.inmodroid.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.inmodroid.R;
import com.example.inmodroid.adapters.PhotoAdapter;
import com.example.inmodroid.models.OneResponseContainer;
import com.example.inmodroid.models.Property;
import com.example.inmodroid.models.ResponseContainer;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.PropertiesService;
import com.example.inmodroid.util.Util;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private List<List<String>> photos;
    private PhotoAdapter adapterImagen;
    private TextView titulo, descripcion, precio, metrosCuadrados, habitaciones, codigoPostal, direccion, categoria, ciudad;
    private ImageView imagenDetalle;
    private Context ctx;
    Property propiedad;
    private ViewPager visorDeFotos;
    SliderLayout imagenSlider;
    String token;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i = getIntent();

        String idPropiedad = i.getStringExtra("id");


        token = Util.getToken(this);
        cargarDetalles();



        PropertiesService propertiesService = ServiceGenerator.createService(PropertiesService.class);
        Call<OneResponseContainer<Property>> callOneProperty = propertiesService.getOnePropertyById(idPropiedad);
        callOneProperty.enqueue(new Callback<OneResponseContainer<Property>>() {
            @Override
            public void onResponse(Call<OneResponseContainer<Property>> call, Response<OneResponseContainer<Property>> response) {
                if (response.code() != 200) {
                    Toast.makeText(DetailsActivity.this, "Error al ver Inmueble", Toast.LENGTH_SHORT).show();
                } else {
                    propiedad =  response.body().getRows();
                    setearDetalles();
                }

            }

            @Override
            public void onFailure(Call<OneResponseContainer<Property>> call, Throwable t) {

            }
        });

    }

    private void setearDetalles() {



        adapterImagen = new PhotoAdapter(DetailsActivity.this,propiedad.getPhotos());
        visorDeFotos.setAdapter(adapterImagen);
        titulo.setText(propiedad.getTitle());
        categoria.setText(propiedad.getCategoryId().getName());
        descripcion.setText(propiedad.getDescription());
        ciudad.setText(propiedad.getCity() + " - " + propiedad.getProvince());
        direccion.setText(propiedad.getAddress());
        precio.setText(propiedad.getPrice() + " €/mes");
        metrosCuadrados.setText(propiedad.getSize() + "m/2");
        habitaciones.setText(propiedad.getRooms());
        codigoPostal.setText(propiedad.getZipcode());
        /*if (propiedad.getPhotos() != null) {
            Glide.with(ctx).load(propiedad.getPhotos().get(0)).into(imagenDetalle);
        } else {
            Glide.with(ctx).load("http://www.bellezaverde.es/wp-content/uploads/2017/08/wnetrze-w-szarosciach-nowoczesne-13.jpg").into(imagenDetalle);
        }*/




    }

    private void cargarDetalles() {

        ctx=this;

        titulo = findViewById(R.id.tituloDetalle);
        precio = findViewById(R.id.precioDetalle);
        descripcion = findViewById(R.id.descripcionDetalle);
        habitaciones = findViewById(R.id.habitacionDetalle);
        metrosCuadrados = findViewById(R.id.metrosDetalle);
        codigoPostal = findViewById(R.id.codigopostalDetalle);
        direccion = findViewById(R.id.direccionDetalle);
        categoria = findViewById(R.id.categoriaDetalle);
        ciudad = findViewById(R.id.ciudadDetalle);
        //imagenDetalle = findViewById(R.id.imagenPropiedadDetalle);
        visorDeFotos = findViewById(R.id.imagenPropiedadDetalle);

        /*imagenSlider = findViewById(R.id.imageSlider);
        imagenSlider.setScrollTimeInSec(1);*/

        //setSliderViews();


    }

   /* private void setSliderViews() {


        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = (SliderView) Arrays.asList(propiedad.getPhotos());


            switch (i) {
                case 0:
                    if (propiedad.getPhotos() != null) {
                        sliderView.setImageUrl(propiedad.getPhotos().get(0));
                    } else {
                        sliderView.setImageUrl("http://www.bellezaverde.es/wp-content/uploads/2017/08/wnetrze-w-szarosciach-nowoczesne-13.jpg");
                    }
                    break;
                case 1:
                    if (propiedad.getPhotos() != null) {
                        sliderView.setImageUrl(propiedad.getPhotos().get(1));
                    } else {
                        sliderView.setImageUrl("http://www.bellezaverde.es/wp-content/uploads/2017/08/wnetrze-w-szarosciach-nowoczesne-13.jpg");
                    }
                case 2:
                    if (propiedad.getPhotos() != null) {
                        sliderView.setImageUrl(propiedad.getPhotos().get(2));
                    } else {
                        sliderView.setImageUrl("http://www.bellezaverde.es/wp-content/uploads/2017/08/wnetrze-w-szarosciach-nowoczesne-13.jpg");
                    }
                case 3:
                    if (propiedad.getPhotos() != null) {
                        sliderView.setImageUrl(propiedad.getPhotos().get(3));
                    } else {
                        sliderView.setImageUrl("http://www.bellezaverde.es/wp-content/uploads/2017/08/wnetrze-w-szarosciach-nowoczesne-13.jpg");
                    }
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription("setDescription " + (i + 1));
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(DetailsActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            imagenSlider.addSliderView(sliderView);
        }

    }*/
}
