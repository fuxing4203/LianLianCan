/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 9, 2017
* Time: 9:39:26 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Menu
* File: FinalProjectGameSceneController
* Description:
*
* ****************************************
 */
package csci205_final_project.Game;

import csci205_final_project.Model.*;
import csci205_final_project.PauseMenu.FinalProjectPauseMenuController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jj030
 */
public class FinalProjectGameSceneController implements Initializable {

    @FXML
    private Button btnPause;
    @FXML
    private Button btnShuffle;
    @FXML
    private Button btnHint;
    @FXML
    private Label labelScore;
    @FXML
    private Label labelLevel;
    @FXML
    private Label labelShuffle;
    @FXML
    private Label labelHint;

    private Level level;
    private Model theModel;
    private int seconds;
    private Thread th;
    @FXML
    private TilePane tilePane;
    @FXML
    private BorderPane parentPane;
    @FXML
    private ProgressBar timeBar;
    public Tile selectedTile;
    public Rectangle selectedRectangle;
    public int numOfSelections = 0;
    private String theme;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // start timer
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
                for (int i = 0; i < 400; i++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.interrupted();
                        break;
                    }
                    updateProgress(i + 1, 400);
                }
                return null;
            }
        };

        timeBar.progressProperty().bind(task.progressProperty());
        th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }

    public void createModel() {
        theModel = new Model(level, theme);
        startGameBoardWithMode(theModel.getLevel());
    }

    public void initData(String themeString, String levelString) {
        this.theme = themeString;
        if (levelString.equals("Easy")) {
            level = Level.EASY;
        }
        else if (levelString.equals("Medium")) {
            level = Level.MEDIUM;
        }
        else if (levelString.equals("Hard")) {
            level = Level.HARD;
        }

    }

    @FXML
    private void btnPause(ActionEvent event) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "../PauseMenu/finalProjectPauseMenu.fxml"));

        VBox pause = (VBox) loader.load();
        FinalProjectPauseMenuController finalProjectPauseMenuController = loader.<FinalProjectPauseMenuController>getController();
        finalProjectPauseMenuController.initData(th, theModel);
        Scene scene = new Scene(pause);
        Stage stage;
        stage = new Stage();
        stage.setScene(scene);

        stage.show();

        //TODO
        th.suspend();

    }

    @FXML
    private void btnShuffle(ActionEvent event) {
        theModel.shuffle();
        Level level = theModel.getLevel();
        tilePane.getChildren().clear();
        for (int i = 1; i < level.getWidth() + 1; i++) {
            for (int j = 1; j < level.getHeight() + 1; j++) {
                Tile aTile = theModel.getData().get(j).get(i);
                Rectangle aRectangle = new Rectangle(50, 50);
                aRectangle.setOnMouseClicked((MouseEvent eventB) -> {
                    selectRectangle(aRectangle, aTile);
                });
                if (aTile != null) {
                    File file = new File(aTile.getImgName());
                    Image img = new Image(file.toURI().toString());
                    aRectangle.setFill(new ImagePattern(img));
                }
                else {
                    aRectangle.setOpacity(0);
                }
                tilePane.getChildren().add(aRectangle);
            }
        }
        labelShuffle.setText(String.format("%d", theModel.getShuffleChance()));
    }

    @FXML
    private void btnHint(ActionEvent event) {
    }

    @FXML
    private void labelScore(MouseEvent event) {
    }

    @FXML
    private void labelLevel(MouseEvent event) {
    }

    /*
        Initialize game with a level. Have to initialize game first.
     */
    public void startGameBoardWithMode(Level level) {
        tilePane.setPrefColumns(level.getWidth());
        tilePane.setPrefRows(level.getWidth());
        tilePane.setPrefWidth(50 * level.getWidth());
        tilePane.setPrefHeight(50 * level.getHeight());
        tilePane.setMaxSize(50 * level.getWidth(), 50 * level.getHeight());
        for (int i = 1; i < level.getWidth() + 1; i++) {
            for (int j = 1; j < level.getHeight() + 1; j++) {
                Tile aTile = theModel.getData().get(j).get(i);
                File file = new File(aTile.getImgName());
                Image img = new Image(file.toURI().toString());
                Rectangle aRectangle = new Rectangle(50, 50);
                aRectangle.setFill(new ImagePattern(img));
                aRectangle.setOnMouseClicked((MouseEvent event) -> {
                    selectRectangle(aRectangle, aTile);
                });
                tilePane.getChildren().add(aRectangle);
            }
        }
    }

    /*

     */
    private void selectRectangle(Rectangle aRectangle, Tile aTile) {
        //System.out.println(aTile.getPosX());
        //System.out.println(aTile.getPosY());
        if (aRectangle.getOpacity() == 0) {
            return;
        }

        if (numOfSelections % 2 == 0) {
            selectedTile = aTile;
            selectedRectangle = aRectangle;
            selectedRectangle.setOpacity(0.5); // set the opacity to show that this rectangle is selected.
            numOfSelections++;
        }
        else if (numOfSelections % 2 == 1) {

            selectedRectangle.setOpacity(1); // set the opacity back in case there is no path between the current one and the next one.

            boolean isPath = theModel.isTileCancelable(selectedTile, aTile);
            if (isPath) {
                theModel.removeTile(selectedTile, aTile);
                // make the tiles invisible.
                selectedRectangle.setOpacity(0);
                aRectangle.setOpacity(0);
            }
            else { // if there is no path between them, change the next selected tile as selected.
                selectedRectangle.setOpacity(1);
                selectedRectangle = aRectangle;
                selectedTile = aTile;
                selectedRectangle.setOpacity(0.5);
            }

            numOfSelections++;
        }
    }

}
