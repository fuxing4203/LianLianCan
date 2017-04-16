/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 16, 2017
* Time: 2:53:57 PM
*
* Project: csci205_final_project
* Package: Util
* File: SaveAndLoadModelUtil
* Description:
*
* ****************************************
 */
package Util;

import csci205_final_project.Model.Model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Zilin Ma
 */
public class SaveAndLoadModelUtil {

    /**
     * Deserializes a file. fileName is the name of the .ser file. Returns the
     * Model object that is deserialized.
     *
     * @param fileName
     * @return the deserialized Model
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     * @see
     * <a href="https://www.tutorialspoint.com/java/java_serialization.html">https://www.tutorialspoint.com/java/java_serialization.html</a>
     *
     */
    public static Model deserializeModel(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        Model myModel = null;
        FileInputStream fileIn;
        fileIn = new FileInputStream(fileName);
        try (final ObjectInputStream in = new ObjectInputStream(fileIn)) {
            myModel = (Model) in.readObject();
        }
        fileIn.close();
        return myModel;
    }

    /**
     * Serializes the ANN object to a file. myANN is the object being
     * serialized, and fileName is the .ser filename.
     *
     * @param myModel
     * @param dirName the directory of name.
     * @throws java.io.FileNotFoundException
     * @see
     * <a href="https://www.tutorialspoint.com/java/java_serialization.htm">https://www.tutorialspoint.com/java/java_serialization.htm</a>
     */
    public static void serializeModel(Model myModel, String dirName) throws FileNotFoundException, IOException {
        //try {
        dirName += ".ser";
        FileOutputStream fileOut = new FileOutputStream(dirName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(myModel);
        out.close();
        fileOut.close();
        System.out.printf("Serialized data is saved in " + dirName + "\n");
        //} catch (IOException i) {
        //}
    }

}
