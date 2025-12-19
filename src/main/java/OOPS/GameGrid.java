package OOPS;
import OOPS.HoleOccupant;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.util.List;
//This classes manages all the HoleOccupant that randomly spawn in the UI
public class GameGrid {
    HoleOccupant[][] holes = new HoleOccupant[3][6];

    public void set(int r, int c, HoleOccupant h) //Setting the HoleOccupant with the type of random spawn
    {
        holes[r][c] = h;
    }
    public HoleOccupant get(int r, int c) { //Getting the random spawn from that array
        return holes[r][c];
    }
    public void clear(int r, int c) {  //Clearing the random spawn
        holes[r][c] = null;
    }
}

