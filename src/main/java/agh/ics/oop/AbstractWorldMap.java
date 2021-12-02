package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final HashMap<Vector2d, IMapElement> mapElements = new LinkedHashMap<>();
    protected final MapVisualizer visualizer = new MapVisualizer(this);
    protected final MapBoundary boundary = new MapBoundary();
    public boolean place(Animal animal) throws IllegalArgumentException {
        if (!canMoveTo(animal.getPosition())){
            throw new IllegalArgumentException("Pole " + animal.getPosition() + " nie jest dobrym polem dla zwierzaka!");
        }
        animal.addObserver(this);
        animal.addObserver(boundary);
        boundary.positionChanged(new Vector2d(0,0), animal.getPosition());
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
        return visualizer.draw(boundary.lowerLeft(), boundary.upperRight());
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
