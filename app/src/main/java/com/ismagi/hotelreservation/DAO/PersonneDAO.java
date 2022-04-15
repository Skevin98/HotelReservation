package com.ismagi.hotelreservation.DAO;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ismagi.hotelreservation.Models.Personne;
import com.ismagi.hotelreservation.Utils.HttpHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.valueOf;

public class PersonneDAO implements IDao<Personne>  {

    String url = HttpHelper.GetUrl()+"/Personne";
    private String TAG = "PersonneDAO";
    private Context context;

    public PersonneDAO(Context context) {
        this.context = context;
    }

    @Override
    public void Add(Personne obj) {
        JSONObject json = new JSONObject();
        try {
            json.put("idPersonne", obj.getId());
            json.put("idFirebasePersonne", obj.getFirebaseId());
            json.put("nom", obj.getNom());
            json.put("adresse",obj.getAdresse());
            json.put("prenom", obj.getPrenom());
            json.put("email", obj.getMail());
            json.put("telephone", obj.getNumero());
            json.put("mot_de_passe", obj.getMdp());
            json.put("age", obj.getAge());
            json.put("sexe", obj.getSexe());
            json.put("privilege", "User");

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i(TAG, "onResponse: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error == null || error.networkResponse == null) {
                        return;
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

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Personne GetById(String id) {
        String GetUrl =url+"/"+id;
        Personne p = new Personne();

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject resp) {

                try {
                    p.setFirebaseId(resp.getString("idFirebasePersonne"));
                    p.setSexe(resp.getString("sexe"));
                    p.setAge(resp.getInt("age"));
                    p.setNumero(resp.getString("telephone"));
                    p.setMdp(resp.getString("mot_de_passe"));
                    p.setNom(resp.getString("nom"));
                    p.setPrenom(resp.getString("prenom"));
                    p.setAdresse(resp.getString("adresse"));
                    p.setId(resp.getString("idPersonne"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null || error.networkResponse == null) {
                    return;
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

        return p;

    }

    @Override
    public void Update(Personne obj) {
        JSONObject json = new JSONObject();
        try {
            json.put("idPersonne", obj.getId());
            json.put("idFirebasePersonne", obj.getFirebaseId());
            json.put("nom", obj.getNom());
            json.put("adresse",obj.getAdresse());
            json.put("prenom", obj.getPrenom());
            json.put("email", obj.getMail());
            json.put("telephone", obj.getNumero());
            json.put("mot_de_passe", obj.getMdp());
            json.put("age", obj.getAge());
            json.put("sexe", obj.getSexe());
            json.put("privilege", "User");

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url,json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i(TAG, "onResponse: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error == null || error.networkResponse == null) {
                        return;
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

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void Delete(Personne obj) {
        JSONObject json = new JSONObject();
        try {
            json.put("idPersonne", obj.getId());
            json.put("idFirebasePersonne", obj.getFirebaseId());
            json.put("nom", obj.getNom());
            json.put("adresse",obj.getAdresse());
            json.put("prenom", obj.getPrenom());
            json.put("email", obj.getMail());
            json.put("telephone", obj.getNumero());
            json.put("mot_de_passe", obj.getMdp());
            json.put("age", obj.getAge());
            json.put("sexe", obj.getSexe());
            json.put("privilege", "User");

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url,json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i(TAG, "onResponse: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error == null || error.networkResponse == null) {
                        return;
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

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Personne> GetAll() {
        List<Personne> list = null;


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject resp) {
                try {
                    JSONArray array = resp.getJSONArray("Personne");
                    for (int i = 0; i < array.length(); i++){
                        Personne p = new Personne();
                        p.setFirebaseId(resp.getString("idFirebasePersonne"));
                        p.setSexe(resp.getString("sexe"));
                        p.setAge(resp.getInt("age"));
                        p.setNumero(resp.getString("telephone"));
                        p.setMdp(resp.getString("mot_de_passe"));
                        p.setNom(resp.getString("nom"));
                        p.setPrenom(resp.getString("prenom"));
                        p.setAdresse(resp.getString("adresse"));
                        p.setId(resp.getString("idPersonne"));

                        list.add(p);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null || error.networkResponse == null) {
                    return;
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
