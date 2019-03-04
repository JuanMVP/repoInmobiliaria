package com.example.inmodroid.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.inmodroid.R;
import com.example.inmodroid.fragments.MisPropiedadesFragment.OnListFragmentInteractionListener;
import com.example.inmodroid.fragments.dummy.DummyContent.DummyItem;
import com.example.inmodroid.models.EditPropertyDto;
import com.example.inmodroid.models.Property;
import com.example.inmodroid.models.TipoAutenticacion;
import com.example.inmodroid.responses.OneResponseContainer;
import com.example.inmodroid.responses.ResponseContainer;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.PropertiesService;
import com.example.inmodroid.ui.PropertyEditActivity;
import com.example.inmodroid.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMisPropiedadesRecyclerViewAdapter extends RecyclerView.Adapter<MyMisPropiedadesRecyclerViewAdapter.ViewHolder> {

    private final List<Property> mValues;
    private final OnListFragmentInteractionListener mListener;
    String token;
    Context ctx;

    public MyMisPropiedadesRecyclerViewAdapter(Context ctx, int layout,List<Property> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mispropiedades, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        token = Util.getToken(ctx);
        holder.mItem = mValues.get(position);

        holder.title.setText(mValues.get(position).getTitle());
        holder.price.setText(mValues.get(position).getPrice());
        if (holder.mItem.getPhotos() != null) {
            Glide.with(ctx).load(holder.mItem.getPhotos().get(0)).into(holder.photo);
        } else {
            Glide.with(ctx).load("http://www.bellezaverde.es/wp-content/uploads/2017/08/wnetrze-w-szarosciach-nowoczesne-13.jpg").into(holder.photo);
        }


        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditPropertyDto propiedadEditada = new EditPropertyDto();
                PropertiesService propertiesService = ServiceGenerator.createService(PropertiesService.class);
                Call<OneResponseContainer<Property>> callOneProperty = propertiesService.getOnePropertyById(holder.mItem.getId());
                callOneProperty.enqueue(new Callback<OneResponseContainer<Property>>() {
                    @Override
                    public void onResponse(Call<OneResponseContainer<Property>> call, Response<OneResponseContainer<Property>> response) {

                        Property property = response.body().getRows();
                        propiedadEditada.setId(holder.mItem.getId());
                        propiedadEditada.setAddress(property.getAddress());
                        propiedadEditada.setCategoryId(property.getCategoryId().getId());
                        propiedadEditada.setCity(property.getCity());
                        propiedadEditada.setDescription(property.getDescription());
                        propiedadEditada.setLoc(property.getLoc());
                        //propiedadEditada.setOwnerId(holder.mItem.getOwnerId());
                        propiedadEditada.setPhotos(property.getPhotos());
                        propiedadEditada.setPrice(Long.valueOf(property.getPrice()));
                        propiedadEditada.setRooms(Long.valueOf(property.getRooms()));
                        propiedadEditada.setProvince(property.getProvince());
                        propiedadEditada.setZipcode(property.getZipcode());
                        propiedadEditada.setSize(Long.valueOf(property.getSize()));
                        propiedadEditada.setTitle(property.getTitle());

                        Intent editPropertyActivity = new Intent(ctx, PropertyEditActivity.class);
                        editPropertyActivity.putExtra("property", propiedadEditada);
                        ctx.startActivity(editPropertyActivity);


                    }

                    @Override
                    public void onFailure(Call<OneResponseContainer<Property>> call, Throwable t) {

                    }
                });
            }
        });



        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Peligro").setMessage("Estas seguro de que quieres borrar la propiedad?");
                builder.setPositiveButton("Ir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteProperty(holder);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("Back", "Going back");
                    }
                });
                AlertDialog dialog = builder.create();

                dialog.show();
            }
        });


    }

    private void deleteProperty(ViewHolder holder) {
        String id = holder.mItem.getId();
        PropertiesService propertiesService = ServiceGenerator.createService(PropertiesService.class,Util.getToken(ctx), TipoAutenticacion.JWT);
        Call<ResponseContainer<Property>> deleteCall = propertiesService.delete(id);
        deleteCall.enqueue(new Callback<ResponseContainer<Property>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Property>> call, Response<ResponseContainer<Property>> response) {
                if (response.code() == 200 || response.code() == 204) {
                    Toast.makeText(ctx, "Elimando Correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ctx, "Error al eliminar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Property>> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final TextView price;
        public final ImageView photo;
        public final ImageButton btnEditar,btnEliminar;

        public Property mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.tituloPropiedadListaUsuario);
            price = view.findViewById(R.id.precioPropiedadListaUsuario);
            photo = view.findViewById(R.id.imagenPropiedadListaUsuario);
            btnEditar = view.findViewById(R.id.buttonEditarMiPropiedad);
            btnEliminar = view.findViewById(R.id.buttonEliminarMiPropiedad);
        }


    }
}
