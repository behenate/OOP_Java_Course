package agh.ics.oop;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RectangularMap implements IWorldMap{
    private final int width;
    private final int height;
    private final ArrayList<Animal> animals = new ArrayList<Animal>();
    MapVisualizer visualizer;
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        visualizer =  new MapVisualizer(this);
    }

    public void addAnimal(Animal animal){
        if (!animals.contains(animal) && canMoveTo(animal.getPosition())){
            animals.add(animal);
        }
    }
    public void removeAnimal(Animal animal){
            animals.remove(animal);
    }
    public ArrayList<Animal> getAnimals(){
        return animals;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.x >= width || position.x < 0 || position.y >= height || position.y < 0){
            return false;
        }
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition()) && !animals.contains(animal)){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal currentAnimal : animals) {
            if (currentAnimal.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal currentAnimal : animals){
            if (currentAnimal.getPosition().equals(position)){
                return currentAnimal;
            }
        }
        return null;
    }
    @Override
    public String toString(){
        return visualizer.draw(new Vector2d(0,0), new Vector2d(this.width, this.height));
    }
}
