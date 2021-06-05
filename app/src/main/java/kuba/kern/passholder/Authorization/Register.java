package kuba.kern.passholder.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import kuba.kern.passholder.Database.DBConnect;

import kuba.kern.passholder.Navi.NaviMain;
import kuba.kern.passholder.R;

public class Register extends AppCompatActivity {

    private static final String TAG = "";
    DBConnect db = new DBConnect(this);

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
   // FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerUser(View view) {

        EditText Email = findViewById(R.id.textEmail);
        EditText Password = findViewById(R.id.textPassword);
        EditText PasswordConfirm = findViewById(R.id.textPasswordConfirm);

        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String passwordConfirm = PasswordConfirm.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        if (!passwordConfirm.equals(password)) {
            Toast.makeText(this,"Passwords does not match.", Toast.LENGTH_SHORT).show();
        }
        else {
                db.registerUser(email,password);
                db.addUser(email,password);
                Intent intent = new Intent(this, NaviMain.class);
                startActivity(intent);
        }

    }

}