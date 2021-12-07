package agh.ics.oop;

import javafx.scene.control.Label;

public class Grass extends AbstractWorldMapElement {

    public Grass(Vector2d position){
        this.position = position;
        label=new Label(this.toString());
    }
    @Override
    public String toString(){
        return "*";
    }

}
