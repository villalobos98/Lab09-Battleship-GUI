package view;

import controller.ConsoleWriter;
import javafx.scene.control.Label;

public class BattleShipWriter extends Label implements ConsoleWriter {


   public void write (String text) {
        javafx.application.Platform.runLater(
                () -> this.setText (text));
   }
}