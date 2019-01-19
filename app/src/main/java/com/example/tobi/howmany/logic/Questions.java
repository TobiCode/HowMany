package com.example.tobi.howmany.logic;

import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Tobi on 19.01.2019.
 */

public class Questions {

    LinkedList<String> questionsList;

    Set<String> questionsOFTheGame;

    public Questions(LinkedList<String> questionsList) {
        this.questionsList = questionsList;
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
