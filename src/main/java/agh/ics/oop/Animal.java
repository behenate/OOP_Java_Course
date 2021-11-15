package agh.ics.oop;

public class Animal {
    private MapDirection mapDirection = MapDirection.NORTH;
    private Vector2d position;
    private final IWorldMap map;
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
        this.map.place(this);
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
            position = newPos;
        }
    }
    public MapDirection getDirection(){
        return mapDirection;
    }
    public Vector2d getPosition(){
        return position;
    }
}
