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

import Util.AudioUtil;
import csci205_final_project.Model.*;
import csci205_final_project.PauseMenu.FinalProjectPauseMenuController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
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
    @FXML
    private TilePane tilePane;
    @FXML
    private BorderPane parentPane;
    @FXML
    private ProgressBar timeBar;
    @FXML
    private Button btnExit;

    private Level level;
    private Model theModel;
    private Thread th;
    public Tile selectedTile;
    public Rectangle selectedRectangle;
    public int numOfSelections = 0;
    private String theme;
    private ArrayList<ArrayList<Rectangle>> data;
    private int levelNum = 0;
    private boolean gg;
    private ArrayList<ArrayList<Integer>> blackTiles = null;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AudioUtil.playMusic("music.wav");
        beginTimer();

    }

    /**
     * a method used to begin a timer
     *
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public void beginTimer() {
        // start timer
        gg = false;
        Task<Void> task;
        task = new Task<Void>() {
            @Override
            public Void call() {
                // 2 minutes
                for (int i = 0; i < 120; i++) {
                    if (blackTiles != null) {
                        drawLine(blackTiles, 0);
                    }
                    try {
                        th.sleep(1000);
                    } catch (InterruptedException e) {
                        gg = true;
                        break;
                    }
                    updateProgress(i + 1, 120);
                }
                return null;
            }

        };

        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                if (!gg) {
                    gg = true;
                }
                else {
                    gg = false;
                }
                if (gg) {
                    tilePane.getChildren().clear();
                    Rectangle gameOver = new Rectangle();
                    gameOver.setWidth(1000);
                    gameOver.setHeight(600);
                    File file = new File("GG.jpg");
                    Image img = new Image(file.toURI().toString());
                    gameOver.setFill(new ImagePattern(img));
                    tilePane.getChildren().add(gameOver);
                }
            }
        });
        timeBar.progressProperty().bind(task.progressProperty());
        th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    /**
     * Initialize the model
     *
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public void createModel() {
        theModel = new Model(level, theme);
        startGameBoardWithMode(theModel.getLevel());
        labelScore.setText(String.format("%d", theModel.getScore()));
        labelHint.setText(String.format("%d", theModel.getHintChance()));
        labelShuffle.setText(String.format("%d", theModel.getShuffleChance()));
        labelLevel.setText(String.format("%d", levelNum));
    }

    /**
     * Load saved model
     *
     * @param model
     */
    public void loadModel(Model model) {
        startGameBoardWithMode(model.getLevel());
        labelScore.setText(String.format("%d", theModel.getScore()));
        labelHint.setText(String.format("%d", theModel.getHintChance()));
        labelShuffle.setText(String.format("%d", theModel.getShuffleChance()));
        labelLevel.setText(String.format("%d", levelNum));
    }

    /**
     * Update theme and level
     *
     * @param themeString
     * @param levelString
     */
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

    /**
     * Update theme and level
     *
     * @param themeString
     * @param level
     */
    public void initData(String themeString, Level level) {
        this.theme = themeString;
        this.level = level;
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
        if (!gg) {
            theModel.shuffle();
            Level level = theModel.getLevel();
            tilePane.getChildren().clear();
            startGameBoardWithMode(level);
            labelShuffle.setText(
                    String.format("%d", theModel.getShuffleChance()));
        }
    }

    @FXML
    private void btnHint(ActionEvent event) {
        if (!gg) {
            ArrayList<Tile> result = theModel.hint();
            if (result != null) {
                Tile a = result.get(0);
                Tile b = result.get(1);
                data.get(a.getPosY()).get(a.getPosX()).setOpacity(0.3);
                data.get(b.getPosY()).get(b.getPosX()).setOpacity(0.3);
            }
            else {
                this.btnShuffle(event);
            }
            labelHint.setText(String.format("%d", theModel.getHintChance()));
        }
    }

    @FXML
    private void labelScore(MouseEvent event) {
    }

    @FXML
    private void labelLevel(MouseEvent event) {
    }

    /**
     * Initialize game with a level. Have to initialize game first.
     *
     * @param level
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public void startGameBoardWithMode(Level level) {
        tilePane.setPrefColumns(level.getWidth() + 2);
        tilePane.setPrefRows(level.getHeight() + 2);
        tilePane.setPrefWidth(50 * (level.getWidth() + 2));
        tilePane.setPrefHeight(50 * (level.getHeight() + 2));
        tilePane.setMaxSize(50 * (level.getWidth() + 2),
                            50 * (level.getHeight() + 2));
        data = new ArrayList();
        for (int i = 0; i < level.getHeight() + 2; i++) {
            ArrayList<Rectangle> row = new ArrayList();
            for (int j = 0; j < level.getWidth() + 2; j++) {
                Tile aTile = theModel.getData().get(i).get(j);
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
                row.add(aRectangle);
            }
            data.add(row);
        }
    }

    /**
     * a method to select a rectangle and link that to the tile in the
     * background
     *
     * @param aRectangle
     * @param aTile
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
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
            //playMusic("audioeff/blomark.wav");
        }
        else if (numOfSelections % 2 == 1) {

            selectedRectangle.setOpacity(1); // set the opacity back in case there is no path between the current one and the next one.

            blackTiles = theModel.findPath(
                    selectedTile, aTile);
            if (blackTiles != null) {
                theModel.removeTile(selectedTile, aTile);
                // make the tiles invisible.
                selectedRectangle.setFill(Color.BLACK);
                aRectangle.setFill(Color.BLACK);
                drawLine(blackTiles, 1);
                labelScore.setText(String.format("%d", theModel.getScore()));
                if (theModel.getTotalSize() == 0) {
                    levelNum += 1;
                    theModel.setLevel(Level.updateLevel(theModel.getLevel()));
                    tilePane.getChildren().clear();
                    startGameBoardWithMode(theModel.getLevel());
                    labelLevel.setText(String.format("%d", levelNum));
                    blackTiles = null;
                    th.interrupt();
                    beginTimer();
                }
            }
            else { // if there is no path between them, change the next selected tile as selected.
                selectedRectangle.setOpacity(1);
                selectedRectangle = aRectangle;
                selectedTile = aTile;
                selectedRectangle.setOpacity(1);
                labelScore.setText(String.format("%d", theModel.getScore()));

                //playMusic("audioeff/blomark.wav");
            }

            numOfSelections++;
        }
    }

    /**
     * Show or hide the tiles on the path
     *
     * @param coordinates - turning points of the path stored in groups (x, y)
     * @param opacity - a double from 0 to 1
     * @author Iris Fu
     */
    private void drawLine(ArrayList<ArrayList<Integer>> coordinates,
                          double opacity) {
        for (int i = 0; i < coordinates.size() - 1; i++) {
            if (coordinates.get(i).get(1) < coordinates.get(i + 1).get(1)) {
                for (int j = coordinates.get(i).get(1); j <= coordinates.get(
                     i + 1).get(1); j++) {
                    data.get(j).get(coordinates.get(i).get(0)).setOpacity(
                            opacity);
                }
            }
            else if (coordinates.get(i).get(1) > coordinates.get(i + 1).get(1)) {
                for (int j = coordinates.get(i + 1).get(1); j <= coordinates.get(
                     i).get(1); j++) {
                    data.get(j).get(coordinates.get(i).get(0)).setOpacity(
                            opacity);
                }
            }
            else if (coordinates.get(i).get(0) < coordinates.get(
                    i + 1).get(0)) {
                for (int j = coordinates.get(i).get(0); j <= coordinates.get(
                     i + 1).get(0); j++) {
                    data.get(coordinates.get(i).get(1)).get(j).setOpacity(
                            opacity);
                }
            }
            else {
                for (int j = coordinates.get(i + 1).get(0); j <= coordinates.get(
                     i).get(0); j++) {
                    data.get(coordinates.get(i).get(1)).get(j).setOpacity(
                            opacity);
                }
            }
        }
    }

    @FXML
    private void btnExit(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnExit.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "../Menu/finalProjectMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
