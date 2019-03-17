package com.example.tobi.howmany.logic;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Tobi on 19.01.2019.
 */

public class Questions implements Serializable{

    LinkedList<String> questionsList;

    Set<String> questionsOFTheGame;

    public Questions(LinkedList<String> questionsList) {
        this.questionsList = questionsList;
        questionsOFTheGame = new HashSet<>();

    }


    public String getRandomQuestion() {
        String question;
        do {
            if(questionsOFTheGame.size() == questionsList.size()){
                questionsOFTheGame.clear();
            }
            int index = (int) (Math.random() * this.questionsList.size());
            question = questionsList.get(index);
        }
        while (questionsOFTheGame.contains(question));
        questionsOFTheGame.add(question);
        return question;
    }

}
