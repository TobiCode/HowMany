package com.example.tobi.howmany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.tobi.howmany.logic.ConstantsOfGame;
import com.example.tobi.howmany.logic.Game;
import com.example.tobi.howmany.logic.Player;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {

    private Game game;
    private LinkedList<Player> players;

    private TextView currentPlayerTextView;
    private TextView questionBoxTextView;
    private Button yesButton;
    private Button noButton;
    private Button nextPlayerButton;
    private NumberPicker numberGuessPicker;

    private int yesCounterQuestion;
    private int currentPlayer = 0;
    private int maxPlayers;


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
        maxPlayers = game.getPlayers().size();
        //Set counter to 0
        yesCounterQuestion = 0;

        //Init Views
        currentPlayerTextView = (TextView) findViewById(R.id.currentPlayerTextView);
        questionBoxTextView = (TextView) findViewById(R.id.questionBoxTextView);
        yesButton = (Button) findViewById(R.id.yesButton);
        noButton = (Button) findViewById(R.id.noButton);
        nextPlayerButton = (Button) findViewById(R.id.nextPlayerButton);
        numberGuessPicker = (NumberPicker) findViewById(R.id.numberGuessPicker);

        //Init Values of Views
        currentPlayerTextView.setText(players.get(0).getName());
        questionBoxTextView.setText(question);
        numberGuessPicker.setMinValue(0);
        numberGuessPicker.setMaxValue(players.size());

        //OnCLickListeners
        initOnCLicks();
    }


    public void backButtonOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Game", game);
        startActivity(intent);
        finish();
    }


    public void initOnCLicks() {
        yesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                yesButton.setPressed(true);
                noButton.setPressed(false);
                noButton.setAlpha(0.5f);
                yesButton.setAlpha(1);
                return true;
            }
        });

        noButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                yesButton.setPressed(false);
                noButton.setPressed(true);
                noButton.setAlpha(1);
                yesButton.setAlpha(0.5f);
                return true;
            }
        });
    }

    public void nextPlayerOnClick(View view) {
        //Update counter and guess of player
        if (yesButton.isPressed() || noButton.isPressed()) {
            if (yesButton.isPressed()) {
                yesCounterQuestion += 1;
            }
            players.get(currentPlayer).setCurrentGuess(numberGuessPicker.getValue());


            if (currentPlayer < maxPlayers - 1) {
                currentPlayer += 1;
                //Change Player
                currentPlayerTextView.setText(players.get(currentPlayer).getName());
                if (currentPlayer == maxPlayers - 1) {
                    //AuflösungActivity
                    Log.i("Debug: ", "Next Activity");
                    nextPlayerButton.setText("Auflösung");
                }
                //Default
                yesButton.setAlpha(1);
                noButton.setAlpha(1);
                yesButton.setPressed(false);
                noButton.setPressed(false);
                numberGuessPicker.setValue(0);
            } else {
                //Auflösung + ResultIntent Change
                givePointsToPlayers(players, (ConstantsOfGame.pointsDistribution));
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("Game", game);
                finish();
                startActivity(intent);
            }

        }
    }

    public void givePointsToPlayers(LinkedList<Player> players, Map<Integer, Integer> pointsDistribution){
        for (Player player: players ){
            int differenceOfPoints = player.getCurrentGuess() - yesCounterQuestion;
            Integer differenceOfPointsInteger = new Integer(differenceOfPoints);
            int addPoints;
            if(pointsDistribution.containsKey(differenceOfPointsInteger)){
                addPoints = pointsDistribution.get(differenceOfPointsInteger).intValue();
            }
            else{
                addPoints = 0;
            }
            player.addPoints(addPoints);
            player.setLastPointsEarned(addPoints);
            Log.i("DebugPoints: ", "Player " + player.getName() + " points = " + player.getPoints());
        }
    }
}
