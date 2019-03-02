package com.example.inmodroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.inmodroid.R;
import com.example.inmodroid.fragments.MisPropiedadesFragment.OnListFragmentInteractionListener;
import com.example.inmodroid.fragments.dummy.DummyContent.DummyItem;
import com.example.inmodroid.models.Property;
import com.example.inmodroid.util.Util;

import java.util.List;

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

        public Property mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.tituloPropiedadListaUsuario);
            price = view.findViewById(R.id.precioPropiedadListaUsuario);
            photo = view.findViewById(R.id.imagenPropiedadListaUsuario);
        }


    }
}
