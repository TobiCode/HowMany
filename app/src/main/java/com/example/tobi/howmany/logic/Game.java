package com.example.tobi.howmany.logic;

import java.util.LinkedList;

/**
 * Created by Tobi on 19.01.2019.
 */

public class Game {

    private int maxPointsToReach;
    private Questions questions;
    private LinkedList<Player> players;

    public Game(Questions questions, int maxPointsToReach){
        this.questions = questions;
        this.maxPointsToReach = maxPointsToReach;
    }

    public LinkedList<Player> addPlayer(Player player){
        this.players.add(player);
        return this.players;
    }

    public LinkedList<Player> addNewPlayer(String name, String color){
        Player player = new Player(name, color);
        this.players.add(player);
        return this.players;
    }

    public int getMaxPointsToReach() {
        return maxPointsToReach;
    }

    public void setMaxPointsToReach(int maxPointsToReach) {
        this.maxPointsToReach = maxPointsToReach;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }
}
