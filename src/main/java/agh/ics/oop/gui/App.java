package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class App extends Application {
    AbstractWorldMap map;
    SimulationEngine engine;
    public void init(){
        String[] args = getParameters().getRaw().toArray(new String[0]);
        ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
        this.map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(-3,1) };
        engine = new SimulationEngine(directions, map, positions);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = this.map.getGrid();
        Scene scene = new Scene(gridPane, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
        engine.runAndShow();

    }
}
