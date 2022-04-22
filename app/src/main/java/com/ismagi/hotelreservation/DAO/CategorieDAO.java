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
import com.ismagi.hotelreservation.Utils.HttpHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CategorieDAO implements IDao<Categorie> {

    String url = HttpHelper.GetUrl()+"/User";
    private String TAG = "CategorieDAO";
    private Context context;

    @Override
    public void Add(Categorie obj) {

    }

    @Override
    public Categorie GetById(String id) {
        Categorie c = new Categorie();
        String GetUrl = url+"/"+id;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GetUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject resp) {
                try {
                    c.setIdCategorie(resp.getString("idCategorie"));
                    c.setLibelle(resp.getString("libelle"));
                    c.setTarif(resp.getDouble("tarif"));
                    c.setDescription(resp.getString("description"));
                    Log.i(TAG, "onResponse: Requete envoyée "+c.getLibelle());

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
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("idCategorie",id);
                return params;
            }

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
    public void Update(Categorie obj) {

    }

    @Override
    public void Delete(Categorie obj) {

    }

    @Override
    public List<Categorie> GetAll() {
        List<Categorie> list = null;


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                try {
                    for (int i = 0; i < resp.length(); i++){
                        Categorie c = new Categorie();
                        JSONObject json = resp.getJSONObject(i);
                        c.setIdCategorie(json.getString("idCategorie"));
                        c.setLibelle(json.getString("libelle"));
                        c.setTarif(json.getDouble("tarif"));
                        c.setDescription(json.getString("description"));
                        Log.i(TAG, "onResponse: Requete envoyée "+c.getLibelle());

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
}
