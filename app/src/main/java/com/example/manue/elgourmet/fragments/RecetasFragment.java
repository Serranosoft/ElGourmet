package com.example.manue.elgourmet.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.manue.elgourmet.R;
import com.example.manue.elgourmet.Util.Receta;
import com.example.manue.elgourmet.adapters.RecetasAdapter;
import com.example.manue.elgourmet.adapters.RecetasFiltrarAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RecetasFragment extends Fragment {

    private RecyclerView rList;
    private List<Receta> recetaLista;
    private RequestQueue mRequestQueue;
    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapter2;
    private OnRecetaSelected callback;
    private FloatingActionButton floating;
    private FloatingActionButton floatingRandom;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private FloatingActionButton back;


    private static final String api = "https://api.spoonacular.com/recipes/search?&apiKey=81b0fa8b8d5841c992c3df7c2c570ec0";
    private static final String random = "https://api.spoonacular.com/recipes/search?&apiKey=81b0fa8b8d5841c992c3df7c2c570ec0";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_recetas, container, false);


        rList = view.findViewById(R.id.lista);

        rList.setHasFixedSize(true);
        rList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recetaLista = new ArrayList<>();

        getData();

        floating = view.findViewById(R.id.fab);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopUp();
            }
        });

        back = view.findViewById(R.id.back);
        back.hide();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
                floating.show();
                back.hide();
            }
        });

        floatingRandom = view.findViewById(R.id.random);
        floatingRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getRandomData();

                back.show();
                floating.hide();
            }
        });

        return view;
    }

    private void createPopUp(){
        builder = new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.popup_filtrar, null);

        builder.setView(view);

        dialog= builder.create();
        dialog.show();


        final EditText porciones_input = view.findViewById(R.id.ingrediente);

        Button botonFiltrar = view.findViewById(R.id.popupFiltrar);
        Button mostrarTodo = view.findViewById(R.id.mostrarTodo);
        botonFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getDataFilter(Integer.parseInt(porciones_input.getText().toString()));

                dialog.dismiss();
            }
        });

        mostrarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                dialog.dismiss();
            }
        });



    }

    private void getData() {
        recetaLista.clear();
        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, api, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for(int i = 0; i < jsonArray.length() ; i++){
                                JSONObject receta = jsonArray.getJSONObject(i);

                                String nombreReceta = receta.getString("title");
                                String imagenUrl = receta.getString("image");
                                int tiempo = receta.getInt("readyInMinutes");
                                int porciones = receta.getInt("servings");
                                String url = receta.getString("sourceUrl");

                                Receta r2 = new Receta(imagenUrl, nombreReceta, tiempo,porciones, url);
                                recetaLista.add(r2);
                            }

                            adapter = new RecetasAdapter(recetaLista, getActivity().getApplicationContext(), new RecetasAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Receta receta, int position) {
                                    callback.onChange(receta);
                                }
                            });
                            rList.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());

            }
        });
        mRequestQueue.add(request);
    }

    private void getDataFilter(final int porciones_input) {
        recetaLista.clear();
        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, api, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for(int i = 0; i < jsonArray.length() ; i++){
                                JSONObject receta = jsonArray.getJSONObject(i);

                                String nombreReceta = receta.getString("title");
                                String imagenUrl = receta.getString("imagen");
                                int tiempo = receta.getInt("readyInMinutes");
                                int porciones = receta.getInt("servings");
                                String url = receta.getString("sourceUrl");

                                Receta r2 = new Receta(imagenUrl, nombreReceta, tiempo, porciones, url);
                                if(porciones_input == porciones){
                                    recetaLista.add(r2);
                                }

                            }

                            adapter2 = new RecetasFiltrarAdapter(recetaLista, getActivity().getApplicationContext(), new RecetasFiltrarAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Receta receta, int position) {
                                    callback.onChange(receta);
                                }
                            });

                            rList.setAdapter(adapter2);
                            adapter.notifyItemRangeChanged(0, 8);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());

            }
        });
        mRequestQueue.add(request);
    }

    private void getRandomData() {
        recetaLista.clear();
        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, random, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for(int i = 0; i < jsonArray.length() ; i++){
                                JSONObject receta = jsonArray.getJSONObject(i);
                                String nombreReceta = receta.getString("title");
                                String imagenUrl = receta.getString("image");

                                int tiempo = receta.getInt("readyInMinutes");
                                int porciones = receta.getInt("servings");
                                String url = receta.getString("sourceUrl");

                                Receta r2 = new Receta(imagenUrl, nombreReceta, tiempo, porciones, url);
                                    recetaLista.add(r2);

                            }

                            adapter2 = new RecetasFiltrarAdapter(recetaLista, getActivity().getApplicationContext(), new RecetasFiltrarAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Receta receta, int position) {
                                    callback.onChange(receta);
                                }
                            });

                            rList.setAdapter(adapter2);
                            adapter.notifyItemRangeChanged(0, 8);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());

            }
        });
        mRequestQueue.add(request);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnRecetaSelected) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public interface OnRecetaSelected {
        public void onChange(Receta mail);
    }


}
