package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DbHelper dbHelper=new DbHelper(this);

        EditText username = findViewById(R.id.RegUsername);
        EditText fullName = findViewById(R.id.RegFullName);
        EditText ssn = findViewById(R.id.RegSSN);
        EditText email = findViewById(R.id.RegEmail);
        EditText pass = findViewById(R.id.RegPassword);
        EditText country = findViewById(R.id.RegCountry);
        EditText City = findViewById(R.id.RegCity);
        EditText street = findViewById(R.id.RegStreet);
        EditText buildingNum = findViewById(R.id.RegBuilding);
        EditText flatNum = findViewById(R.id.RegApartment);
        Button registerButton = findViewById(R.id.Regsubmitbtn);

        //Update Personal Info
        String isUpdate = getIntent().getStringExtra("update");
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        User user = (User)system.getCurrentPerson();
        if(isUpdate != null){
            TextView RegTitle = findViewById(R.id.RegTitle);
            RegTitle.setText("Update Personal Info");
            registerButton.setText("update");
            username.setText(user.getUserName());
            pass.setText(user.getPassword());
            pass.setInputType(InputType.TYPE_CLASS_TEXT);
            if(user.getFullName()!= null)fullName.setText(user.getFullName());
            if(user.getEmail()!= null)email.setText(user.getEmail());
            if(user.getSSN()!= null)ssn.setText(user.getSSN());
            if(user.getUserAddress()!= null) {
                if(user.getUserAddress().getCountry()!= null)country.setText(user.getUserAddress().getCountry());
                if(user.getUserAddress().getCity()!= null)City.setText(user.getUserAddress().getCity());
                if(user.getUserAddress().getStreet()!= null)street.setText(user.getUserAddress().getStreet());
                if(user.getUserAddress().getBuildingNum()!= null)buildingNum.setText(user.getUserAddress().getBuildingNum());
                if(user.getUserAddress().getFlatNum()!= null)flatNum.setText(user.getUserAddress().getFlatNum());
            }
            EditText RegRePassword = findViewById(R.id.RegRePassword);
            RegRePassword.setVisibility(View.GONE);
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUpdate == null){
                    //Person person = PersonFactory.GetPerson("user");}
                    User user = new User();
                    user.Register(username.getText().toString(),
                            pass.getText().toString(),
                            fullName.getText().toString(),
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
                else{
                    user.UpdatePersonalInfo(
                            username.getText().toString(),
                            pass.getText().toString(),
                            fullName.getText().toString(),
                            email.getText().toString(),
                            ssn.getText().toString(),
                            country.getText().toString(),
                            City.getText().toString(),
                            street.getText().toString(),
                            buildingNum.getText().toString(),
                            flatNum.getText().toString()
                    );
                    Intent intent = new Intent( RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}