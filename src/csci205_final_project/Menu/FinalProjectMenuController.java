/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 9, 2017
* Time: 8:59:49 PM
*
* Project: csci205_final_project
* Package: csci205_final_project
* File: FinalProjectMenuController
* Description:
*
* ****************************************
 */
package csci205_final_project.Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hs031
 */
public class FinalProjectMenuController implements Initializable {

    @FXML
    private Button btnStart;
    @FXML
    private Button btnLoad;
    @FXML
    private Button btnTutorial;
    @FXML
    private Button btnRecords;
    @FXML
    private Button btnExit;
    @FXML
    private VBox background;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Image woodenTexture = new Image(getClass().getResourceAsStream(
        //        "../images/wooden.jpg"));
        //btnStart.setGraphic(new ImageView(woodenTexture));
    }

    @FXML
    void startNewGame() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnStart.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "../Option/option.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loadGame() {

    }

    @FXML
    void openTutorial() {

    }

    void openOptions() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnStart.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "../Option/option.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openRecords() {

    }

    @FXML
    void Exit() {
        Platform.exit();
    }

}
