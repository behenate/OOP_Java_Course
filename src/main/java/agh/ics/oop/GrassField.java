package agh.ics.oop;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap implements IWorldMap{
    private ArrayList<Grass> grasses = new ArrayList<>();
    public GrassField(int n){
        visualizer = new MapVisualizer(this);
        for (int i = 0; i < n; i++) {
            placeGrass(n);
        }
    }
    private void placeGrass(int n){
        int posX = ThreadLocalRandom.current().nextInt(0, (int)Math.sqrt(n*10) + 1);
        int posY = ThreadLocalRandom.current().nextInt(0, (int)Math.sqrt(n*10) + 1);
//        Nie używam isOccupied, ponieważ zwierzak nie blokuje postawienia trawy, pojawia się ona pod nim.
        while (objectAt(new Vector2d(posX, posY)) instanceof Grass){
            posX = ThreadLocalRandom.current().nextInt(0, (int)Math.sqrt(n*10) + 1);
            posY = ThreadLocalRandom.current().nextInt(0, (int)Math.sqrt(n*10) + 1);
        }
        grasses.add(new Grass(new Vector2d(posX, posY)));
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position))
            return true;
        for (Grass grass: grasses){
            if (grass.getPosition().equals(position))
                return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null)
            return super.objectAt(position);
        for (Grass grass: grasses) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    Vector2d[] calculateBounds() {
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
        return new Vector2d[] {lowerLeft, upperRight};
    }

}
