package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        ArrayList<Direction> argsEnum = toEnum(args);
        run(argsEnum);
        System.out.println("Stop");
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
    }
}
