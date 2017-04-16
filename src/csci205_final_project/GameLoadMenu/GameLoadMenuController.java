/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 16, 2017
* Time: 4:14:18 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.GameLoadMenu
* File: GameLoadMenuController
* Description:
*
* ****************************************
 */
package csci205_final_project.GameLoadMenu;

import Util.SaveAndLoadModelUtil;
import csci205_final_project.GameSaveMenu.GameSaveMenuController;
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
public class GameLoadMenuController implements Initializable {

    @FXML
    private Button saveSlot1;
    @FXML
    private Button saveSlot2;
    @FXML
    private Button saveSlot3;
    @FXML
    private Button resume;

    private Model myModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // read saves and change the text on the tiles.
        try {
            Model save1 = SaveAndLoadModelUtil.deserializeModel("save1.ser");

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
            Model save2 = SaveAndLoadModelUtil.deserializeModel("save2.ser");

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
            Model save3 = SaveAndLoadModelUtil.deserializeModel("save3.ser");
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

    public void initModel(Model myModel) {
        this.myModel = myModel;
    }

    @FXML
    private void saveInSlot1(ActionEvent event) {
        try {
            SaveAndLoadModelUtil.serializeModel(myModel, "save1.ser");
            this.saveSlot1.setText(myModel.toString());
        } catch (IOException ex) {
            this.saveSlot1.setText("Failed to save");

        }

    }

    @FXML
    private void saveInSlot2(ActionEvent event) {
        try {
            SaveAndLoadModelUtil.serializeModel(myModel, "save2.ser");
            this.saveSlot2.setText(myModel.toString());
        } catch (IOException ex) {
            this.saveSlot2.setText("Failed to save");
        }
    }

    @FXML
    private void saveInSlot3(ActionEvent event) {
        System.out.println("Hehe");
        try {
            SaveAndLoadModelUtil.serializeModel(myModel, "save3.ser");
            this.saveSlot3.setText(myModel.toString());
        } catch (IOException ex) {
            this.saveSlot3.setText("Failed to save");

        }
    }

    @FXML
    private void resumeToGame(ActionEvent event) {
        Stage stage = (Stage) this.resume.getScene().getWindow();
        stage.close();
    }

}
