package agh.ics.oop;

import javafx.application.Platform;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class SimulationEngine implements IEngine{
    private final ArrayList<MoveDirection> moveArray;
    private final ArrayList<Animal> animals;
    private final IWorldMap map;
    public SimulationEngine(ArrayList<MoveDirection> moveArray, IWorldMap map, Vector2d[] initialPositions){
        this.moveArray = moveArray;
        this.animals = new ArrayList<>();
        this.map = map;
        for (Vector2d position: initialPositions) {
            Animal animal = new Animal(map, position);
            animals.add(animal);
            map.place(animal);
        }
    }

    @Override
    public void run() {
        int animalIndex = 0;
        for (MoveDirection move: moveArray) {
            animals.get(animalIndex).move(move);
            System.out.println(map.toString());
            animalIndex = (animalIndex + 1) % animals.size();
        }
    }
}
