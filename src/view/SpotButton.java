package view;
/**
 * @author Isaias Villalobos
 * @date 4/10/18
 * @language java 9
 */

import controller.BattleShip;
import controller.Observer;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Location;

public class SpotButton extends Button implements Observer<Boolean> {
    private int row;
    private int col;
    private BattleShip controller;
    private SpotButton button;

    /**
     * The constructor which creates field such as row, column, controller, and button.
     * The controller takes account of what location the target is hit.
     * @param row Integer
     * @param col Integer
     * @param controller A battleship
     */
    public SpotButton(int row, int col, BattleShip controller) {

        this.row = row;
        this.col = col;
        this.controller = controller;
        controller.registerTarget(new Location(row, col), this);
        button = this;
    }

    /**
     * When this observer observes that a boolean is changed then it update the circle
     * graphic color, to either red for hit or white for miss.
     * @param pushValue The value that will be pushed to the observer
     */
    @Override
    public void update(Boolean pushValue) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (pushValue) {
                    Circle circle = new Circle();
                    circle.setRadius(5);
                    circle.setFill(Color.RED);
                    button.setGraphic(circle);
                } else {
                    Circle circle = new Circle();
                    circle.setRadius(5);
                    circle.setFill(Color.WHITE);
                    button.setGraphic(circle);
                }
            }
        });
    }

    /**
     * @return the column
     */
    public int getCol() {
        return col;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }
}
