package view;
/**
 * @author Isaias Villalobos
 * @date 4/10/18
 * @language java 9
 */
import controller.Observer;
import controller.ShipData;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import model.ShipModel;

import java.util.ArrayList;

public class ShipView implements Observer<Integer> {
    private ArrayList<Button> listLabels;
    private ShipModel model;

    /**
     * This method registers the shipView to the model. Gets the row and col and
     * puts the ship to the aerialist of labels.
     * @param model the ship
     * @param Label the label
     */
    public ShipView(ShipModel model, Button[][] Label) {
        listLabels = new ArrayList<>();
        this.model = model;
        model.register(this);
        int ROW = model.getBowLocation().getRow();
        int COL = model.getBowLocation().getCol();

        for (int i = 0; i < model.getSize(); i++) {
            listLabels.add(Label[ROW][COL]);
            Label[ROW][COL].setStyle("-fx-background-color: grey;");
            if (model.getOrientation().equals(ShipData.Orientation.VERTICAL)) {
                ROW++;
            } else {
                COL++;
            }
        }

    }

    /**
     * The update that will make a circle when pushValue gets changed.
     * @param pushValue The value that will be pushed to the observer
     */
    @Override
    public void update(Integer pushValue) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Circle c = new Circle();
                c.setRadius(5);
                listLabels.get(pushValue).setGraphic(c);

            }
        });
    }
}
