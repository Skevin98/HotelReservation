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
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ismagi.hotelreservation.Models.User;
import com.ismagi.hotelreservation.Utils.HttpHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.Integer.valueOf;

public class PersonneDAO implements IDao<User>  {

    String url = HttpHelper.GetUrl()+"/User";
    private String TAG = "PersonneDAO";
    private Context context;

    public PersonneDAO(Context context) {
        this.context = context;
    }

    @Override
    public void Add(User obj) {
        JSONObject json = new JSONObject();
        try {
            json.put("id", obj.getId());
            json.put("idFirebaseUser", obj.getFirebaseId());
            json.put("nom", obj.getNom());
            json.put("adresse",obj.getAdresse());
            json.put("prenom", obj.getPrenom());
            json.put("email", obj.getMail());
            json.put("telephone", obj.getNumero());
            json.put("password", obj.getMdp());
            json.put("age", obj.getAge());
            json.put("sexe", obj.getSexe());
            json.put("username", obj.getUsername());
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
    public void GetById(String id, VolleyCallback callback) {
        User p = new User();
        String GetUrl = url+"/"+id;

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GetUrl,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject resp) {

                try {

                    p.setFirebaseId(resp.getString("idFirebaseUser"));
                    p.setSexe(resp.getString("sexe"));
                    p.setAge(resp.getInt("age"));
                    p.setNumero(resp.getString("telephone"));
                    p.setMdp(resp.getString("password"));
                    p.setNom(resp.getString("nom"));
                    p.setPrenom(resp.getString("prenom"));
                    p.setAdresse(resp.getString("adresse"));
                    p.setId(resp.getString("id"));
                    p.setUsername(resp.getString("username"));
                    p.setMail(resp.getString("email"));
                    p.setPrivilege(resp.getString("privilege"));
                    Log.i(TAG, "onResponse: Requete envoyée "+p.getNom());

                    callback.onSuccess(p);
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
                    callback.onError(error.toString());
                }


            }
        }){
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("id",id);
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

        //return p;

    }

    @Override
    public void Update(User obj, String id) {
        String GetUrl = url+"/"+id;
        JSONObject json = new JSONObject();
        try {
            json.put("id", obj.getId());
            json.put("idFirebaseUser", obj.getFirebaseId());
            json.put("username", obj.getUsername());
            json.put("nom", obj.getNom());
            json.put("adresse",obj.getAdresse());
            json.put("prenom", obj.getPrenom());
            json.put("email", obj.getMail());
            json.put("telephone", obj.getNumero());
            json.put("password", obj.getMdp());
            json.put("age", obj.getAge());
            json.put("sexe", obj.getSexe());
            json.put("privilege", "User");

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
    public void GetAll(final VolleyCallback callback) {
        List<User> list = new ArrayList<>();


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                try {
                    Log.i(TAG, "Reponse : "+resp.length());
                    for (int i = 0; i < resp.length(); i++){
                        User p = new User();
                        JSONObject json = resp.getJSONObject(i);
                        //Log.i(TAG, "Reponse : "+json.getString("age"));
                        p.setFirebaseId(json.getString("idFirebaseUser"));
                        p.setSexe(json.getString("sexe"));
                        p.setAge(json.getInt("age"));
                        p.setNumero(json.getString("telephone"));
                        p.setMdp(json.getString("password"));
                        p.setNom(json.getString("nom"));
                        p.setUsername(json.getString("username"));
                        p.setPrenom(json.getString("prenom"));
                        p.setAdresse(json.getString("adresse"));
                        p.setId(json.getString("id"));
                        p.setMail(json.getString("email"));
                        p.setPrivilege(json.getString("privilege"));
                        list.add(p);

                        Log.i(TAG, "Element : "+i+" : "+p.getNom());

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



        //Log.i(TAG, "Longueur de la liste : "+list.size());
    }


    /*public User GetById2(String id){
        User p = new User();
        String GetUrl = url+"/"+id;

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GetUrl, null, requestFuture, new Response.ErrorListener() {
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
        });
        requestQueue.add(request);

        try {
            JSONObject resp = requestFuture.get(30, TimeUnit.SECONDS);
            p.setFirebaseId(resp.getString("idFirebaseUser"));
            p.setSexe(resp.getString("sexe"));
            p.setAge(resp.getInt("age"));
            p.setNumero(resp.getString("telephone"));
            p.setMdp(resp.getString("password"));
            p.setNom(resp.getString("nom"));
            p.setPrenom(resp.getString("prenom"));
            p.setAdresse(resp.getString("adresse"));
            p.setId(resp.getString("id"));
            p.setUsername(resp.getString("username"));
            p.setMail(resp.getString("email"));
            p.setMail(resp.getString("privilege"));
            Log.i(TAG, "onResponse: Requete envoyée "+p.getNom());


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return p;
    }*/
}
