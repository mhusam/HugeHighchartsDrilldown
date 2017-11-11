package com.jmco.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Husam
 * @since  1.0.0
 * @datetime   Nov 10, 2017  5:02:49 AM
 */
public final class Data {
    
    public static int FIRST_LEVELS = 2;
    public static int DOWN_LEVELS = 5;
    
    public static BigDecimal random(int min, int max){
        return new BigDecimal(new Random().nextInt((max - min) + 1) + min);
    }
    
    public static BigDecimal ranS(){
        return random(0, 50);
    }
    
    public static final List<Club> CLUBS = new ArrayList<Club>(){{      
            for(int id = 0; id < FIRST_LEVELS; id++){
                String name = "Node " + "@" +id;
                add(new Club(new BigDecimal(id), name, ranS(), ranS(), ranS(), ranS(), ranSL(name), ranC(name, id, DOWN_LEVELS)));
            }
    }};
    
    // random childs
    public static List<ClubGroup> ranC(final String name, final int id, int level){
        if(level != 0){
            final int nextLevel = --level;
            return new ArrayList<ClubGroup>(){{
                for(int i = 0; i < random(0, 10).intValue(); i++){
                    add(new ClubGroup(new BigDecimal(id + i), name + "_" + i, ranS(), ranS(), ranS(), ranS(), ranSL(name + "_" + i), 

                            ranCLub(name + "_" + i, i, nextLevel)));
                }
            }};
        } else {
            return null;
        }
    }
    
    public static List<Club> ranCLub(final String name, final int id, int level){
        if(level != 0){
            final int nextLevel = --level;
            return new ArrayList<Club>(){{
                for(int i = 0; i < random(0, 10).intValue(); i++){
                    add(new Club(new BigDecimal(id + i), name + "_" + i, ranS(), ranS(), ranS(), ranS(), ranSL(name + "_" + i), 

                            ranC(name + "_" + i, i, nextLevel)));
                }
            }};
        } else {
            return null;
        }
    }
    
    // random status list
    public static List<ClubStatus2> ranSL(final String name) {
        return new ArrayList<ClubStatus2>(){{
            add(new ClubStatus2("Status 1 - " + name, ranS()));
            add(new ClubStatus2("Status 2 - " + name, ranS()));
            add(new ClubStatus2("Status 3 - " + name, ranS()));
            add(new ClubStatus2("Status 4 - " + name, ranS()));
        }};
    }
}
