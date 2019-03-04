package com.example.inmodroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inmodroid.R;
import com.example.inmodroid.adapters.MyMisPropiedadesRecyclerViewAdapter;
import com.example.inmodroid.fragments.dummy.DummyContent;
import com.example.inmodroid.fragments.dummy.DummyContent.DummyItem;
import com.example.inmodroid.models.Property;
import com.example.inmodroid.models.TipoAutenticacion;
import com.example.inmodroid.responses.ResponseContainer;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.PropertiesService;
import com.example.inmodroid.util.Util;
import com.example.inmodroid.util.UtilToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisPropiedadesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private Context ctx;
    private List<Property> listaPropiedadesUsuario;
    private MyMisPropiedadesRecyclerViewAdapter adapter;


    public MisPropiedadesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MisPropiedadesFragment newInstance(int columnCount) {
        MisPropiedadesFragment fragment = new MisPropiedadesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mispropiedades_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            PropertiesService propertiesService = ServiceGenerator.createService(PropertiesService.class, Util.getToken(ctx), TipoAutenticacion.JWT);
            Call<ResponseContainer<Property>> callUserProperties = propertiesService.getUserProperties();

            callUserProperties.enqueue(new Callback<ResponseContainer<Property>>() {
                @Override
                public void onResponse(Call<ResponseContainer<Property>> call, Response<ResponseContainer<Property>> response) {
                    if(response.isSuccessful()){
                        listaPropiedadesUsuario = response.body().getRows();
                        adapter = new MyMisPropiedadesRecyclerViewAdapter(ctx,R.layout.fragment_mispropiedades,listaPropiedadesUsuario,mListener);
                        recyclerView.setAdapter(adapter);


                    }else{
                        Toast.makeText(getActivity(), "Error en la peticion", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseContainer<Property>> call, Throwable t) {
                    Log.e("NetworkFailure", t.getMessage());
                    Toast.makeText(getActivity(), "Error de conexión", Toast.LENGTH_SHORT).show();

                }
            });
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ctx = context;
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
