package com.example.inmodroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.inmodroid.R;

import com.example.inmodroid.listeners.OnListInmueblesInteractionListener;
import com.example.inmodroid.models.Property;
import com.example.inmodroid.models.ResponseContainer;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.PropertiesService;
import com.example.inmodroid.ui.DetailsActivity;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyInmueblesRecyclerViewAdapter extends RecyclerView.Adapter<MyInmueblesRecyclerViewAdapter.ViewHolder> {

    private final List<Property> mValues;
    private final OnListInmueblesInteractionListener mListener;
    private Context ctx;
    private Property propiedad;

    public MyInmueblesRecyclerViewAdapter(Context ctx, int layout, List<Property> items, OnListInmueblesInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_inmuebles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.tituloPropiedad.setText(holder.mItem.getTitle());
        holder.precioPropiedad.setText(holder.mItem.getPrice());
        if (holder.mItem.getPhotos() != null) {
            Glide.with(ctx).load(holder.mItem.getPhotos().get(0)).into(holder.imagenInmueble);
        } else {
            Glide.with(ctx).load("http://www.bellezaverde.es/wp-content/uploads/2017/08/wnetrze-w-szarosciach-nowoczesne-13.jpg").into(holder.imagenInmueble);
        }

       /* holder.constraintPropiedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PropertiesService propertiesService = ServiceGenerator.createService(PropertiesService.class);
                Call<ResponseContainer<Property>> callOneProperty = propertiesService.getOnePropertyById(holder.mItem.getId());
                callOneProperty.enqueue(new Callback<ResponseContainer<Property>>() {
                    @Override
                    public void onResponse(Call<ResponseContainer<Property>> call, Response<ResponseContainer<Property>> response) {
                        Property resp = (Property) response.body().getRows();
                        Intent detailsActivity = new Intent(ctx , DetailsActivity.class);
                        detailsActivity.putExtra("property", resp);
                        ctx.startActivity(detailsActivity);


                    }

                    @Override
                    public void onFailure(Call<ResponseContainer<Property>> call, Throwable t) {

                    }
                });


            }
        });*/

        holder.imagenInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, DetailsActivity.class);
                i.putExtra("id", mValues.get(position).getId());
                ctx.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tituloPropiedad;
        public final TextView precioPropiedad;
        public final ImageView imagenInmueble;
        public Property mItem;
        public ConstraintLayout constraintPropiedades;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            tituloPropiedad =  view.findViewById(R.id.tituloPropiedad);
            precioPropiedad =  view.findViewById(R.id.precioPropiedad);
            imagenInmueble = view.findViewById(R.id.imagenPropiedadLista);
            constraintPropiedades = view.findViewById(R.id.constraintListaInmuebles);
        }


    }
}
