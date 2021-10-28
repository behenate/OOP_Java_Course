package agh.ics.oop;

import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    Animal testAnimal;

    @Test
    void testOrientation() {
        testAnimal = new Animal();
        assertEquals(MapDirection.NORTH, testAnimal.getDirection());
        testAnimal.move(MoveDirection.FORWARD);
        assertEquals(MapDirection.NORTH, testAnimal.getDirection());
        testAnimal.move(MoveDirection.BACKWARD);
        assertEquals(MapDirection.NORTH, testAnimal.getDirection());

        testAnimal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, testAnimal.getDirection());
        testAnimal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH, testAnimal.getDirection());
        testAnimal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, testAnimal.getDirection());
        testAnimal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH, testAnimal.getDirection());

        testAnimal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, testAnimal.getDirection());
        testAnimal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.SOUTH, testAnimal.getDirection());
        testAnimal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.WEST, testAnimal.getDirection());
        testAnimal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.NORTH, testAnimal.getDirection());
    }

    /*
        Funkcja testująca dla moveTest. Obraca zwierzaka podaną liczbę razy w prawo, następnie próbuje nim wyjść
        poza mapę przodem i tyłem. Dla każdego ruchu sprawdzane jest czy pozycja jest poprawna.
    */
    void boundsMoveTest(int rotations, int x_vec, int y_vec) {
        Animal testAnimal = new Animal();
        int expected_x = 2;
        int expected_y = 2;
        for (int i = 0; i < rotations; i++) {
            testAnimal.move(MoveDirection.RIGHT);
        }
        for (int i = 0; i < 6; i++) {
            testAnimal.move(MoveDirection.FORWARD);
            expected_x = Math.min(Math.max(expected_x + x_vec, 0), 4);
            expected_y = Math.min(Math.max(expected_y + y_vec, 0), 4);
            assertEquals(new Vector2d(expected_x, expected_y), testAnimal.getPosition());

        }
        for (int i = 0; i < 6; i++) {
            testAnimal.move(MoveDirection.BACKWARD);
            expected_x = Math.min(Math.max(expected_x - x_vec, 0), 4);
            expected_y = Math.min(Math.max(expected_y - y_vec, 0), 4);
            assertEquals(new Vector2d(expected_x, expected_y), testAnimal.getPosition());
        }
    }

    @Test
    void testMove() {
//      Dla north
        boundsMoveTest(0, 0, 1);
//      Dla East
        boundsMoveTest(1, 1, 0);
//      Dla South
        boundsMoveTest(2, 0, -1);
//      Dla West
        boundsMoveTest(3, -1, 0);
    }

    @Test
    void testParser() {
        OptionsParser parser = new OptionsParser();
        String[] testInput1 = {"f", "v", "a", "b", "r", "l", "lalalala", "trolololo", "forward"};
        MoveDirection[] expectedOutput1 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT,
                MoveDirection.LEFT, MoveDirection.FORWARD};
        assertArrayEquals(expectedOutput1, parser.parse(testInput1).toArray());
        String[] testInput2 = {"f", "b", "l", "r", "forward", "backward", "left", "right"};
        MoveDirection[] expectedOutput2 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT,
                MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
        assertArrayEquals(expectedOutput2, parser.parse(testInput2).toArray());
        String[] testInput3 = {"f","sia", "b","la", "l","la", "r","ba", "forward","fa", "backward","trololo",
                "left","kupol", "right"};
        MoveDirection[] expectedOutput3 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT,
                MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
        assertArrayEquals(expectedOutput3, parser.parse(testInput3).toArray());
    }
}
