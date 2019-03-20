package com.example.tobi.howmany.logic;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tobi on 17.03.2019.
 */

public class ConstantsOfGame implements Serializable {

    public static final int maxPointsToReach = 12;

    public static final Map<Integer, Integer> pointsDistribution;
    static{
        HashMap<Integer, Integer> pointDistrbution = new HashMap<>();
        pointDistrbution.put(0, 3);
        pointDistrbution.put(1, 2);
        pointDistrbution.put(-1, 2);
        pointsDistribution = Collections.unmodifiableMap(pointDistrbution);
    }
}
