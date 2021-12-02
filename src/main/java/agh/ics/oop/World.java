package agh.ics.oop;

import java.util.*;
import java.util.stream.Collectors;

public class World {
    public static void main(String[] args) {
        try{
            ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,1) };
            SimulationEngine engine = new SimulationEngine(directions, map, positions);
//        Zamiast run wywołuję run and show, które robi dokładnie to co run, tylko pokazuje informacje z punku 7.
            engine.runAndShow();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Program kończy działanie");
        }
    }
}