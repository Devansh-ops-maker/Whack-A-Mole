package OOPS;
import java.util.*;
import java.io.*;
import java.awt.*;
import com.example.gl2.*;
import javafx.application.Platform;
import javafx.scene.image.Image;
import OOPS.GameGrid.*;
import ScoreManager.HighScoreManager;
import ScoreManager.PlayerScore;
public class GameEngine implements Runnable {
    int score=0;
    int time=60;
    Image hole=new Image("hole.png");
    GameGrid gr=new GameGrid();// We create a Gamegrid object so that we know what type of HoleOccupant is present in which  grid
    HighScoreManager hsm=new HighScoreManager();   // High Score Manager to add player score in the dat file
    PlayerScore ps;
    UIGame ui;
    public GameEngine(UIGame ui){
        this.ui=ui;
        this.score=0;    //Constructor to recieve instance of the currently running UI to keep track of the currently running UI
        this.time=60;
    }

    @Override
    public void run() {
        boolean gameRunning=true;
        Random rd=new Random();
        while(gameRunning) {  //Method to keep the gameRunning loop to keep track when the game will end
            if(time==0)
            {
                ps=new PlayerScore("Player"+" "+(score+1),score); //When time ends register a new player
                hsm.addScore(ps);
                gameRunning=false; //Make gameRunning False
                Platform.runLater(()-> ui.showGameOver());   //Update the UI using Platform.runlater
                break;
            }
            time -= 1;
            int finalTime = time;
            Platform.runLater(() -> ui.updateTimer(finalTime));
            int select = rd.nextInt(3); // Randomly selecting which type of HoleOccupant to spawn
            int sr=rd.nextInt(3);  //Randomly selecting a row
            int sco=rd.nextInt(6); //Randomly selecting a col
            if (select == 0)
            {
                Mole ml=new Mole();
                gr.set(sr,sco,ml);  //Setting the mole in HoleOccupant array
                Platform.runLater(()->ui.updateHoleImage(sr,sco,gr.get(sr,sco).getImage())); //Updating the UI
            }
            else if(select==1){
               Bomb bm=new Bomb();
               gr.set(sr,sco,bm); // Setting the bomb in HoleOccupant array
                Platform.runLater(()->ui.updateHoleImage(sr,sco,gr.get(sr,sco).getImage())); // Updating the UI
            }
            else{
               BonusMole bsm=new BonusMole();
               gr.set(sr,sco,bsm);   //setting the Bonusmole in HoleOccupant array
                Platform.runLater(()-> ui.updateHoleImage(sr,sco,gr.get(sr,sco).getImage())); // Updating the UI
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
           gr.clear(sr,sco);   //Clearing the HoleOccupant
            Platform.runLater(()->ui.updateHoleImage(sr,sco,hole));   //Updating the UI
        }
    }
    public void handleWhack(int sr,int sco)
    {
        if(gr.get(sr,sco)!=null)
        {
            score+=gr.get(sr,sco).whack();  //Handling the whack method by the help of HoleOccupant array
            Platform.runLater(()->ui.updateHoleImage(sr,sco,hole));   //Updating the UI
            Platform.runLater(()->ui.updateScore(score)); // Updating the score
        }
    }
}
