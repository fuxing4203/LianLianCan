/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 10, 2017
* Time: 1:44:16 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: Model
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Iris
 */
public class Model implements Serializable {

    private Level level;
    private final int turn = 2;
    protected static ArrayList<ArrayList<Tile>> data;
    private int totalSize;
    private int shuffleChance = 2;

    Model(Level level) {
        this.level = level;
        this.totalSize = this.level.getHeight() * this.level.getWidth();
        data = new ArrayList();
        for (int i = 0; i < this.level.getHeight(); i++) {
            ArrayList<Tile> row = new ArrayList();
            for (int j = 0; j < this.level.getWidth(); j++) {
                row.add(new Tile(j, i, "a"));
            }
            data.add(row);
        }
    }

    public boolean cancelTile(Tile a, Tile b) {
        if (!a.equals(b)) {
            //System.out.println("Not Equal!");
            return false;
        }
        else if ((data.get(a.getPosY()).get(a.getPosX()) == null) || (data.get(
                                                                      b.getPosY()).get(
                                                                      b.getPosX()) == null)) {
            return false;
        }
        else {
            int numTurn = 0;
            if (checkPath(a.getPosX(), a.getPosY(), b.getPosX(), b.getPosY(),
                          numTurn)) {
                data.get(a.getPosY()).set(a.getPosX(), null);
                data.get(b.getPosY()).set(b.getPosX(), null);
                this.totalSize -= 2;
                return true;
            }
            return false;
        }
    }

    public boolean checkPath(int ax, int ay, int bx, int by, int numTurn) {
        System.out.println(ax + " " + ay + " " + bx + " " + by);
        if ((ax == bx) && (ay == by)) {
            return false;
        }
        else if (numTurn > this.turn) {
            System.out.println("Exceeds max turn");
            return false;
        }
        else {
            InLine row = new InLine(ax, ay);
            InLine col = new InLine(ax, ay);
            if (row.checkRow(ax, ay, bx, by)) {
                return true;
            }
            if (col.checkCol(ax, ay, bx, by)) {
                return true;
            }
            System.out.println("row: " + row.getX() + " " + row.getY());
            System.out.println("col: " + col.getX() + " " + col.getY());
            return checkPath(row.getX(), row.getY(), bx, by, numTurn + 1) || checkPath(
                    col.getX(), col.getY(), bx, by, numTurn + 1);
        }
    }
}
