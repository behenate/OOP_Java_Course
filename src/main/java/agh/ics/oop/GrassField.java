package agh.ics.oop;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField implements IWorldMap{
    private ArrayList<Grass> grasses = new ArrayList<Grass>();
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private final MapVisualizer visualizer = new MapVisualizer(this);
    public GrassField(int n){
        for (int i = 0; i < n; i++) {
            int posX = ThreadLocalRandom.current().nextInt(0, (int)Math.sqrt(n*10) + 1);
            int posY = ThreadLocalRandom.current().nextInt(0, (int)Math.sqrt(n*10) + 1);
            grasses.add(new Grass(new Vector2d(posX, posY)));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (this.isOccupied(position)){
            return false;
        };
        return true;
    }

    @Override
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    @Override
    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    @Override
    public boolean place(Animal animal) {
        if (this.isOccupied(animal.getPosition()) && this.objectAt(animal.getPosition()) instanceof Animal){
            return false;
        }
        animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.isAt(position))
                return true;
        }
        for (Grass grass: grasses){
            if (grass.getPosition().equals(position))
                return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        for (Grass grass: grasses) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        Vector2d lowerLeft = animals.get(0).getPosition();
        Vector2d upperRight = animals.get(0).getPosition();
        for (Animal animal : animals) {
            lowerLeft = lowerLeft.lowerLeft(animal.getPosition());
            upperRight = upperRight.upperRight(animal.getPosition());
        }
        for (Grass grass : grasses) {
            lowerLeft = lowerLeft.lowerLeft(grass.getPosition());
            upperRight = upperRight.upperRight(grass.getPosition());
        }
        return visualizer.draw(lowerLeft, upperRight);
    }

}
