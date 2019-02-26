package com.example.inmodroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inmodroid.adapters.MyInmueblesRecyclerViewAdapter;
import com.example.inmodroid.R;
import com.example.inmodroid.dummy.DummyContent;
import com.example.inmodroid.dummy.DummyContent.DummyItem;
import com.example.inmodroid.listeners.OnListInmueblesInteractionListener;
import com.example.inmodroid.models.Property;
import com.example.inmodroid.models.ResponseContainer;
import com.example.inmodroid.responses.PropertiesResponse;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.PropertiesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InmueblesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListInmueblesInteractionListener mListener;
    private Context ctx;
    private RecyclerView recyclerView;
    private MyInmueblesRecyclerViewAdapter adapter;
    private List<Property> listaPropiedades;


    public InmueblesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static InmueblesFragment newInstance(int columnCount) {
        InmueblesFragment fragment = new InmueblesFragment();
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
        View view = inflater.inflate(R.layout.fragment_inmuebles_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            recyclerView = view.findViewById(R.id.inmueblesList);
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            
            cargarDatos(recyclerView);
            
            
        }
        return view;
    }

    private void cargarDatos(final RecyclerView recyclerView) {

        PropertiesService propertiesService = ServiceGenerator.createService(PropertiesService.class);
        Call<ResponseContainer<Property>> call = propertiesService.getProperties();

        call.enqueue(new Callback<ResponseContainer<Property>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Property>> call, Response<ResponseContainer<Property>> response) {
                if(response.isSuccessful()){
                    listaPropiedades = response.body().getRows();

                    adapter = new MyInmueblesRecyclerViewAdapter(
                            ctx,
                            R.layout.fragment_inmuebles,
                            listaPropiedades,
                            mListener
                    );
                    recyclerView.setAdapter(adapter);
                } else{
                    Toast.makeText(getContext(), "Error Al obtener Inmuebles", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Property>> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ctx = context;
        if (context instanceof OnListInmueblesInteractionListener) {
            mListener = (OnListInmueblesInteractionListener) context;
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
