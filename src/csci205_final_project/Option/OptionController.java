/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 9, 2017
* Time: 9:31:13 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Option
* File: OptionController
* Description:
*
* ****************************************
 */
package csci205_final_project.Option;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;

/**
 * FXML Controller class
 *
 * @author Iris
 */
public class OptionController implements Initializable {

    @FXML
    private Slider volumeSlider;
    @FXML
    private ComboBox<?> dropdownComboBox;
    @FXML
    private Button returnBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
