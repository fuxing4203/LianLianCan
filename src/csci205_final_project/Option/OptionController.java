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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iris
 */
public class OptionController implements Initializable {

    ObservableList<String> themeList = FXCollections.observableArrayList(
            "pokeman", "professor", "ragecomics");
    ObservableList<String> levelList = FXCollections.observableArrayList(
            "Easy", "Medium", "Hard");
    private static String theme;
    private static String level;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Button returnBtn;
    @FXML
    private ComboBox themeBox;
    @FXML
    private ComboBox levelBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        themeBox.setValue("pokeman");
        themeBox.setItems(themeList);

        levelBox.setValue("Easy");
        levelBox.setItems(levelList);
    }

    @FXML
    private void resume(ActionEvent event) throws IOException {
        theme = (String) themeBox.getValue();
        level = (String) levelBox.getValue();
        Stage stage;
        Parent root;
        stage = (Stage) returnBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "../Menu/finalProjectMenu.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static String getTheme() {
        return theme;
    }

    public static String getLevel() {
        return level;
    }

}
