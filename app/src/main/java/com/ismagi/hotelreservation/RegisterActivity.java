package com.ismagi.hotelreservation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ismagi.hotelreservation.DAO.PersonneDAO;
import com.ismagi.hotelreservation.Models.Personne;
import com.ismagi.hotelreservation.databinding.ActivityRegisterBinding;
import com.ismagi.hotelreservation.databinding.ActivityResetPasswordBinding;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.Integer.valueOf;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityRegisterBinding arb;

    PersonneDAO DAO;

    final String TAG = "RegisterActivity";


    Personne p;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-zA-Z])" +
                    "(?=\\S+$)" +
                    ".{8,}" +
                    "$");

    private static final Pattern NUMABON_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=\\S+$)" +
                    ".{14}" +
                    "$");

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arb = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(arb.getRoot());

        mAuth = FirebaseAuth.getInstance();

        DAO = new PersonneDAO(this);

        arb.btnCreate.setOnClickListener(this);

    }


    public void register(Personne p){
        mAuth.createUserWithEmailAndPassword(p.getMail(), p.getMdp())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            FirebaseUser user = mAuth.getCurrentUser();
                            p.setFirebaseId(user.getUid());
                            try {
                                DAO.Add(p);
                                Intent activit = new Intent(getApplicationContext(), MenuActivity.class);
                                Log.i("RegisterActivity", "Compte créé avec succès");
                                getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(activit);

                            } finally {
                                finish();
                            }
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("RegisterActivity", "Creation échouée.", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }






    @Override
    public void onClick(View view) {
        p = new Personne();
        p.setNom(arb.txtNom.getText().toString());
        p.setPrenom(arb.txtPrenom.getText().toString());
        p.setAdresse(arb.txtAdresse.getText().toString());
        p.setAge(valueOf(arb.txtAge.getText().toString()));
        p.setMail(arb.txtMailRegister.getText().toString());
        p.setNumero(arb.txtPhone.getText().toString());
        p.setMdp(arb.txtMdpRegister.getText().toString());
        p.setSexe("Masculin");
        String confirm = arb.txtMdpConfirm.getText().toString();
        register(p);



    }
}