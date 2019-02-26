package com.example.inmodroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inmodroid.R;

import com.example.inmodroid.listeners.OnListInmueblesInteractionListener;
import com.example.inmodroid.models.Property;

import java.util.List;




public class MyInmueblesRecyclerViewAdapter extends RecyclerView.Adapter<MyInmueblesRecyclerViewAdapter.ViewHolder> {

    private final List<Property> mValues;
    private final OnListInmueblesInteractionListener mListener;
    private Context ctx;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tituloPropiedad.setText(holder.mItem.getTitle());
        holder.precioPropiedad.setText(holder.mItem.getPrice());


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tituloPropiedad;
        public final TextView precioPropiedad;
        public Property mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            tituloPropiedad =  view.findViewById(R.id.tituloPropiedad);
            precioPropiedad =  view.findViewById(R.id.precioPropiedad);
        }


    }
}
