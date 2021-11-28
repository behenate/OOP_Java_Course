package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final HashMap<Vector2d, IMapElement> mapElements = new LinkedHashMap<>();
    protected final MapVisualizer visualizer = new MapVisualizer(this);

    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition())){
            return false;
        }
        animal.addObserver(this);
        mapElements.put(animal.getPosition(), animal);
        return true;
    }


    public Object objectAt(Vector2d position) {
        return mapElements.get(position);
    }


    public boolean isOccupied(Vector2d position) {
        return mapElements.get(position) != null;
    }

    public String toString() {
        Vector2d lowerLeft = calculateBounds()[0];
        Vector2d upperRight = calculateBounds()[1];
        return visualizer.draw(lowerLeft, upperRight);
    }

    public boolean canMoveTo(Vector2d position){
        return !(isOccupied(position) && objectAt(position) instanceof Animal);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

        IMapElement movedElement = mapElements.get(oldPosition);
        mapElements.remove(oldPosition);
        mapElements.put(newPosition, movedElement);

    }

    abstract Vector2d[] calculateBounds();

}
