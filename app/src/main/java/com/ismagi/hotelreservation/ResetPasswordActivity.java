package com.ismagi.hotelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.ismagi.hotelreservation.databinding.ActivityMenuBinding;
import com.ismagi.hotelreservation.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityResetPasswordBinding arpb;
    FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arpb = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(arpb.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        arpb.btnReset.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        String mail = arpb.txtMailReset.getText().toString();
        if(mail.equals("") && view.getId()==R.id.btn_reset){
            Toast.makeText(ResetPasswordActivity.this, "Champ requis", Toast.LENGTH_SHORT).show();
        }
        else{
            firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ResetPasswordActivity.this, "Email de reinitialisation envoyé. Veuillez Verifier votre adresse mail",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ResetPasswordActivity.this, ConnexionActivity.class));
                    }
                    else{
                        String error = task.getException().getMessage();
                        Toast.makeText(ResetPasswordActivity.this, error,Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}