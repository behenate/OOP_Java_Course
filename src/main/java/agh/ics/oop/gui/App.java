package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application {
    AbstractWorldMap map;
    SimulationEngine engine;
    public void init(){
        String[] args = getParameters().getRaw().toArray(new String[0]);
        ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
        this.map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(-3,1) };
        this.engine = new SimulationEngine(directions, map, positions);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = this.map.generateGrid();
        Scene scene = new Scene(gridPane, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
        engine.run();
    }
}
