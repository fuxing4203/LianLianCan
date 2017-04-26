/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 25, 2017
* Time: 11:19:03 PM
*
* Project: csci205_final_project
* Package: Records
* File: RecordsController
* Description:
*
* ****************************************
 */
package csci205_final_project.Records;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hs031
 */
public class RecordsController implements Initializable {

    @FXML
    private Label first;
    @FXML
    private Label second;
    @FXML
    private Label third;
    @FXML
    private Button btnReturn;
    @FXML
    private ImageView challenger;
    @FXML
    private ImageView diamond;
    @FXML
    private ImageView gold;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        File file = new File("1.png");
        Image img = new Image(file.toURI().toString());
        challenger.setImage(img);

        file = new File("2.png");
        img = new Image(file.toURI().toString());
        diamond.setImage(img);

        file = new File("3.png");
        img = new Image(file.toURI().toString());
        gold.setImage(img);

        ArrayList<Label> records = new ArrayList<Label>();
        records.add(first);
        records.add(second);
        records.add(third);
        String content;

        File recordsFile = new File("Records.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(recordsFile));
        } catch (FileNotFoundException ex) {
        }

        int i = 0;
        try {
            while ((content = br.readLine()) != null) {
                records.get(i).setText(content);
                i++;
            }
        } catch (IOException ex) {
        }
    }

    @FXML
    private void returnToMenu(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnReturn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "../Menu/finalProjectMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

}
