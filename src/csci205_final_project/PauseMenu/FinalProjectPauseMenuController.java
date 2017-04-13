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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void resumeGame(ActionEvent event) {
        Stage stage = (Stage) this.resumeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveGame(ActionEvent event) {
    }

    @FXML
    private void loadGame(ActionEvent event) {
    }

}
