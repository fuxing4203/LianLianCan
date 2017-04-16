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
package csci205_final_project.GameSaveMenu;

import Util.SaveAndLoadModelUtil;
import csci205_final_project.Model.Model;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zilin Ma
 */
public class GameSaveMenuController implements Initializable {

    @FXML
    private Button saveSlot1;
    @FXML
    private Button saveSlot2;
    @FXML
    private Button saveSlot3;
    @FXML
    private Button resume;

    private Model save1;
    private Model save2;
    private Model save3;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // read the three save files and see if they are there.
        // if they are, deserialize them and put their information on the buttons.

        try {
            save1 = SaveAndLoadModelUtil.deserializeModel("save1.ser");

            this.saveSlot1.setText(save1.toString());
        } catch (IOException ex) {
            this.saveSlot1.setText("Empty Save Slot");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameSaveMenuController.class.getName()).log(
                    Level.SEVERE,
                    null,
                    ex);
        }

        try {
            save2 = SaveAndLoadModelUtil.deserializeModel("save2.ser");

            this.saveSlot2.setText(save2.toString());
        } catch (IOException ex) {

            this.saveSlot2.setText("Empty Save Slot");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameSaveMenuController.class.getName()).log(
                    Level.SEVERE,
                    null,
                    ex);
        }
        try {
            save3 = SaveAndLoadModelUtil.deserializeModel("save3.ser");
            this.saveSlot3.setText(save3.toString());
        } catch (IOException ex) {

            this.saveSlot3.setText("Empty Save Slot");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameSaveMenuController.class.getName()).log(
                    Level.SEVERE,
                    null,
                    ex);
        }
    }

    @FXML
    private void saveInSlot1(ActionEvent event) {

    }

    @FXML
    private void saveInSlot2(ActionEvent event) {
    }

    @FXML
    private void saveInSlot3(ActionEvent event) {
    }

    @FXML
    private void resumeToGame(ActionEvent event) {
        Stage stage = (Stage) this.resume.getScene().getWindow();
        stage.close();
    }

}
