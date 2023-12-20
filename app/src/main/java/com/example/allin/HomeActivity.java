package com.example.allin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private EditText searchbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView lv;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        lv = findViewById(R.id.test_list);
        ImageView Gotocartbtn = findViewById(R.id.gotocartbtn);
        ImageView Yourordersbtn = findViewById(R.id.yourordersbtn);


        DbHelper dbHelper = new DbHelper(this);
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        //system.loadUsersFromDatabase(dbHelper);
        //system.InitializeAppData(dbHelper);
        //system.loadItemsFromDatabase(dbHelper);

        List<Item> testing = new ArrayList<>();
        HomeAdapter testadapter = new HomeAdapter(this,R.layout.itemdesign,system.getItemsList(), "user");
        lv.setAdapter(testadapter);

        Gotocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,CartActivity.class);
                startActivity(i);
            }
        });

        Yourordersbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(HomeActivity.this,OrderActivity.class);
                startActivity(i2);
            }
        });

        //Button b=findViewById(R.id.editInfo);



        Spinner catspinner = findViewById(R.id.Catspinner);

        // Create an ArrayAdapter using the ArrayList
        List<String> categories = system.getCateogriesNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        catspinner.setAdapter(adapter);
        int position = categories.indexOf("All categories");
        catspinner.setSelection(position);
        catspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Perform the action when an item is selected
                String selectedValue = (String) parentView.getItemAtPosition(position);
                HomeAdapter filteredadapter = new HomeAdapter(HomeActivity.this,R.layout.itemdesign,system.getItemByCategory(selectedValue), "user");
                lv.setAdapter(filteredadapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });

        ImageView searchicon = findViewById(R.id.Searchicon);
        searchbar = findViewById(R.id.Searchbar);
        searchicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x = searchbar.getText().toString();
                HomeAdapter filteredadapter = new HomeAdapter(HomeActivity.this,R.layout.itemdesign, system.SearchByText(x), "user");
                lv.setAdapter(filteredadapter);
            }
        });

        ImageView rec = findViewById(R.id.micIcon);
        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceRecognition();
            }
        });


        Button BestSellersBtn=findViewById(R.id.button);
        BestSellersBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                List<Item> best=new ArrayList<>();
                system.setTopSoldItems(system.getItemsList(), system.getBestSellers(), 3);
                HomeAdapter BestSellers=new HomeAdapter(HomeActivity.this,R.layout.itemdesign,system.getBestSellers(),"user");
                lv.setAdapter(BestSellers);
            }
        });



    }


    private void startVoiceRecognition() {
        searchbar.setText("Talk");
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something");
        SpeechRecognizer recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        recognizer.setRecognitionListener(new SearchRecognitionListener(HomeActivity.this,searchbar));
        recognizer.startListening(intent);
    }





}