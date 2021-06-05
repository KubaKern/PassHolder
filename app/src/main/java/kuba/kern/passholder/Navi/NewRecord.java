package kuba.kern.passholder.Navi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.common.base.MoreObjects;

import kuba.kern.passholder.R;

public class NewRecord extends AppCompatActivity {

    ImageView EyeOpen;
    ImageView EyeClosed;
    EditText Password;
    EditText Title;
    Button Confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EyeOpen = findViewById(R.id.eyeOpen);
        EyeClosed = findViewById(R.id.eyeClosed);
        Password = findViewById(R.id.textPass);
        Title = findViewById(R.id.textTitle);
        Confirm = findViewById(R.id.buttonConfirm);


        EyeOpen.setVisibility(View.INVISIBLE);
        EyeClosed.setClickable(true);

        EyeClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EyeOpen.setVisibility(View.VISIBLE);
                EyeClosed.setVisibility(View.INVISIBLE);
                Password.setTransformationMethod(null);

                EyeClosed.setClickable(false);
                EyeOpen.setClickable(true);
            }
        });

        EyeOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EyeOpen.setVisibility(View.INVISIBLE);
                EyeClosed.setVisibility(View.VISIBLE);
                Password.setTransformationMethod(new PasswordTransformationMethod());
                EyeOpen.setClickable(false);
                EyeClosed.setClickable(true);
            }
        });
    }
    @Override
    public void onBackPressed(){
        Toast.makeText(this,"Wciśnięto powrót",Toast.LENGTH_SHORT).show();
        dialogConfirm().show();
       // this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // dialog czy napewno
                dialogConfirm().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public AlertDialog dialogConfirm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Save changes?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(NewRecord.this,NaviMain.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // cancelled
                        Toast.makeText(NewRecord.this,"Nope", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewRecord.this,NaviMain.class);
                        startActivity(intent);
                    }
                });
        return builder.create();
    }

}