package com.example.tobi.howmany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tobi.howmany.utils.CSVReader;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CSVReader.readExcelFileFromAssets(getBaseContext());
    }


    public void startGameOnClick(View view){
        Intent intent = new Intent(this, PlayersActivity.class);
        startActivity(intent);
    }
}
