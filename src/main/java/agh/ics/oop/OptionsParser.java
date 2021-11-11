package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;

public class OptionsParser {
    public ArrayList<MoveDirection> parse(String[] options){
        ArrayList<MoveDirection> result = new ArrayList<>();
        ArrayList<String> allowed = new ArrayList<>(Arrays.asList("f", "forward","b","backward","r","right","l","left"));
        for (String word: options) {
            if (!allowed.contains(word)){
                continue;
            }
            char letter = word.charAt(0);
            switch (letter){
                case 'f'-> result.add(MoveDirection.FORWARD);
                case 'b'->result.add(MoveDirection.BACKWARD);
                case 'r'->result.add(MoveDirection.RIGHT);
                case 'l'->result.add(MoveDirection.LEFT);
            }
        }
        return result;
    }
}
