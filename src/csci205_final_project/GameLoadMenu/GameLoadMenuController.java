/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 16, 2017
* Time: 3:34:02 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model.GameSaveMenu
* File: GameSaveMenuController
* Description:
*
* ****************************************
 */
package csci205_final_project.GameLoadMenu;

import Util.SaveAndLoadModelUtil;
import csci205_final_project.Game.FinalProjectGameSceneController;
import csci205_final_project.Model.Level;
import csci205_final_project.Model.Model;
import csci205_final_project.Model.Tile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
 */
public class GameLoadMenuController implements Initializable {

    @FXML
    private Button resume;

    private Model save1;
    private Model save2;
    private Model save3;
    private Model theModel;
    @FXML
    private Button loadSlot1;
    @FXML
    private Button loadSlot2;
    @FXML
    private Button loadSlot3;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // read the three save files and see if they are there.
        // if they are, deserialize them and put their information on the buttons.

        try {
            save1 = SaveAndLoadModelUtil.deserializeModel("save1.ser");

            this.loadSlot1.setText(save1.toString());
        } catch (IOException ex) {
            this.loadSlot1.setText("Empty Save Slot");
        } catch (ClassNotFoundException ex) {

        }

        try {
            save2 = SaveAndLoadModelUtil.deserializeModel("save2.ser");

            this.loadSlot2.setText(save2.toString());
        } catch (IOException ex) {

            this.loadSlot2.setText("Empty Save Slot");

        } catch (ClassNotFoundException ex) {

        }
        try {
            save3 = SaveAndLoadModelUtil.deserializeModel("save3.ser");
            this.loadSlot3.setText(save3.toString());
        } catch (IOException ex) {

            this.loadSlot3.setText("Empty Save Slot");
        } catch (ClassNotFoundException ex) {

        }
    }

    @FXML
    private void resumeToGame(ActionEvent event) {
        Stage stage = (Stage) this.resume.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void loadSlot1(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException {

        save1 = SaveAndLoadModelUtil.deserializeModel("save1.ser");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "../Game/finalProjectGameScene.fxml"));

        Parent root;
        BorderPane game = (BorderPane) loader.load();

        FinalProjectGameSceneController gameController = loader.<FinalProjectGameSceneController>getController();

        Stage stage;
        stage = (Stage) loadSlot1.getScene().getWindow();
        gameController.initData(save1.getTheme(), save1.getLevel());
        gameController.setTheModel(save1);
        TilePane tilePane = gameController.getTilePane();
        Level level = gameController.getLevel();
        setupTilePane(tilePane, level);
        drawTiles(level, gameController, tilePane);

        setupLables(gameController);
        Scene scene = new Scene(game);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void loadSlot2(ActionEvent event
    ) throws IOException, FileNotFoundException, ClassNotFoundException {
        save2 = SaveAndLoadModelUtil.deserializeModel("save2.ser");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "../Game/finalProjectGameScene.fxml"));

        Parent root;
        BorderPane game = (BorderPane) loader.load();

        FinalProjectGameSceneController gameController = loader.<FinalProjectGameSceneController>getController();

        Stage stage;
        stage = (Stage) loadSlot1.getScene().getWindow();
        gameController.initData(save1.getTheme(), save1.getLevel());
        gameController.setTheModel(save1);
        TilePane tilePane = gameController.getTilePane();
        Level level = gameController.getLevel();
        setupTilePane(tilePane, level);
        drawTiles(level, gameController, tilePane);

        setupLables(gameController);
        Scene scene = new Scene(game);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void loadSlot3(ActionEvent event
    ) throws IOException, FileNotFoundException, ClassNotFoundException {
        save3 = SaveAndLoadModelUtil.deserializeModel("save3.ser");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "../Game/finalProjectGameScene.fxml"));

        Parent root;
        BorderPane game = (BorderPane) loader.load();

        FinalProjectGameSceneController gameController = loader.<FinalProjectGameSceneController>getController();

        Stage stage;
        stage = (Stage) loadSlot1.getScene().getWindow();
        gameController.initData(save1.getTheme(), save1.getLevel());
        gameController.setTheModel(save1);
        TilePane tilePane = gameController.getTilePane();
        Level level = gameController.getLevel();
        setupTilePane(tilePane, level);
        drawTiles(level, gameController, tilePane);

        setupLables(gameController);
        Scene scene = new Scene(game);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

    public void setupLables(FinalProjectGameSceneController gameController) {
        gameController.getLabelHint().setText(String.valueOf(
                gameController.getTheModel().getHintChance()));
        gameController.getLabelShuffle().setText(String.valueOf(
                gameController.getTheModel().getShuffleChance()));
        gameController.getLabelScore().setText(String.valueOf(
                gameController.getTheModel().getScore()));
    }

    public void setupTilePane(TilePane tilePane, Level level) {
        tilePane.setPrefColumns(level.getWidth());
        tilePane.setPrefRows(level.getWidth());
        tilePane.setPrefWidth(50 * level.getWidth());
        tilePane.setPrefHeight(50 * level.getHeight());
        tilePane.setMaxSize(50 * level.getWidth(), 50 * level.getHeight());
    }

    public void drawTiles(Level level,
                          FinalProjectGameSceneController gameController,
                          TilePane tilePane) {
        for (int i = 1; i < level.getHeight() + 1; i++) {
            ArrayList<Rectangle> row = new ArrayList();
            for (int j = 1; j < level.getWidth() + 1; j++) {
                Tile aTile = gameController.getTheModel().getData().get(i).get(j);
                Rectangle aRectangle = new Rectangle(50, 50);
                aRectangle.setOnMouseClicked((MouseEvent eventB) -> {
                    gameController.selectRectangle(aRectangle, aTile);
                });
                if (aTile != null) {
                    File file = new File(aTile.getImgName());
                    Image img = new Image(file.toURI().toString());
                    aRectangle.setFill(new ImagePattern(img));
                }
                else {
                    aRectangle.setOpacity(0);
                }
                row.add(aRectangle);
                tilePane.getChildren().add(aRectangle);
            }
            gameController.getData().add(row);
        }
    }

}
