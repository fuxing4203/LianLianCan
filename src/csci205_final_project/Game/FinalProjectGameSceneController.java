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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
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
    private Pane tilePane;

    private Model theModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        theModel = new Model(Level.EASY);
        double height = tilePane.getHeight();
        double width = tilePane.getWidth();
        ArrayList<ArrayList<Tile>> drawTile = theModel.getData();
        double perHeight = height / 8;
        double perWidth = width / 8;

        int i, j;
        for (i = 1; i < 7; i++) {
            for (j = 1; j < 7; j++) {
                Tile aTile = drawTile.get(i).get(j);
                aTile.setX(perWidth * j);
                aTile.setY(perHeight * i);
                Image img = new Image(aTile.getImgName());
                aTile.setFill(new ImagePattern(img));
                tilePane.getChildren().add(aTile);
            }
        }

    }

    @FXML
    private void btnPause(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "../PauseMenu/finalProjectPauseMenu.fxml"));
        VBox pause = (VBox) loader.load();
        Scene scene = new Scene(pause);
        Stage stage;
        stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void btnShuffle(ActionEvent event) {
        // theModel = new Model();
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

}
