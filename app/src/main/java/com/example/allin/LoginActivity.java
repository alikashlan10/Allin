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

        OnlineShoppingSystem sys=OnlineShoppingSystem.getInstance();

        Button loginbtn=findViewById(R.id.login_button);
        Button regbtn=findViewById(R.id.reg_button);

        DbHelper dbHelper=new DbHelper(this);
        EditText username=findViewById(R.id.un_tb);
        EditText pass=findViewById(R.id.pass_tb);

        RadioButton userRadiobutton=findViewById(R.id.UserradioButton);
        RadioButton AdminRadiobutton=findViewById(R.id.AdminradioButton);
        userRadiobutton.setChecked(true);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set logined person object

                Person person;
                if(userRadiobutton.isChecked()){
                    person = PersonFactory.GetPerson(userRadiobutton.getText().toString());
                }
                else {
                    person = PersonFactory.GetPerson(AdminRadiobutton.getText().toString());
                }


                if(person.login(username.getText().toString(),pass.getText().toString(),dbHelper) && person instanceof User) {
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                else if (person.login(username.getText().toString(),pass.getText().toString(),dbHelper) && person instanceof Admin)
                {
                    Intent i = new Intent(LoginActivity.this, AdminOrderActivity.class);
                    startActivity(i);
                }
                else {
                    Toast toast = Toast.makeText(LoginActivity.this, "Failed login !!! ", Toast.LENGTH_LONG);
                    toast.show();
                }


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

