package com.example.tobi.howmany.logic;

import java.util.Comparator;

/**
 * Created by Tobi on 27.03.2019.
 */

public class SortByPoints implements Comparator<Player> {

    @Override
    public int compare(Player player, Player t1) {

        return t1.getPoints() - player.getPoints();

    }
}
