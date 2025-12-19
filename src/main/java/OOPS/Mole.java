package OOPS;
import javafx.scene.image.*;
//This is a type of HoleOccupant -mole
class Mole extends HoleOccupant{
    Image mole=new Image("mole.png");
    @Override
    public int whack() {  //Its whack method
        return 100;
    }
    public Image getImage(){  //This is how we get the image for that HoleOccupant from resource file
        return mole;
    }
}
