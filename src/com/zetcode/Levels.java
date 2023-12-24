package com.zetcode;

import java.util.HashMap;

public class Levels {

    private HashMap<Integer , int[][]> levels = new HashMap<>();

    public Levels(){
        levels.put(1,Commons.LEVEL1);
        levels.put(2,Commons.LEVEL2);
        levels.put(3,Commons.LEVEL3);
        levels.put(4,Commons.LEVEL4);
        levels.put(5,Commons.LEVEL5);
        levels.put(6,Commons.LEVEL6);
        levels.put(7,Commons.LEVEL7);
    }

    public int[][] getlevel(Integer number){
        return levels.get(number);
    }

}
