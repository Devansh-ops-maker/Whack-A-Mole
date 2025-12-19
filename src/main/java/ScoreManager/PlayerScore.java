package ScoreManager;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PlayerScore implements Serializable {  //Player Score for keeping track of the player
    private String name; // Name of the player
    private int score; //Score of the player

    public PlayerScore(String name, int score) { //Construtor for loading player info
        this.name = name;
        this.score = score;
    }
    public String getName() {
        return name; //Getting the name of the player
    }

    public int getScore() {
        return score; //Getting the score of the player
    }
}
