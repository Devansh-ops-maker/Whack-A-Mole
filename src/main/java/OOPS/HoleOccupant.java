package OOPS;
import javafx.scene.image.*;
abstract class HoleOccupant {  //This is a abstract class that serves as basis for all the HoleOccupants
    boolean visibility=false;
    int time=0;
    public abstract int whack(); //asbtract whack method
    public abstract Image getImage(); //abstract image method to get the image of the HoleOccupant
}
