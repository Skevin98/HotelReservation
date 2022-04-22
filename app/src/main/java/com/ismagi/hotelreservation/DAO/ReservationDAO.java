package com.ismagi.hotelreservation.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ismagi.hotelreservation.Models.Reservation;
import com.ismagi.hotelreservation.Utils.HttpHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationDAO implements IDao<Reservation> {

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
            json.put("IdReservation", obj.getIdReservation());
            json.put("Chambres", obj.getChambres());
            json.put("DateReservation", obj.getDateReservation());
            json.put("DateEntree", obj.getDateEntree());
            json.put("DateSortie",obj.getDateSortie());
            json.put("IsActive",obj.isActive());
            json.put("Invites", obj.getInvites());
            json.put("Montant",obj.getMontant());

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
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
    public Reservation GetById(String id) {
        Reservation r = new Reservation();
        String GetUrl = url+"/"+id;

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GetUrl,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject resp) {

                try {

                    r.setIdReservation(resp.getString("idReservation"));
                    r.se
                    Log.i(TAG, "onResponse: Requete envoyée "+p.getNom());
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

        return p;
    }

    @Override
    public void Update(Reservation obj) {

    }

    @Override
    public void Delete(Reservation obj) {

    }

    @Override
    public List<Reservation> GetAll() {
        return null;
    }
}
