/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 9, 2017
* Time: 9:47:41 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Menu
* File: startMenu
* Description:
*
* ****************************************
 */
package csci205_final_project.Menu;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * start menu class
 *
 * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
 */
public class startMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Class thisClass = startMenu.class;
        InputStream in = thisClass.getResourceAsStream(
                "finalProjectMenu.fxml");
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(thisClass.getResource(
                "finalProjectMenu.fxml"));

        Parent root = loader.load(in);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * main class
     *
     * @param args the command line arguments
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public static void main(String[] args) {
        launch(args);
    }

}
