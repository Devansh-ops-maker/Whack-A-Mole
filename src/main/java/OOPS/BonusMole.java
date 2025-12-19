package OOPS;
import javafx.scene.image.*;
//This is a type of HoleOccupant -BonusMole
class BonusMole extends HoleOccupant{
    Image bonusmole=new Image("bonusmole.png");
    public int whack() { //Whacking the bonus mole
        return 1000;
    }
    public Image getImage(){ //Getting the image for that bonusMole occupant
        return bonusmole;
    }
}
