package com.ismagi.hotelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ismagi.hotelreservation.databinding.ActivityConnexionBinding;

public class ConnexionActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityConnexionBinding acb;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //profil de test : sawadogokevin98@gmail.com

        auth = FirebaseAuth.getInstance();

        acb = ActivityConnexionBinding.inflate(getLayoutInflater());
        setContentView(acb.getRoot());

        acb.txtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                acb.btnCon.setEnabled(true);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        acb.btnCon.setOnClickListener(this);
        acb.btnCreateUser.setOnClickListener(this);
        acb.forgotPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_con:
                String txt_mail = acb.txtMail.getText().toString().trim();
                String txt_pass = acb.txtMdp.getText().toString().trim();

                if(TextUtils.isEmpty(txt_mail) || TextUtils.isEmpty(txt_pass)){

                    Toast.makeText(ConnexionActivity.this,"Champs vides",Toast.LENGTH_SHORT).show();
                }
                else{

                    auth.signInWithEmailAndPassword(txt_mail,txt_pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Intent activit = new Intent(getApplicationContext(), MenuActivity.class);
                                        getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(activit);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(ConnexionActivity.this,"Connexion impossible",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                break;
            case R.id.btn_create_user:
                Intent Create_User = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(Create_User);
                break;
            case R.id.forgot_password:
                Intent new_pass = new Intent(getApplicationContext(),ResetPasswordActivity.class);
                startActivity(new_pass);
                break;
        }

    }
}