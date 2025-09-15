package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addButton;
    Button deleteButton;
    Button confirmButton;
    EditText userInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Vancouver"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addButton = (Button) findViewById(R.id.add_button);
        userInput = (EditText) findViewById(R.id.user_input);
        confirmButton = (Button) findViewById(R.id.confirm_button);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                userInput.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String newCity = userInput.getText().toString().trim();
                dataList.add(newCity);
                userInput.setText("City");
                userInput.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);
                cityAdapter.notifyDataSetChanged();
            }
        });
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            boolean cityClicked = false;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedCity = cities[position];
                cityClicked = true;
                deleteButton = (Button) findViewById(R.id.delete_button);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cityClicked) {
                            dataList.remove(position);
                            cityAdapter.notifyDataSetChanged();
                        }
                        cityClicked = false;
                    }
                });
            }

        });
    }
}
