package com.example.theunknownvariable.Controller;

public class GameStateManager {
    private static GameStateManager instance;

    //Flags
    private boolean clue1Unlocked = false;
    private boolean clue2Unlocked = false;
    private boolean clue3Unlocked = false;
    private boolean clue4Unlocked = false;

    private GameStateManager() {}

    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    //Check if clue is unlocked
    public void unlockClue1() {
        clue1Unlocked = true;
    }
    //Check if clue is unlocked
    public void unlockClue2() {
        clue2Unlocked = true;
    }
    //Check if clue is unlocked
    public void unlockClue3() {
        clue3Unlocked = true;
    }
    //Check if clue is unlocked
    public void unlockClue4() {
        clue4Unlocked = true;
    }

    //Return if clue is unlocked
    public boolean isClue1Unlocked() {
        return clue1Unlocked;
    }
    //Return if clue is unlocked
    public boolean isClue2Unlocked() {
        return clue2Unlocked;
    }
    //Return if clue is unlocked
    public boolean isClue3Unlocked() {
        return clue3Unlocked;
    }
    //Return if clue is unlocked
    public boolean isClue4Unlocked() {
        return clue4Unlocked;
    }

}
