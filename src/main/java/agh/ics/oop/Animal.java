package agh.ics.oop;

import java.util.ArrayList;

public class Animal extends AbstractWorldMapElement {
    private MapDirection mapDirection = MapDirection.NORTH;

    private final IWorldMap map;
    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
    }
    @Override
    public String toString() {
        return switch (mapDirection){
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }
    public void move(MoveDirection direction){
        Vector2d newPos = position;
        switch (direction){
            case LEFT -> mapDirection = mapDirection.previous();
            case RIGHT -> mapDirection = mapDirection.next();
            case FORWARD -> newPos = position.add(mapDirection.toUnitVector());
            case BACKWARD -> newPos = position.add(mapDirection.toUnitVector().opposite());
        }
        if (map.canMoveTo(newPos)){
            positionChanged(newPos);
            position = newPos;
        }
    }
    public MapDirection getDirection(){
        return mapDirection;
    }
    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    private void positionChanged(Vector2d newPosition ){
        for (IPositionChangeObserver observer: observers) {
            observer.positionChanged(position, newPosition);
        }
    }
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
}
