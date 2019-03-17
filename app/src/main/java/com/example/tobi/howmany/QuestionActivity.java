package com.example.tobi.howmany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.tobi.howmany.logic.Game;
import com.example.tobi.howmany.logic.Player;

import org.w3c.dom.Text;

import java.util.LinkedList;

public class QuestionActivity extends AppCompatActivity {

    private Game game;
    private LinkedList<Player> players;

    private TextView currentPlayerTextView;
    private TextView questionBoxTextView;
    private Button yesButton;
    private Button noButton;
    private NumberPicker numberGuessPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Get Game Object
        Intent intent = getIntent();
        game = (Game) intent.getSerializableExtra("Game");
        players = game.getPlayers();
        String question = game.getQuestions().getRandomQuestion();
        Log.i("Debug: ", question);


        //Init Views
        currentPlayerTextView = (TextView) findViewById(R.id.currentPlayerTextView);
        questionBoxTextView = (TextView) findViewById(R.id.questionBoxTextView);
        yesButton = (Button) findViewById(R.id.yesButton);
        noButton = (Button) findViewById(R.id.noButton);
        numberGuessPicker = (NumberPicker) findViewById(R.id.numberGuessPicker);

        //Init Values of Views
        currentPlayerTextView.setText(players.get(0).getName());
        questionBoxTextView.setText(question);
        numberGuessPicker.setMinValue(0);
        numberGuessPicker.setMaxValue(players.size());
    }


    public void backButtonOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Game", game);
        startActivity(intent);
        finish();
    }
}
