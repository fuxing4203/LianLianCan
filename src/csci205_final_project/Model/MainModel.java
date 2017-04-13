/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 12, 2017
* Time: 9:10:59 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: MainModel
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

import static csci205_final_project.Model.Level.EASY;

/**
 *
 * @author Iris
 */
public class MainModel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Model md = new Model(EASY);
        md.data.get(3).get(2).setImgName("b");
        md.data.get(4).get(4).setImgName("b");
        for (int i = 2; i < 4; i++) {
            md.data.get(i).set(4, null);
        }
        /*
        for (int i = 0; i < md.data.size(); i++) {
            String s = "";
            for (int j = 0; j < md.data.get(0).size(); j++) {
                s += md.data.get(i).get(j) + " ";
            }
            System.out.println(s);
        }*/
        md.data.get(1).set(3, null);
        md.data.get(1).set(4, null);
        md.data.get(1).set(2, null);
        md.data.get(2).set(2, null);
        boolean result = md.cancelTile(md.data.get(3).get(2),
                                       md.data.get(4).get(4));
        System.out.println(result);
    }

}
