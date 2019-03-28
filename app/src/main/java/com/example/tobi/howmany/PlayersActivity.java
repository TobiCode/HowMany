package com.example.tobi.howmany;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tobi.howmany.logic.ConstantsOfGame;
import com.example.tobi.howmany.logic.Game;
import com.example.tobi.howmany.logic.Player;
import com.example.tobi.howmany.logic.Questions;
import com.example.tobi.howmany.utils.CSVReader;

import java.util.LinkedList;

public class PlayersActivity extends AppCompatActivity {

    public ListView playersListView;
    public EditText nameOfPlayer;
    public LinkedList<String> playerNames;
    public int playerCounter = 0;
    public Questions questions;
    private Game game;
    //Weiß, blau, grün, gelb
    public String[] colorsold = new String[]{"#FFFFFF", "#f0f404","#04f4b0","#6c9bf7"};
    public String[] colorsbackup = new String[]{"#FF8432", "#A2F252","#59DBD0","#333333", "#CECECE"};
    //https://coolors.co/efeae3-fec400-44c18e-1f49bf-fd85a2
    public String[] colorskwoat = new String[]{"#EFEAE3", "#FEC400","#44C18E","#1F49BF", "#FD85A2"};
    //https://coolors.co/ff8300-f80089-8b00ff-2bb5ff-1dff00
    public String[] colorshappy = new String[]{"#FF8300", "#F80089","#8B00FF","#2BB5FF", "#1DFF00"};
    public String[] colors = new String[]{"#FF8300", "#F80089","#8B00FF","#2BB5FF", "#1DFF00"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        playersListView = (ListView) findViewById(R.id.playerListVIew);
        nameOfPlayer = (EditText) findViewById(R.id.nameEditText);
        playerNames = new LinkedList<String>();
        LinkedList<String> questionsList = CSVReader.readExcelFileFromAssets(getApplicationContext());
        questions = new Questions(questionsList);

        game = new Game(questions, ConstantsOfGame.maxPointsToReach);

        playersListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                playerCounter -= 1;
                String nameOfDeletion = playerNames.get(pos);
                playerNames.remove(pos);
                game.removePlayer(nameOfDeletion);
                updateListView();
                Toast.makeText(PlayersActivity.this, "Spieler " + nameOfDeletion + " gelöscht", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


    public void backButtonOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void addPlayerOnCLick(View view) {
        playerCounter += 1;
        String playerName = nameOfPlayer.getText().toString();
        if (playerName != null && playerName.length() > 0) {
            if (!game.playerNameExists(playerName)) {
                int color_index = playerCounter % colors.length;
                Player newPlayer = new Player(playerName, colors[color_index]);
                game.addPlayer(newPlayer);
                playerNames.add(playerName);
                updateListView();
                nameOfPlayer.setText("");
            }
            else{
                Toast.makeText(this, "Spieler Name existiert schon.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Gib bitte die Namen der Spieler ein.", Toast.LENGTH_SHORT).show();
        }

    }


    public void startGameOnClick(View view) {
        if(game.getPlayers().size() < 2){
            Toast.makeText(getApplicationContext(), "Mehr als 2 Spieler benötigt", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Start Game", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }

    public void updateListView() {
        // Create The Adapter with passing ArrayList as 3rd parameter
        //Create order
        LinkedList<String> playersListItemsCopy = new LinkedList<String>();
        int counter = 1;
        for (String name: playerNames){
            String new_name = counter + ". " + name;
            playersListItemsCopy.add(new_name);
            counter += 1;
        }
        final String[] playersArray = playersListItemsCopy.toArray(new String[playersListItemsCopy.size()]);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, playersArray){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v = super.getView(position, convertView, parent);
                        //your condition logic
                        v.setBackgroundColor(Color.parseColor(game.getPlayers().get(position).getColor()));
                        return v;
                    }
                };
        // Set The Adapter
        playersListView.setAdapter(arrayAdapter);
    }


}
