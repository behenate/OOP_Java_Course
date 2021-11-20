package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap{
    protected final ArrayList<Animal> animals = new ArrayList<Animal>();
    protected MapVisualizer visualizer;



    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()) || animals.contains(animal)){
            return false;
        }
        animals.add(animal);
        return true;
    }


    public Object objectAt(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }


    public boolean isOccupied(Vector2d position) {
        for (Animal currentAnimal : animals) {
            if (currentAnimal.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        Vector2d lowerLeft = calculateBounds()[0];
        Vector2d upperRight = calculateBounds()[1];
        return visualizer.draw(lowerLeft, upperRight);
    }

    public boolean canMoveTo(Vector2d position){
        return !(isOccupied(position) && objectAt(position) instanceof Animal);
    }
    abstract Vector2d[] calculateBounds();
}
