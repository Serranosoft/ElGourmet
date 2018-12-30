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
import android.widget.Toast;

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

    private Button back;


    private static final String api = "https://www.themealdb.com/api/json/v1/1/latest.php";
    private static final String random = "https://www.themealdb.com/api/json/v1/1/random.php";

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
        back.setVisibility(View.INVISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
                back.setVisibility(View.INVISIBLE);
                floating.show();
            }
        });

        floatingRandom = view.findViewById(R.id.random);
        floatingRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getRandomData();
                back.setVisibility(View.VISIBLE);
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

        final EditText ingr = view.findViewById(R.id.ingrediente);
        Button botonFiltrar = view.findViewById(R.id.popupFiltrar);
        Button mostrarTodo = view.findViewById(R.id.mostrarTodo);
        botonFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getDataFilter(ingr.getText().toString());

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
                            JSONArray jsonArray = response.getJSONArray("meals");

                            for(int i = 0; i < jsonArray.length() ; i++){
                                JSONObject receta = jsonArray.getJSONObject(i);
                                String[] ingredientes = new String[20];
                                String nombreReceta = receta.getString("strMeal");
                                String imagenUrl = receta.getString("strMealThumb");

                                ingredientes[0] = receta.getString("strIngredient1");
                                ingredientes[1] = receta.getString("strIngredient2");
                                ingredientes[2] = receta.getString("strIngredient3");
                                ingredientes[3] = receta.getString("strIngredient4");
                                ingredientes[4] = receta.getString("strIngredient5");
                                ingredientes[5] = receta.getString("strIngredient6");
                                ingredientes[6] = receta.getString("strIngredient7");
                                ingredientes[7] = receta.getString("strIngredient8");
                                ingredientes[8] = receta.getString("strIngredient9");
                                ingredientes[9] = receta.getString("strIngredient10");
                                ingredientes[10] = receta.getString("strIngredient11");
                                ingredientes[11] = receta.getString("strIngredient12");
                                ingredientes[12] = receta.getString("strIngredient13");
                                ingredientes[13] = receta.getString("strIngredient14");
                                ingredientes[14] = receta.getString("strIngredient15");
                                ingredientes[15] = receta.getString("strIngredient16");
                                ingredientes[16] = receta.getString("strIngredient17");
                                ingredientes[17] = receta.getString("strIngredient18");
                                ingredientes[18] = receta.getString("strIngredient19");
                                ingredientes[19] = receta.getString("strIngredient20");

                                String elaboracion = receta.getString("strInstructions");

                                String url = receta.getString("strSource");

                                Receta r2 = new Receta(imagenUrl, nombreReceta, elaboracion, ingredientes, url);
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

    private void getDataFilter(final String ingrediente) {
        recetaLista.clear();
        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, api, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("meals");

                            for(int i = 0; i < jsonArray.length() ; i++){
                                JSONObject receta = jsonArray.getJSONObject(i);
                                String[] ingredientes = new String[20];
                                String nombreReceta = receta.getString("strMeal");
                                String imagenUrl = receta.getString("strMealThumb");

                                ingredientes[0] = receta.getString("strIngredient1");
                                ingredientes[1] = receta.getString("strIngredient2");
                                ingredientes[2] = receta.getString("strIngredient3");
                                ingredientes[3] = receta.getString("strIngredient4");
                                ingredientes[4] = receta.getString("strIngredient5");
                                ingredientes[5] = receta.getString("strIngredient6");
                                ingredientes[6] = receta.getString("strIngredient7");
                                ingredientes[7] = receta.getString("strIngredient8");
                                ingredientes[8] = receta.getString("strIngredient9");
                                ingredientes[9] = receta.getString("strIngredient10");
                                ingredientes[10] = receta.getString("strIngredient11");
                                ingredientes[11] = receta.getString("strIngredient12");
                                ingredientes[12] = receta.getString("strIngredient13");
                                ingredientes[13] = receta.getString("strIngredient14");
                                ingredientes[14] = receta.getString("strIngredient15");
                                ingredientes[15] = receta.getString("strIngredient16");
                                ingredientes[16] = receta.getString("strIngredient17");
                                ingredientes[17] = receta.getString("strIngredient18");
                                ingredientes[18] = receta.getString("strIngredient19");
                                ingredientes[19] = receta.getString("strIngredient20");

                                String elaboracion = receta.getString("strInstructions");

                                String url = receta.getString("strSource");

                                boolean filtrar = false;
                                for(int j=0;j<20;j++){
                                    if(ingredientes[j].equals(ingrediente)){
                                        filtrar = true;
                                        break;
                                    }else{
                                        filtrar = false;
                                    }

                                }

                                Receta r2 = new Receta(imagenUrl, nombreReceta, elaboracion, ingredientes, url);
                                if(filtrar){
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
                            JSONArray jsonArray = response.getJSONArray("meals");

                            for(int i = 0; i < jsonArray.length() ; i++){
                                JSONObject receta = jsonArray.getJSONObject(i);
                                String[] ingredientes = new String[20];
                                String nombreReceta = receta.getString("strMeal");
                                String imagenUrl = receta.getString("strMealThumb");

                                ingredientes[0] = receta.getString("strIngredient1");
                                ingredientes[1] = receta.getString("strIngredient2");
                                ingredientes[2] = receta.getString("strIngredient3");
                                ingredientes[3] = receta.getString("strIngredient4");
                                ingredientes[4] = receta.getString("strIngredient5");
                                ingredientes[5] = receta.getString("strIngredient6");
                                ingredientes[6] = receta.getString("strIngredient7");
                                ingredientes[7] = receta.getString("strIngredient8");
                                ingredientes[8] = receta.getString("strIngredient9");
                                ingredientes[9] = receta.getString("strIngredient10");
                                ingredientes[10] = receta.getString("strIngredient11");
                                ingredientes[11] = receta.getString("strIngredient12");
                                ingredientes[12] = receta.getString("strIngredient13");
                                ingredientes[13] = receta.getString("strIngredient14");
                                ingredientes[14] = receta.getString("strIngredient15");
                                ingredientes[15] = receta.getString("strIngredient16");
                                ingredientes[16] = receta.getString("strIngredient17");
                                ingredientes[17] = receta.getString("strIngredient18");
                                ingredientes[18] = receta.getString("strIngredient19");
                                ingredientes[19] = receta.getString("strIngredient20");

                                String elaboracion = receta.getString("strInstructions");

                                String url = receta.getString("strSource");

                                Receta r2 = new Receta(imagenUrl, nombreReceta, elaboracion, ingredientes, url);
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
