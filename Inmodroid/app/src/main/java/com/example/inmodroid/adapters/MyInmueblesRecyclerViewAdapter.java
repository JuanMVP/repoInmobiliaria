package com.example.inmodroid.adapters;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.inmodroid.R;

import com.example.inmodroid.fragments.InmueblesFavoritosFragment;
import com.example.inmodroid.listeners.OnListInmueblesInteractionListener;
import com.example.inmodroid.models.Property;
import com.example.inmodroid.models.PropertyFav;
import com.example.inmodroid.models.TipoAutenticacion;
import com.example.inmodroid.responses.FavouriteResponse;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.PropertiesService;
import com.example.inmodroid.ui.DetailsActivity;
import com.example.inmodroid.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyInmueblesRecyclerViewAdapter extends RecyclerView.Adapter<MyInmueblesRecyclerViewAdapter.ViewHolder> {

    private final List<Property> mValues;
    private final OnListInmueblesInteractionListener mListener;
    private Context ctx;
    private Property propiedad;
    private List<FavouriteResponse> favoritos;
    private boolean isFav,fragmentFav;

    public MyInmueblesRecyclerViewAdapter(Context ctx, int layout, List<Property> items, OnListInmueblesInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }


    public MyInmueblesRecyclerViewAdapter(Context ctx, List<Property> items, OnListInmueblesInteractionListener listener, boolean fragmentFav) {
       this.ctx = ctx;
       mValues = items;
       mListener = listener;
       this.fragmentFav = fragmentFav;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_inmuebles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        //auth
        if(mValues == null){


            holder.mItem = mValues.get(position);
            holder.tituloPropiedad.setText(holder.mItem.getTitle());
            holder.precioPropiedad.setText(holder.mItem.getPrice());
            if (holder.mItem.getPhotos() != null) {
                Glide.with(ctx).load(holder.mItem.getPhotos().get(0)).into(holder.imagenInmueble);
            } else {
                Glide.with(ctx).load("http://www.bellezaverde.es/wp-content/uploads/2017/08/wnetrze-w-szarosciach-nowoczesne-13.jpg").into(holder.imagenInmueble);
            }



            holder.imagenInmueble.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ctx, DetailsActivity.class);
                    i.putExtra("id", mValues.get(position).getId());
                    ctx.startActivity(i);
                }
            });




        //noAuth
        }else{



            holder.mItem = mValues.get(position);
            holder.tituloPropiedad.setText(holder.mItem.getTitle());
            holder.precioPropiedad.setText(holder.mItem.getPrice());
            if (holder.mItem.getPhotos() != null) {
                Glide.with(ctx).load(holder.mItem.getPhotos().get(0)).into(holder.imagenInmueble);
            } else {
                Glide.with(ctx).load("http://www.bellezaverde.es/wp-content/uploads/2017/08/wnetrze-w-szarosciach-nowoczesne-13.jpg").into(holder.imagenInmueble);
            }



            holder.imagenInmueble.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ctx, DetailsActivity.class);
                    i.putExtra("id", mValues.get(position).getId());
                    ctx.startActivity(i);
                }
            });

            if(Util.getEmailUser(ctx) == null){
                holder.imagenNoFav.setVisibility(View.GONE);
            }else{
                isFav = mValues.get(position).isFav();

                if( isFav || fragmentFav){
                    holder.imagenNoFav.setImageResource(R.drawable.ic_favorite);
                }else{
                    holder.imagenNoFav.setImageResource(R.drawable.ic_favorite_border);
                }

                holder.imagenNoFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFav = mValues.get(position).isFav();
                        if(isFav || fragmentFav){
                            PropertiesService service = ServiceGenerator.createService(PropertiesService.class, Util.getToken(ctx), TipoAutenticacion.JWT);
                            Call<FavouriteResponse> callFavourites = service.deletePropertiesFav(holder.mItem.getId());

                            callFavourites.enqueue(new Callback<FavouriteResponse>() {
                                @Override
                                public void onResponse(Call<FavouriteResponse> call, Response<FavouriteResponse> response) {
                                    if(response.isSuccessful()){
                                        holder.imagenNoFav.setImageResource(R.drawable.ic_favorite_border);
                                        isFav = !isFav;
                                        Toast.makeText(ctx, "Favorito eliminado con éxito", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(ctx, "Error en la petición", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<FavouriteResponse> call, Throwable t) {

                                    Log.e("NetworkFailure", t.getMessage());
                                    Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show();

                                }
                            });
                            mValues.get(position).setFav(false);
                        }else{
                            PropertiesService service = ServiceGenerator.createService(PropertiesService.class,Util.getToken(ctx),TipoAutenticacion.JWT);
                            Call<FavouriteResponse> callNoFav = service.addPropertiesFav(holder.mItem.getId());
                            callNoFav.enqueue(new Callback<FavouriteResponse>() {
                                @Override
                                public void onResponse(Call<FavouriteResponse> call, Response<FavouriteResponse> response) {
                                    if(response.isSuccessful()){
                                        holder.imagenNoFav.setImageResource(R.drawable.ic_favorite);
                                        isFav = !isFav;
                                        Toast.makeText(ctx, "Añadido a favoritos correctamente", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(ctx, "Error de petición", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<FavouriteResponse> call, Throwable t) {
                                    Log.e("NetworkFailure", t.getMessage());
                                    Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show();

                                }
                            });
                            mValues.get(position).setFav(true);
                        }
                    }
                });



            }


        }

        holder.mItem = mValues.get(position);
        holder.tituloPropiedad.setText(holder.mItem.getTitle());
        holder.precioPropiedad.setText(holder.mItem.getPrice());
        if (holder.mItem.getPhotos() != null) {
            Glide.with(ctx).load(holder.mItem.getPhotos().get(0)).into(holder.imagenInmueble);
        } else {
            Glide.with(ctx).load("http://www.bellezaverde.es/wp-content/uploads/2017/08/wnetrze-w-szarosciach-nowoczesne-13.jpg").into(holder.imagenInmueble);
        }



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
        public final ImageView imagenInmueble, imagenNoFav;
        public Property mItem;
        public ConstraintLayout constraintPropiedades;
        public PropertyFav favProperty;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            tituloPropiedad =  view.findViewById(R.id.tituloPropiedad);
            precioPropiedad =  view.findViewById(R.id.precioPropiedad);
            imagenInmueble = view.findViewById(R.id.imagenPropiedadLista);
            constraintPropiedades = view.findViewById(R.id.constraintListaInmuebles);
            imagenNoFav = view.findViewById(R.id.imagenFavBorder);
        }


    }
}
