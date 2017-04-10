/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 10, 2017
* Time: 2:03:54 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: Row
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

import java.util.ArrayList;

/**
 *
 * @author Iris
 */
public class Row {

    private ArrayList<Tile> row;

    Row(int row, int width) {
        this.row = new ArrayList();
        for (int i = 0; i < width; i++) {
            this.row.add(new Tile());
        }
    }

    public ArrayList<Tile> getRow() {
        return row;
    }
}
