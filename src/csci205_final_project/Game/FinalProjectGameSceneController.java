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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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

    private Model theModel;
    @FXML
    private TilePane tilePane;
    @FXML
    private BorderPane parentPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        theModel = new Model(Level.EASY, tilePane);
        theModel.generateGameWithMode(tilePane, Level.EASY);

    }

    private ArrayList<String> imgNameProducer(String theme) {
        ArrayList<String> sResult = new ArrayList<String>();
        ArrayList<Integer> iResult = new ArrayList<Integer>();
        int width = theModel.getLevel().getWidth();
        int height = theModel.getLevel().getHeight();
        int numTiles = width * height;
        int numImgs = numTiles / 4;
        Random rnd = new Random();
        int n;
        for (int i = 0; i < numImgs; i++) {
            while (true) {
                n = rnd.nextInt(numImgs);
                if (numContained(iResult, n) < 4) {
                    iResult.add(n);
                    sResult.add(theme + "/" + n);
                    break;
                }
            }
        }
        return sResult;
    }

    private int numContained(ArrayList<Integer> input, int x) {
        int i;
        int result = 0;
        for (i = 0; i < input.size(); i++) {
            if (input.get(i) == x) {
                result += 1;
            }
        }
        return result;
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
