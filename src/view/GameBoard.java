package view;
/**
 * @author Isaias Villalobos
 * @date 4/10/18
 * @language java 9
 */

import controller.BattleShip;
import controller.Observer;
import controller.ShipData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Location;
import model.ShipModel;

import java.util.List;

/**
 * This is a gameboard class which extends application and is also an observer of shipData. Several methods are called to make
 * a proper gameboard.
 * The game is made up of a Grid Pane and Player pane, one displays where the button can be clicked and the other shows the
 * ships that can be hit.
 */


public class GameBoard extends Application implements Observer<ShipData> {
    private BattleShipWriter console;
    private BattleShip controller;
    private Label status;
    private static final double BUTTON_PADDING = 1.5;
    private GridPane grid;
    private boolean[][] ships;
    private Button[][] buttons;
    private GridPane playerPane;

    /**
     * initializes the variables that will be used to make the board.
     * Calls the right constructor when given the correct number of arguments.
     */
    public void init() {

        this.console = new BattleShipWriter();
        Parameters params = getParameters();
        List<String> args = params.getRaw();
        this.buttons = new Button[10][10];
        this.ships = new boolean[10][10];

        this.playerPane = makePlayerPane();

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                ships[i][j] = false;
            }

        }

        if (args.size() == 2) {
            this.controller = new BattleShip(console, args.get(0), Integer.parseInt(args.get(1)));
        } else if (args.size() == 1) {
            this.controller = new BattleShip(console, Integer.parseInt(args.get(0)));
        } else {
            this.controller = new BattleShip(console);
        }
        this.status = new Label();
        this.controller.addShips(this);


    }

    /**
     * Makes a GridPane that when clicked hits a specific location on the controller.
     * @return a GridPane
     */
    private GridPane makeGridPane() {

        grid = new GridPane();
        grid.setStyle("-fx-background-color: blue;");
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);
        int row;
        int col;
        for (row = 0; row <= 9; ++row) {
            for (col = 0; col <= 9; col++) {
                SpotButton bt = new SpotButton(row, col, controller);
                bt.setStyle("-fx-background-color: #33E0FF;");
                bt.setMinSize(20, 20);
                bt.setMaxSize(20, 20);
                bt.setOnAction(e -> {
                    int COLUMN = bt.getCol();
                    int ROW = bt.getRow();

                    controller.attack(new Location(ROW, COLUMN));

                });
                grid.add(bt, col, row);
            }
        }
        col = 1;
        col++;

        return grid;
    }

    /**
     * Given a stage, adds teh GridPane and PlayerPane to the scene which is a vBox.
     * @param stage A stage
     */
    public void start(Stage stage) {
        VBox v = new VBox();
        stage.setScene(new Scene(v));
        v.getChildren().addAll(makeGridPane(), console, playerPane);
        stage.show();
        makeGridPane();
        stage.setTitle("Battleship");
    }

    /**
     * Make a new grid array of buttons that are assigned buttons.
     * @return a GridPane
     */
    private GridPane makePlayerPane() {
        grid = new GridPane();
        grid.setStyle("-fx-background-color: blue;");
        grid.setHgap(BUTTON_PADDING); //horizontal gap in pixels => that's what you are asking for
        grid.setVgap(BUTTON_PADDING);
        int row;
        int col;
        for (row = 0; row <= 9; ++row) {
            for (col = 0; col <= 9; col++) {
                Button bt = new Button();
                bt.setMinSize(20, 20);
                bt.setMaxSize(20, 20);
                buttons[row][col] = bt;
                bt.setStyle("-fx-background-color: #33E0FF;");

                grid.add(bt, col, row);
            }
        }
        col = 1;
        col++;

        return grid;

    }

    /**
     * Updates the shipModel
     * @param pushValue The value that will be pushed to the observer
     */
    @Override
    public void update(ShipData pushValue) {
        ShipModel shipModel = (ShipModel) pushValue;
        new ShipView(shipModel, buttons);
    }
}
