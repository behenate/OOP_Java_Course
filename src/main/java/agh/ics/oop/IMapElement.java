package agh.ics.oop;

import javafx.scene.control.Label;

public interface IMapElement {
    Vector2d getPosition();
    Label getLabel();
    void setLabel(Label label);
}
