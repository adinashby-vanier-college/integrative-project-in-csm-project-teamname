package com.example.theunknownvariable.Controller;

public class GameStateManager {
    private static GameStateManager instance;

    //Flags
    private boolean clue1Unlocked = false;
    private boolean clue2Unlocked = false;
    private boolean clue3Unlocked = false;
    private boolean clue4Unlocked = false;

    private boolean game1Locked = false;
    private boolean game2Locked = false;
    private boolean game3Locked = false;
    private boolean game4Locked = false;

    //Singleton class that constantly looks for the games' progress (one instance through the game)
    private GameStateManager() {}

    //Unique instance of the method - getter
    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    //Check if clue is unlocked for each game
    public void unlockClue1() {
        clue1Unlocked = true;
    }
    public void unlockClue2() {
        clue2Unlocked = true;
    }
    public void unlockClue3() {clue3Unlocked = true;}
    public void unlockClue4() {
        clue4Unlocked = true;
    }

    //Return if clue is unlocked for each game
    public boolean isClue1Unlocked() {
        return clue1Unlocked;
    }
    public boolean isClue2Unlocked() {
        return clue2Unlocked;
    }
    public boolean isClue3Unlocked() {
        return clue3Unlocked;
    }
    public boolean isClue4Unlocked() {
        return clue4Unlocked;
    }



    //Check if game is unlocked for each game
    public void lockGame1() {
        game1Locked = true;
    }
    public void lockGame2() {
        game2Locked = true;
    }
    public void lockGame3() {
        game3Locked = true;
    }
    public void lockGame4() {
        game4Locked = true;
    }

    //Return if game is unlocked
    public boolean isGame1Locked() {
        return game1Locked;
    }
    public boolean isGame2Locked() {
        return game2Locked;
    }
    public boolean isGame3Locked() {
        return game3Locked;
    }
    public boolean isGame4Locked() {
        return game4Locked;
    }



}
