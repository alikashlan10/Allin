package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent i=getIntent();

        OnlineShoppingSystem sys=OnlineShoppingSystem.getInstance();
        DbHelper dbHelper=new DbHelper(this);

        EditText username = findViewById(R.id.RegUsername);
        EditText name = findViewById(R.id.RegFullName);
        EditText ssn = findViewById(R.id.RegSSN);
        EditText email = findViewById(R.id.RegEmail);
        EditText pass = findViewById(R.id.RegPassword);
        EditText country = findViewById(R.id.RegCountry);
        EditText City = findViewById(R.id.RegCity);
        EditText street = findViewById(R.id.RegStreet);
        EditText buildingNum = findViewById(R.id.RegBuilding);
        EditText flatNum = findViewById(R.id.RegApartment);
        Button registerButton = findViewById(R.id.Regsubmitbtn);

        Intent Choiceintent = getIntent();
        String source = Choiceintent.getStringExtra("Source");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Person person = PersonFactory.GetPerson("user");}
                User user = new User();

                user.Register(username.getText().toString(),
                            pass.getText().toString(),
                            name.getText().toString(),
                            email.getText().toString(),
                            ssn.getText().toString(),
                            country.getText().toString(),
                            City.getText().toString(),
                            street.getText().toString(),
                            buildingNum.getText().toString(),
                            flatNum.getText().toString(),
                            dbHelper
                );
                Intent intent = new Intent( RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}