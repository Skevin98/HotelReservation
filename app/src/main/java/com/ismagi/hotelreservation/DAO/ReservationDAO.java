package com.ismagi.hotelreservation.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ismagi.hotelreservation.Models.Reservation;
import com.ismagi.hotelreservation.Models.User;
import com.ismagi.hotelreservation.Utils.HttpHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationDAO implements IReservationDAO {

    String url = HttpHelper.GetUrl()+"/Reservation";
    private String TAG = "ReservationDAO";
    private Context context;

    public ReservationDAO(Context context) {
        this.context = context;
    }

    @Override
    public void Add(Reservation obj) {
        JSONObject json = new JSONObject();
        try {
            json.put("id", obj.getId());
            json.put("userID", obj.getIdUser());
            //json.put("user", obj.getUser());
            json.put("chambreId", obj.getIdChambre());
            //json.put("chambre", obj.getChambre());

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String DATE = sdf.format(c.getTime());

            json.put("DateReservation", DATE);
            json.put("DateEntree", obj.getDateEntree());
            json.put("DateSortie",obj.getDateSortie());
            json.put("IsActive",obj.isActive());
            json.put("montant",obj.getMontant());
            json.put("nb_Enfants", obj.getNbEnfant());
            json.put("nb_Adults",obj.getNbAdult());


            RequestQueue requestQueue = Volley.newRequestQueue(context);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i(TAG, "onResponseAdd: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error == null || error.networkResponse == null) {
                        Log.i(TAG, "onErrorResponseAdd: "+error.toString());
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
                    Log.i(TAG, "onErrorResponseAdd: "+body);
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
    public void GetById(String id, VolleyCallback callback) {
        Reservation r = new Reservation();
        String GetUrl = url+"/"+id;

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GetUrl,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject resp) {

                try {

                    r.setId(resp.getString("id"));
                    r.setIdUser(resp.getString("userID"));
                    r.setIdChambre(resp.getString("chambreId"));
                    r.setDateReservation(resp.getString("dateReservation"));
                    r.setDateEntree(resp.getString("dateEntree"));
                    r.setDateSortie(resp.getString("dateSortie"));
                    r.setActive(resp.getBoolean("isActive"));
                    r.setMontant(resp.getDouble("montant"));
                    r.setNbAdult(resp.getInt("nb_Adults"));
                    r.setNbEnfant(resp.getInt("nb_Enfants"));
                    Log.i(TAG, "onResponseById: Requete envoyée "+r.getIdUser());
                    callback.onSuccess(r);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null || error.networkResponse == null) {
                    Log.i(TAG, "onErrorResponseById: "+error);
                    callback.onError(error.toString());
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
                    Log.i(TAG, "onErrorResponseById: "+body);
                    callback.onError(error.toString());
                }


            }
        }){
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("idPersonnne",id);
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

        //return null;
    }

    @Override
    public void Update(Reservation obj, String id) {
        String GetUrl = url+"/"+id;
        JSONObject json = new JSONObject();
        try {
            json.put("id", obj.getId());
            json.put("userID", obj.getIdUser());
            //json.put("user", obj.getUser());
            json.put("chambreId", obj.getIdChambre());
            //json.put("chambre", obj.getChambre());
            json.put("DateReservation", obj.getDateReservation());
            json.put("DateEntree", obj.getDateEntree());
            json.put("DateSortie",obj.getDateSortie());
            json.put("IsActive",obj.isActive());
            json.put("montant",obj.getMontant());
            json.put("nb_Enfants", obj.getNbEnfant());
            json.put("nb_Adults",obj.getNbAdult());

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, GetUrl,json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i(TAG, "onResponseUpdate: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error == null || error.networkResponse == null) {
                        Log.i(TAG, "onErrorResponseUpdate: "+error);
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
                    Log.i(TAG, "onErrorResponseUpdate: "+body);
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
    public void Delete(String id) {
        String GetUrl = url+"/"+id;


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.DELETE, GetUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponseDelete: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null || error.networkResponse == null) {
                    Log.i(TAG, "onErrorResponseDelete: "+error);
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
                Log.i(TAG, "onErrorResponseDelete: "+body);
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

    }

    @Override
    public void GetAll(VolleyCallback callback) {
        List<Reservation> list = new ArrayList<>();


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                try {
                    Log.i(TAG, "Reponse : "+resp.length());
                    for (int i = 0; i < resp.length(); i++){
                        Reservation r = new Reservation();
                        JSONObject json = resp.getJSONObject(i);
                        //Log.i(TAG, "Reponse : "+json.getString("age"));
                        r.setId(json.getString("id"));
                        r.setIdUser(json.getString("userID"));
                        r.setIdChambre(json.getString("chambreId"));
                        r.setDateReservation(json.getString("dateReservation"));
                        r.setDateEntree(json.getString("dateEntree"));
                        r.setDateSortie(json.getString("dateSortie"));
                        r.setActive(json.getBoolean("isActive"));
                        r.setMontant(json.getDouble("montant"));
                        r.setNbAdult(json.getInt("nb_Adults"));
                        r.setNbEnfant(json.getInt("nb_Enfants"));
                        list.add(r);

                        Log.i(TAG, "Element : "+i+" : "+r.getId());

                    }
                    //Log.i(TAG, "DAO: List des users "+ list.get(0).getMail());
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
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };


        requestQueue.add(request);
    }

    @Override
    public void GetReservationByUser(String idUser, VolleyCallback callback) {

        //A implementer dans l'ASP
    }
}
