package com.example.tobi.howmany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;

public class PlayersActivity extends AppCompatActivity {

    public ListView playersListView;
    public EditText nameOfPlayer;
    public LinkedList<String> playersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        playersListView = (ListView) findViewById(R.id.playerListVIew);
        nameOfPlayer = (EditText) findViewById(R.id.nameEditText);
        playersList = new LinkedList<String>();
    }


    public void backButtonOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void addPlayerOnCLick(View view) {
        String playerName = nameOfPlayer.getText().toString();
        if (playerName != null && playerName.length() > 0) {
            playersList.add(playerName);
            updateListView();
            nameOfPlayer.setText("");

        } else {
            Toast.makeText(this, "Gib bitte einen Namen ein.", Toast.LENGTH_SHORT).show();
        }

    }

    public void startGameOnClick(View view){
        Toast.makeText(this, "Start Game", Toast.LENGTH_SHORT).show();
    }

    public void updateListView() {
        // Create The Adapter with passing ArrayList as 3rd parameter
        String[] playersArray = playersList.toArray(new String[playersList.size()]);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, playersArray);
        // Set The Adapter
        playersListView.setAdapter(arrayAdapter);
    }




}
