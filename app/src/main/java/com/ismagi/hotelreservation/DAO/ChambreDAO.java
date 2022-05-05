package com.ismagi.hotelreservation.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ismagi.hotelreservation.Models.Categorie;
import com.ismagi.hotelreservation.Models.Chambre;
import com.ismagi.hotelreservation.Utils.HttpHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChambreDAO implements IChambreDao {
    String url = HttpHelper.GetUrl()+"/Chambre";
    private String TAG = "ChambreDAO";
    private Context context;

    public ChambreDAO(Context context) {
        this.context = context;
    }

    @Override
    public void Add(Chambre obj) {

    }

    @Override
    public void GetById(String id, VolleyCallback callback) {
        Chambre c = new Chambre();
        Categorie cat = new Categorie();
        String GetUrl = url+"/"+id;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GetUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject resp) {
                try {
                    //Log.i(TAG, "Type of categorie: "+resp);
                    c.setId(resp.getString("id"));
                    c.setNumEtage(resp.getInt("numEtage"));
                    c.setNbLits(resp.getInt("nbLits"));
                    c.setNumero(resp.getInt("numero"));

                    JSONObject temp = resp.getJSONObject("categorie");
                    cat.setIdCategorie(temp.getString("id"));
                    cat.setLibelle(temp.getString("libelle"));
                    cat.setTarif(temp.getDouble("tarif"));
                    cat.setDescription(temp.getString("description"));
                    c.setCategorie(cat);

                    c.setAvailable(resp.getBoolean("isAvailable"));
                    c.setHasBalcon(resp.getBoolean("hasBalcon"));
                    c.setHasVue_sur_mer(resp.getBoolean("hasVue_sur_mer"));
                    c.setHasSalle_sejour(resp.getBoolean("hasSalle_sejour"));
                    c.setHasCuisine(resp.getBoolean("hasCuisine"));
                    Log.i(TAG, "onResponse: Requete envoyée "+c.getId());

                    callback.onSuccess(c);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null || error.networkResponse == null) {
                    Log.i(TAG, "onErrorResponse: "+error);
                }
                else{
                    String body="";
                    //get status code here
                    final String statusCode = String.valueOf(error.networkResponse.statusCode);
                    //get response body and parse with appropriate encoding
                    try {
                        body = new String(error.networkResponse.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        // exception
                    }
                    Log.i(TAG, "onErrorResponse: "+body);
                }

            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        requestQueue.add(request);
        //return c;

    }

    @Override
    public void Update(Chambre obj, String id) {

    }

    @Override
    public void Delete(String id) {

    }

    @Override
    public void GetAll(VolleyCallback callback) {
        List<Chambre> list = new ArrayList<>();


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                try {
                    for (int i = 0; i < resp.length(); i++){
                        Chambre c = new Chambre();
                        Categorie cat = new Categorie();
                        JSONObject json = resp.getJSONObject(i);
                        c.setId(json.getString("id"));
                        c.setNumEtage(json.getInt("numEtage"));
                        c.setNbLits(json.getInt("nbLits"));
                        c.setNumero(json.getInt("numero"));

                        JSONObject temp = json.getJSONObject("categorie");
                        cat.setIdCategorie(temp.getString("id"));
                        cat.setLibelle(temp.getString("libelle"));
                        cat.setTarif(temp.getDouble("tarif"));
                        cat.setDescription(temp.getString("description"));
                        c.setCategorie(cat);

                        c.setAvailable(json.getBoolean("isAvailable"));
                        c.setHasBalcon(json.getBoolean("hasBalcon"));
                        c.setHasVue_sur_mer(json.getBoolean("hasVue_sur_mer"));
                        c.setHasSalle_sejour(json.getBoolean("hasSalle_sejour"));
                        c.setHasCuisine(json.getBoolean("hasCuisine"));
                        Log.i(TAG, "onResponse: Requete envoyée "+c.getId());

                        list.add(c);

                    }

                    callback.onSuccess(list);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null || error.networkResponse == null) {
                    Log.i(TAG, "onErrorResponse: "+error);
                }

                String body="";
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    body = new String(error.networkResponse.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // exception
                }
                Log.i(TAG, "onErrorResponse: "+body);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };


        requestQueue.add(request);

        //return list;
    }

    @Override
    public List<Chambre> GetChambreByCategorie(String IdCategorie, VolleyCallback callback) {
        String GetUrl = url+"/byCategorie/"+IdCategorie;
        List<Chambre> list = new ArrayList<>();


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, GetUrl,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                try {
                    for (int i = 0; i < resp.length(); i++){
                        Chambre c = new Chambre();
                        Categorie cat = new Categorie();
                        JSONObject json = resp.getJSONObject(i);
                        c.setId(json.getString("id"));
                        c.setNumEtage(json.getInt("numEtage"));
                        c.setNbLits(json.getInt("nbLits"));

                        /*JSONObject temp = json.getJSONObject("categorie");
                        cat.setIdCategorie(temp.getString("id"));
                        cat.setLibelle(temp.getString("libelle"));
                        cat.setTarif(temp.getDouble("tarif"));
                        cat.setDescription(temp.getString("description"));
                        c.setCategorie(cat);*/

                        c.setAvailable(json.getBoolean("isAvailable"));
                        c.setHasBalcon(json.getBoolean("hasBalcon"));
                        c.setHasVue_sur_mer(json.getBoolean("hasVue_sur_mer"));
                        c.setHasSalle_sejour(json.getBoolean("hasSalle_sejour"));
                        c.setHasCuisine(json.getBoolean("hasCuisine"));
                        Log.i(TAG, "onResponse: Requete envoyée "+c.getId());

                        list.add(c);

                    }
                    callback.onSuccess(list);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null || error.networkResponse == null) {
                    Log.i(TAG, "onErrorResponse: "+error);
                    callback.onError(error.toString());
                }

                String body="";
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    body = new String(error.networkResponse.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // exception
                }
                Log.i(TAG, "onErrorResponse: "+body);
                callback.onError(error.toString());
            }
        })
        {



            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };


        requestQueue.add(request);

        return list;
    }
}
