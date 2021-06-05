package kuba.kern.passholder.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kuba.kern.passholder.Database.DBConnect;
import kuba.kern.passholder.Navi.NaviMain;
import kuba.kern.passholder.R;

public class MainActivity extends AppCompatActivity {

   // FirebaseFirestore db = FirebaseFirestore.getInstance();
   DBConnect db = new DBConnect(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginUser(View view) {

        EditText Email = findViewById(R.id.editTextLogin);
        EditText Password = findViewById(R.id.editTextPassword);
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        db.loginUser(email,password);

        Intent intent = new Intent(this, NaviMain.class);
        startActivity(intent);
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}