package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap implements IWorldMap{
    private final int width;
    private final int height;
    private final ArrayList<Animal> animals = new ArrayList<Animal>();
    public RectangularMap(int width, int height){
        visualizer = new MapVisualizer(this);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && !(position.x >= width || position.x < 0 || position.y >= height || position.y < 0);
    }

    @Override
    Vector2d[] calculateBounds() {
        return new Vector2d[]{
                new Vector2d(0,0),
                new Vector2d(width-1, height-1)
        };
    }
}
