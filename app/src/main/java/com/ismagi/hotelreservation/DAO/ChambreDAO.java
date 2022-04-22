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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ChambreDAO implements IChambreDao {
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
    public Chambre GetById(String id) {
        Chambre c = new Chambre();
        Categorie cat = new Categorie();
        String GetUrl = url+"/"+id;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GetUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject resp) {
                try {

                    c.setId(resp.getString("Id"));
                    c.setNumEtage(resp.getInt("NumEtage"));
                    c.setNbLits(resp.getInt("NbLits"));

                    JSONObject temp = resp.getJSONObject("Categorie");
                    cat.setIdCategorie(temp.getString("IdCategorie"));
                    cat.setLibelle(temp.getString("Libelle"));
                    cat.setTarif(temp.getDouble("Tarif"));
                    cat.setDescription(temp.getString("Description"));
                    c.setCategorie(cat);

                    c.setAvailable(resp.getBoolean("IsAvailable"));
                    c.setHasBalcon(resp.getBoolean("HasBalcon"));
                    c.setHasVue_sur_mer(resp.getBoolean("HasVue_sur_mer"));
                    c.setHasSalle_sejour(resp.getBoolean("HasSalle_sejour"));
                    c.setHasCuisine(resp.getBoolean("HasCuisine"));
                    Log.i(TAG, "onResponse: Requete envoyée "+c.getId());

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
        return c;

    }

    @Override
    public void Update(Chambre obj) {

    }

    @Override
    public void Delete(Chambre obj) {

    }

    @Override
    public List<Chambre> GetAll() {
        List<Chambre> list = null;


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                try {
                    for (int i = 0; i < resp.length(); i++){
                        Chambre c = new Chambre();
                        Categorie cat = new Categorie();
                        JSONObject json = resp.getJSONObject(i);
                        c.setId(json.getString("Id"));
                        c.setNumEtage(json.getInt("NumEtage"));
                        c.setNbLits(json.getInt("NbLits"));

                        JSONObject temp = json.getJSONObject("Categorie");
                        cat.setIdCategorie(temp.getString("IdCategorie"));
                        cat.setLibelle(temp.getString("Libelle"));
                        cat.setTarif(temp.getDouble("Tarif"));
                        cat.setDescription(temp.getString("Description"));
                        c.setCategorie(cat);

                        c.setAvailable(json.getBoolean("IsAvailable"));
                        c.setHasBalcon(json.getBoolean("HasBalcon"));
                        c.setHasVue_sur_mer(json.getBoolean("HasVue_sur_mer"));
                        c.setHasSalle_sejour(json.getBoolean("HasSalle_sejour"));
                        c.setHasCuisine(json.getBoolean("HasCuisine"));
                        Log.i(TAG, "onResponse: Requete envoyée "+c.getId());

                        list.add(c);

                    }


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

        return list;
    }

    @Override
    public List<Chambre> GetChambreByCategorie(String IdCategorie) {
        String GetUrl = url+"/ChambreByCategorie/"+IdCategorie;
        List<Chambre> list = null;


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                try {
                    for (int i = 0; i < resp.length(); i++){
                        Chambre c = new Chambre();
                        Categorie cat = new Categorie();
                        JSONObject json = resp.getJSONObject(i);
                        c.setId(json.getString("Id"));
                        c.setNumEtage(json.getInt("NumEtage"));
                        c.setNbLits(json.getInt("NbLits"));

                        JSONObject temp = json.getJSONObject("Categorie");
                        cat.setIdCategorie(temp.getString("IdCategorie"));
                        cat.setLibelle(temp.getString("Libelle"));
                        cat.setTarif(temp.getDouble("Tarif"));
                        cat.setDescription(temp.getString("Description"));
                        c.setCategorie(cat);

                        c.setAvailable(json.getBoolean("IsAvailable"));
                        c.setHasBalcon(json.getBoolean("HasBalcon"));
                        c.setHasVue_sur_mer(json.getBoolean("HasVue_sur_mer"));
                        c.setHasSalle_sejour(json.getBoolean("HasSalle_sejour"));
                        c.setHasCuisine(json.getBoolean("HasCuisine"));
                        Log.i(TAG, "onResponse: Requete envoyée "+c.getId());

                        list.add(c);

                    }


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
