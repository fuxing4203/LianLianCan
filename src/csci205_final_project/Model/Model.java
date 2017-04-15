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
import java.util.Collections;

/**
 *
 * @author Iris
 */
public class Model implements Serializable {

    private Level level; // determines the dimension of the board
    private final int turn = 3; // maximum number of turns for the inner tiles to be cancelled

    /**
     *
     */
    protected static ArrayList<ArrayList<Tile>> data; // stores the tiles
    private int totalSize = 0; // records the total number of tiles
    private int shuffleChance = 2; // records the number of chances for shuffle

    public Model(Level level) {
        this.level = level;
        this.totalSize = this.level.getHeight() * this.level.getWidth();
        data = new ArrayList();
        for (int i = 0; i < this.level.getHeight(); i++) {
            ArrayList<Tile> row = new ArrayList();
            for (int j = 0; j < this.level.getWidth(); j++) {
                row.add(new Tile(j, i, "a"));
                this.totalSize += 1;
            }
            data.add(row);
        }
    }

    /**
     * Check if two tiles can be canceled. If it's possible, cancel tiles and
     * return true.
     *
     * @param a - a tile
     * @param b - another tile
     * @return boolean
     */
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
            if (a.getPosX() == 0 && b.getPosX() == 0) {
                data.get(a.getPosY()).set(a.getPosX(), null);
                data.get(b.getPosY()).set(b.getPosX(), null);
                this.totalSize -= 2;
                return true;
            }
            else if (a.getPosX() == Model.data.size() && b.getPosX() == Model.data.size()) {
                data.get(a.getPosY()).set(a.getPosX(), null);
                data.get(b.getPosY()).set(b.getPosX(), null);
                this.totalSize -= 2;
                return true;
            }
            else if (a.getPosY() == 0 && b.getPosY() == 0) {
                data.get(a.getPosY()).set(a.getPosX(), null);
                data.get(b.getPosY()).set(b.getPosX(), null);
                this.totalSize -= 2;
                return true;
            }
            else if (a.getPosY() == Model.data.get(0).size() && b.getPosY() == Model.data.get(
                    0).size()) {
                data.get(a.getPosY()).set(a.getPosX(), null);
                data.get(b.getPosY()).set(b.getPosX(), null);
                this.totalSize -= 2;
                return true;
            }

            else if (checkPath(a.getPosX(), a.getPosY(), b.getPosX(),
                               b.getPosY(),
                               numTurn)) {
                data.get(a.getPosY()).set(a.getPosX(), null);
                data.get(b.getPosY()).set(b.getPosX(), null);
                this.totalSize -= 2;
                return true;
            }
            return false;
        }
    }

    /**
     * Check path for the inner tiles to see if they can be connected with in
     * the number of turns. Return true if that's possible.
     *
     * @param ax - posX for a
     * @param ay - posY for a
     * @param bx - posX for b
     * @param by - posY for b
     * @param numTurn - number of turns that has been turned already
     * @return boolean
     */
    public boolean checkPath(int ax, int ay, int bx, int by, int numTurn) {
//        System.out.println(ax + " " + ay + " " + bx + " " + by);
        if ((ax == bx) && (ay == by)) {
            // If two tiles are at the same position, return false
            return false;
        }
        else if (numTurn > this.turn) {
//            System.out.println("Exceeds max turn");
            // If the number of turns reach this.turn, return false
            return false;
        }
        else {
            // each InLine checks one direction
            InLine up = new InLine(ax, ay);
            InLine down = new InLine(ax, ay);
            InLine left = new InLine(ax, ay);
            InLine right = new InLine(ax, ay);
            if (up.checkUp(ax, ay, bx, by) || down.checkDown(ax, ay, bx, by) || left.checkLeft(
                    ax, ay, bx, by) || right.checkRight(ax, ay, bx, by)) {
                return true;
            }
            // call checkPath with updated position
            return checkPath(up.getX(), up.getY(), bx, by, numTurn + 1) || checkPath(
                    down.getX(), down.getY(), bx, by, numTurn + 1) || checkPath(
                    left.getX(), left.getY(), bx, by, numTurn + 1) || checkPath(
                    right.getX(), right.getY(), bx, by, numTurn + 1);
        }
    }

    /**
     * Shuffle the remaining tiles with canceled stay at the same position,
     * return true if able to shuffle
     *
     * @return boolean
     */
    public boolean shuffle() {
        if (this.shuffleChance <= 0) {
            return false;
        }
        else {
            ArrayList<Tile> temp = new ArrayList();
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(0).size(); j++) {
                    if (data.get(i).get(j) != null) {
                        temp.add(data.get(i).get(j));
                    }
                }
            }
            Collections.shuffle(temp);
            int index = 0;
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(0).size(); j++) {
                    if (data.get(i).get(j) != null) {
                        Tile current = temp.get(index);
                        current.setX(j);
                        current.setY(i);
                        data.get(i).set(j, current);
                        index += 1;
                    }
                }
            }
            this.shuffleChance -= 1;
            return true;
        }
    }
}
