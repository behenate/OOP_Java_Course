package agh.ics.oop;

public class Animal {
    private MapDirection mapDirection = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    @Override
    public String toString() {
        return "(" + position.x + "," + position.y + ")";
    }
    private Vector2d dirToVector(){
        Vector2d vector = switch (mapDirection){
            case NORTH -> new Vector2d(0,1);
            case SOUTH -> new Vector2d(0, -1);
            case EAST -> new Vector2d(1,0);
            case WEST -> new Vector2d(-1,0);
        };
        return vector;
    }
    public void move(MoveDirection direction){
        Vector2d newPos = position;
        switch (direction){
            case LEFT -> mapDirection = mapDirection.previous();
            case RIGHT -> mapDirection = mapDirection.next();
            case FORWARD -> newPos = position.add(dirToVector());
            case BACKWARD -> newPos = position.add(dirToVector().opposite());
        }
        if (newPos.x < 5 && newPos.x > -1 && newPos.y < 5 && newPos.y>-1){
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
