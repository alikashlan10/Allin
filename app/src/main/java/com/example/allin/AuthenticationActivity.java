package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        OnlineShoppingSystem sys=OnlineShoppingSystem.getInstance();
        DbHelper dbHelper=new DbHelper(this);


        ForgetPasswordContext context=new ForgetPasswordContext();


        Button submitbtn=findViewById(R.id.SubmitButton);


        RadioButton emailRadio=findViewById(R.id.EmailradioButton);
        RadioButton SsnRadio=findViewById(R.id.SsnRadioButton);

        EditText input=findViewById(R.id.ChoosenInput);
        EditText passTextBox=findViewById(R.id.ResetPass);

        passTextBox.setVisibility(View.GONE);

        //checkoutbtn.setVisibility(View.GONE);
        //placeorderbtn.setVisibility(View.VISIBLE);

        SsnRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform your action when radioButton1 is selected
                input.setHint("SSN");
            }
        });

        Intent fromLoginIntent = getIntent();
        String receivedUsername = fromLoginIntent.getStringExtra("usernamefromlogin");

        boolean authenticated=false;

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (emailRadio.isChecked())
                {
                    context.setAuthenticationStrategy(new EmailAuthentecationStrategy());
                    if(context.executetAuthenticationStrategy(input.getText().toString(),receivedUsername)){
                        passTextBox.setVisibility(View.VISIBLE);
                        submitbtn.setText("Reset");

                        submitbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sys.UpdatePassword(receivedUsername,passTextBox.getText().toString(),dbHelper);
                                submitbtn.setText("Login");
                                submitbtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent tologin=new Intent(AuthenticationActivity.this,LoginActivity.class);
                                        startActivity(tologin);
                                    }
                                });
                            }
                        });
                    }

                }
                else if(SsnRadio.isChecked())
                {

                    context.setAuthenticationStrategy(new SsnAuthenticationStrategy());
                    if(context.executetAuthenticationStrategy(input.getText().toString(),receivedUsername)){
                        passTextBox.setVisibility(View.VISIBLE);
                        submitbtn.setText("Reset");

                        submitbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sys.UpdatePassword(receivedUsername,passTextBox.getText().toString(),dbHelper);
                                submitbtn.setText("Login");
                                submitbtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent tologin=new Intent(AuthenticationActivity.this,LoginActivity.class);
                                        startActivity(tologin);
                                    }
                                });
                            }
                        });
                    }
                }

            }
        });




    }
}