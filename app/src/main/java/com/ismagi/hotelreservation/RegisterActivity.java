package com.ismagi.hotelreservation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ismagi.hotelreservation.DAO.IDao;
import com.ismagi.hotelreservation.DAO.PersonneDAO;
import com.ismagi.hotelreservation.Models.User;
import com.ismagi.hotelreservation.databinding.ActivityRegisterBinding;

import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityRegisterBinding arb;

    private RadioButton radioButton;

    IDao<User> DAO;

    final String TAG = "RegisterActivity";


    User p;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$");

    private static final Pattern NUMABON_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=\\S+$).{14}$");

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arb = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(arb.getRoot());

        mAuth = FirebaseAuth.getInstance();

        DAO = new PersonneDAO(this);

        arb.btnCreate.setOnClickListener(this);

        arb.pickerAge.setMinValue(0);
        arb.pickerAge.setMaxValue(200);

        //p = new User();
        //p = DAO.GetById("1e3ebf4f-108d-45b5-ad93-868b4e29ce45");


    }


    public void register(User p){
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
                                activit.putExtra("idUser",p.getId());
                                Log.i("RegisterActivity", "Compte créé avec succès");
                                getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(activit);

                            } finally {
                                finish();
                            }

                        } else {

                            Log.w("RegisterActivity", "Creation échouée.", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    private boolean validateEmail(){
        String emailInput = arb.txtMailRegister.getEditableText().toString().trim();

        if (emailInput.isEmpty()) {
            arb.textLayoutMail.setError("Champ Obligatoire");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            arb.textLayoutMail.setError("Adresse mail invalide");
            return false;
        }
        else{
            arb.textLayoutMail.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordInput = arb.txtMdpRegister.getEditableText().toString().trim();
        String passwordInputverif = arb.txtMdpConfirm.getEditableText().toString().trim();

        if (passwordInput.isEmpty()) {

            arb.textlayoutMdp.setError("Champ Obligatoire");
            return false;
        }
        else if (passwordInputverif.isEmpty()) {
            arb.textlayoutMdpVerif.setError("Champ Obligatoire");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            arb.textlayoutMdp.setError("Doit contenir au moins 8 caractères et au moins un chiffre [0 à 9]");
            return false;
        }
        else if (!passwordInput.equals(passwordInputverif) ){
            arb.textlayoutMdpVerif.setError("Les mots de passe ne correspondent pas.");
            return false;
        }
        else{
            arb.textlayoutMdpVerif.setError(null);

            arb.textlayoutMdp.setError(null);
            return true;
        }
    }

    private boolean validateName(){
        String nameInput = arb.txtNom.getEditableText().toString().trim();

        if (nameInput.isEmpty()) {

            arb.textLayoutNom.setError("Champ Obligatoire");
            return false;
        }

        else{
            arb.textLayoutNom.setError(null);
            return true;
        }
    }

    private boolean validateUserName(){
        String nameInput = arb.txtUsernameR.getEditableText().toString().trim();

        if (nameInput.isEmpty()) {

            arb.txtUsernameR.setError("Champ Obligatoire");
            return false;
        }

        else{
            arb.txtUsernameR.setError(null);
            return true;
        }
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_create:

                p = new User();
//                p = DAO.GetById("1e3ebf4f-108d-45b5-ad93-868b4e29ce45");
//                Log.i(TAG, "onClick: "+p.getNom());


                p.setNom(arb.txtNom.getText().toString());
                p.setPrenom(arb.txtPrenom.getText().toString());
                p.setAdresse(arb.txtAdresse.getText().toString());
                p.setAge(arb.pickerAge.getValue());
                p.setMail(arb.txtMailRegister.getText().toString());
                p.setNumero(arb.txtPhone.getText().toString());
                p.setMdp(arb.txtMdpRegister.getText().toString());
                p.setUsername(arb.txtUsernameR.getText().toString());
                int SelectedS = arb.radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(SelectedS);
                p.setSexe(radioButton.getText().toString());
                if (!validateName() || !validateEmail() || !validatePassword() || !validateUserName()){

                    Toast.makeText(RegisterActivity.this," Coordonnées invalides ",Toast.LENGTH_LONG).show();

                }
                else
                    Log.i(TAG, "onClick: "+p.getNom());
                    register(p);
                break;
            case R.id.btn_cancel:
                break;

        }




    }
}