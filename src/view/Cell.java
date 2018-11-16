package view;

import controller.BattleShip;
import controller.Observer;
import javafx.application.Platform;
import model.Location;

import javafx.scene.control.Button;

public class Cell extends Button implements Observer<Boolean> {
    private BattleShip controller;
    private Location location;

    public Cell(BattleShip controller, Location location) {
        this.location = location;
        this.controller = controller;
        this.controller.registerTarget(this.location, this);
        this.setOnAction(e -> {
            this.controller.attack(this.location);
        });
    }

    @Override
    public void update(Boolean pushValue) {
        System.out.println(pushValue);
        Platform.runLater(
                () -> {
                    this.setText(pushValue.toString());
                }
        );
    }
}
