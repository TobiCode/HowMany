package com.example.tobi.howmany;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tobi.howmany.logic.ConstantsOfGame;
import com.example.tobi.howmany.logic.Game;
import com.example.tobi.howmany.logic.Player;
import com.example.tobi.howmany.logic.SortByPoints;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class ResultActivity extends AppCompatActivity {

    private Game game;
    private LinkedList<Player> players;
    private TableLayout resultLayout;
    private Button nextQButton;
    private Button newGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Get Game Object
        Intent intent = getIntent();
        game = (Game) intent.getSerializableExtra("Game");
        players = game.getPlayers();

        resultLayout = (TableLayout) findViewById(R.id.tableLayout);
        nextQButton = (Button) findViewById(R.id.nextQButton);
        newGameButton = (Button) findViewById(R.id.newGameButton);

        displayTable(players, resultLayout);

        for (Player player: players){
            if(player.getPoints() < ConstantsOfGame.maxPointsToReach){
                newGameButton.setVisibility(View.INVISIBLE);
            }
            else{
                nextQButton.setVisibility(View.INVISIBLE);
                newGameButton.setVisibility(View.VISIBLE);
            }
        }
    }


    public void nextQuestion(View view){
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("Game", game);
        startActivity(intent);
        finish();
    }

    public void newGame(View view){
        Toast.makeText(this, "New Game", Toast.LENGTH_SHORT).show();;
    }



    private void displayTable(LinkedList<Player> players, TableLayout resultLayout){
        //Sort players first according their points
        Collections.sort(players, new SortByPoints());
        int i=1;
        for (Player player: players){
            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(lp);

            TextView textViewName = new TextView(getApplicationContext());
            TextView textViewPoints = new TextView(getApplicationContext());
            TextView textViewPointsLastRound = new TextView(getApplicationContext());

            textViewName.setText(player.getName());
            String pointsEarned = "";
            if(player.getLastPointsEarned() > 0){
                pointsEarned += "+" + player.getLastPointsEarned();
            }
            else{
                pointsEarned = "0";
            }
            textViewPoints.setText(Integer.toString(player.getPoints()));
            textViewPointsLastRound.setText(pointsEarned);

            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            textViewName.setLayoutParams(params);
            textViewPoints.setLayoutParams(params);
            textViewPointsLastRound.setLayoutParams(params);

            textViewName.setGravity(Gravity.CENTER);
            textViewPoints.setGravity(Gravity.CENTER);
            textViewPointsLastRound.setGravity(Gravity.CENTER);

            textViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textViewPoints.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            textViewPoints.setTypeface(Typeface.DEFAULT_BOLD);
            textViewPointsLastRound.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);

            row.setBackgroundColor(Color.parseColor(player.getColor()));

            row.addView(textViewName);
            row.addView(textViewPointsLastRound);
            row.addView(textViewPoints);

            resultLayout.addView(row,i);

            i+=1;
        }
    }


    public void backButtonOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Game", game);
        startActivity(intent);
        finish();
    }


}
