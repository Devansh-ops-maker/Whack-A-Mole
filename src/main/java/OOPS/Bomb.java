package OOPS;

import javafx.scene.image.*;
// This creates a Bomb which is a type of HoleOccupant
class Bomb extends HoleOccupant{
    Image bomb=new Image("bomb.png");
    @Override
    public int whack() {  //Whacking the bomb
        return -500;
    }
    public Image getImage(){ // Getting the image from the resource file
        return bomb;
    }
}