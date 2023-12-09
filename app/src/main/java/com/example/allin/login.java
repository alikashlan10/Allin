package com.example.allin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DbHelper dbHelper=new DbHelper(this);
        EditText username=(EditText)findViewById(R.id.un_tb);
        EditText pass=(EditText)findViewById(R.id.pass_tb);
        Button login_btn=(Button)findViewById(R.id.login_button);
        int found;
        found = (int) dbHelper.loginUser(username.getText().toString(),pass.getText().toString());

        if (found!=-1)
            Toast.makeText(getApplicationContext(),"login successfuly",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"login failed",Toast.LENGTH_SHORT).show();





    }
}