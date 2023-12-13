package com.example.allin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginbtn=findViewById(R.id.login_button);
        Button regbtn=findViewById(R.id.reg_button);

        DbHelper dbHelper=new DbHelper(this);
        EditText username=findViewById(R.id.un_tb);
        EditText pass=findViewById(R.id.pass_tb);

        RadioButton userbutton=findViewById(R.id.UserradioButton);
        RadioButton Adminbutton=findViewById(R.id.AdminradioButton);
        userbutton.setChecked(true);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person;
                if(userbutton.isChecked()){
                    person = PersonFactory.GetPerson("user");}
                else {
                    person = PersonFactory.GetPerson("admin");
                }

                if(person.login(username.getText().toString(),pass.getText().toString(),dbHelper))
                    loginbtn.setText("ALOOOO");
                else
                    loginbtn.setText("ad5555looo");

            }
        });
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}

