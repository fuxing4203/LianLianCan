/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 10, 2017
* Time: 1:34:05 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.PauseMenu
* File: FinalProjectPauseMenuController
* Description:
*
* ****************************************
 */
package csci205_final_project.PauseMenu;

import csci205_final_project.GameLoadMenu.GameLoadMenuController;
import csci205_final_project.GameSaveMenu.GameSaveMenuController;
import csci205_final_project.Model.Model;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jj030
 */
public class FinalProjectPauseMenuController implements Initializable {

    @FXML
    private VBox pauseVBox;
    @FXML
    private Button resumeBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button loadBtn;

    private Model theModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Model theModel) {
        this.theModel = theModel;

    }

    @FXML
    private void resumeGame(ActionEvent event) {
        Stage stage = (Stage) this.resumeBtn.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void saveGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "../GameSaveMenu/GameSaveMenu.fxml"));
        Pane save = (Pane) loader.load();
        GameSaveMenuController gameSave = loader.<GameSaveMenuController>getController();
        Scene scene = new Scene(save);
        Stage stage;
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void loadGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "../GameLoadMenu/GameLoadMenu.fxml"));
        Pane load = (Pane) loader.load();
        GameLoadMenuController gameLoad = loader.<GameLoadMenuController>getController();
        gameLoad.initModel(theModel);
        Scene scene = new Scene(load);
        Stage stage;
        stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

}
