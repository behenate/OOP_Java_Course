package agh.ics.oop;

import java.util.*;
import java.util.stream.Collectors;

public class World {
    public static void main(String[] args) {
//        Laboratoria 1
        /*
        System.out.println("Start");
        ArrayList<Direction> argsEnum = toEnum(args);
        run(argsEnum);
        System.out.println("Stop");
        */

        // Laboratoria 3
//        Animal animal = new Animal();
//
//        ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
//        for (MoveDirection dir: directions) {
//            animal.move(dir);
//            System.out.println(animal);
//        }

//        Laboratoria 4
        ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.runAndShow();
    }

    static ArrayList<Direction> toEnum(String[] args) {
        List<String> argsList = Arrays.asList(args);
        List<String> filtered = argsList.stream().filter(arg -> Objects.equals(arg, "f") || Objects.equals(arg, "b") || Objects.equals(arg, "r") || Objects.equals(arg, "l")).collect(Collectors.toList());
        return filtered.stream().map(arg -> switch (arg) {
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            case "l" -> Direction.LEFT;
            default -> Direction.FORWARD;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    static void run(ArrayList<Direction> args) {
//===================================================================================================================//
//        LABORATORIA 1                                                                                              //
//===================================================================================================================//
        int length = args.size();
        System.out.println("Zwierzak idzie do przodu");

        for (int i = 0; i < length; i++) {
            System.out.println(args.get(i) + ((i == length - 1) ? "":","));
        }
        for (Direction arg : args) {
            switch (arg) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo ");
                default -> {
                }
            }
        }
//===================================================================================================================//
//      Laboratoria 2                                                                                                //
//===================================================================================================================//
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        System.out.println(MapDirection.NORTH);
        System.out.println(MapDirection.NORTH.toUnitVector());
        System.out.println(MapDirection.NORTH.next());
        System.out.println(MapDirection.NORTH.previous());
    }
//===================================================================================================================//
//      Laboratoria 3                                                                                                //
//===================================================================================================================//

}
