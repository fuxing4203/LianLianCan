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
 * FXML Controller class for pause menu
 *
 * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
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

    private Thread myThread;
    private Model theModel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * initialize the data
     *
     * @param t
     * @param theModel
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public void initData(Thread t, Model theModel) {
        myThread = t;

        this.theModel = theModel;

    }

    @FXML
    private void resumeGame(ActionEvent event) {
        Stage stage = (Stage) this.resumeBtn.getScene().getWindow();
        //TODO
        myThread.resume();
        stage.close();

    }

    @FXML
    private void saveGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "../GameSaveMenu/GameSaveMenu.fxml"));
        Pane save = (Pane) loader.load();
        GameSaveMenuController gameSave = loader.<GameSaveMenuController>getController();
        gameSave.initModel(theModel);
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

        Scene scene = new Scene(load);
        Stage stage;
        stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

}
