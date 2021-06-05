package kuba.kern.passholder.Database;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

import kuba.kern.passholder.Navi.NaviMain;

public class DBConnect {

    private static final String TAG = "";
    @SuppressLint("StaticFieldLeak")
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static Map<String, Object> user =  new HashMap<>();;
    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final Context ctx;
    public DBConnect(Context ctx) {
        db = FirebaseFirestore.getInstance();
        user = new HashMap<>();
        this.ctx = ctx;
    }
    
    public FirebaseFirestore getInstance() {
        return db;
    }
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }
    
    public void addUser(String login, String password) {
        user.put("login",login);
        user.put("password",password); // tutaj szyfrowanko jakies będzie se
        //AES.secure(password);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }
    public void registerUser(String email, String password) {

       // mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) ctx, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(ctx, "Register succes.", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ctx, NaviMain.class);
                            ctx.startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(ctx, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(ctx, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }

    public void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) ctx, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(ctx, "Authentication succes.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ctx, NaviMain.class);
                            ctx.startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(ctx, "Authentication failed. " + Objects.requireNonNull(task.getException()).getMessage(),
                                    Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }
                    }
                });
    }
}
