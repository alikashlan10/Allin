package com.example.allin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginbtn=findViewById(R.id.login_button);

        DbHelper dbHelper=new DbHelper(this);
        EditText username=findViewById(R.id.un_tb);
        EditText pass=findViewById(R.id.pass_tb);

        RadioButton userbutton=findViewById(R.id.UserradioButton);
        RadioButton Adminbutton=findViewById(R.id.AdminradioButton2);






        //Button login_btn=findViewById(R.id.login_button);
        //Button a7a=findViewById(R.id.button);












    }
}