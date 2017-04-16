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

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Iris
 */
public class Model implements Serializable {

    private Level level; // determines the dimension of the board
    private final int turn = 2; // maximum number of turns for the inner tiles to be cancelled
    /**
     *
     */
    private ArrayList<ArrayList<Tile>> data; // stores the tiles
    private int totalSize = 0; // records the total number of tiles
    private int shuffleChance = 2; // records the number of chances for shuffle
    private Tile selectedTile;
    private int numOfSelections = 0;
    private TilePane tilePane;

    public Model(Level level, TilePane tp) {
        tilePane = tp;
        this.level = level;
        this.totalSize = this.level.getHeight() * this.level.getWidth();
        data = new ArrayList();
        for (int i = 0; i < this.level.getHeight() + 2; i++) {
            ArrayList<Tile> row = new ArrayList();
            for (int j = 0; j < this.level.getWidth() + 2; j++) {
                if (j == 0 || j == this.level.getWidth() + 1 || i == 0 || i == this.level.getHeight() + 1) {
                    row.add(null);
                }
                else {
                    Tile tile = new Tile(j, i, "a");
                    row.add(tile);
                    this.totalSize += 1;
                }
            }
//            System.out.println(row);
            data.add(row);
        }
//        System.out.println("row: " + data.size());
//        System.out.println("col: " + data.get(0).size());
    }

    /**
     * Check if two tiles can be canceled. If it's possible, cancel tiles and
     * return true.
     *
     * @param a - a tile
     * @param b - another tile
     * @return boolean
     */
    public boolean isTileCancelable(Tile a, Tile b) {
        if (a == null || b == null) {
            return false;
        }
        else if (!a.isEqualTo(b)) {
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
            if (checkPath(a.getPosX(), a.getPosY(), b.getPosX(),
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
            return true;
        }
        else if (numTurn > this.turn) {
//            System.out.println("Exceeds max turn");
            // If the number of turns reach this.turn, return false
            return false;
        }
        else {

            // each InLine checks one direction
            InLine aup = new InLine(ax, ay, data);
            InLine adown = new InLine(ax, ay, data);
            InLine aleft = new InLine(ax, ay, data);
            InLine aright = new InLine(ax, ay, data);
            InLine bup = new InLine(bx, by, data);
            InLine bdown = new InLine(bx, by, data);
            InLine bleft = new InLine(bx, by, data);
            InLine bright = new InLine(bx, by, data);

            int oneright = ax;
            int oneleft = ax;
            int oneup = ay;
            int onedown = ay;

            if (ax < data.get(0).size() - 1) {
                oneright += 1;
            }
            if (ax > 0) {
                oneleft -= 1;
            }
            if (ay < data.size() - 1) {
                onedown += 1;
            }
            if (ay > 0) {
                oneup -= 1;
            }

            InLine one = new InLine();

            // call checkPath with updated position
            if (aup.checkUp(ax, ay, bx, by) || adown.checkDown(ax, ay, bx, by) || aleft.checkLeft(
                    ax, ay, bx, by) || aright.checkRight(ax, ay, bx, by)) {
                return true;
            }
            bup.checkUp(bx, by, ax, ay);
            bdown.checkDown(bx, by, ax, ay);
            bleft.checkLeft(bx, by, ax, ay);
            bright.checkRight(bx, by, ax, ay);
            return checkPath(aup.getX(), aup.getY(), bup.getX(), bup.getY(),
                             numTurn + 2) || checkPath(
                            adown.getX(), adown.getY(), bdown.getX(),
                            bdown.getY(), numTurn + 2) || checkPath(
                    aleft.getX(), aleft.getY(), bleft.getX(), bleft.getY(),
                    numTurn + 2) || checkPath(
                            aright.getX(), aright.getY(), bright.getX(),
                            bright.getY(), numTurn + 2) || checkPath(aup.getX(),
                                                                     aup.getY(),
                                                                     bx, by,
                                                                     numTurn + 1) || checkPath(
                            adown.getX(), adown.getY(), bx, by, numTurn + 1) || checkPath(
                    aleft.getX(), aleft.getY(), bx, by,
                    numTurn + 1) || checkPath(
                            aright.getX(), aright.getY(), bx, by, numTurn + 1) || checkPath(
                    aup.getX(), aup.getY(), bdown.getX(), bdown.getY(),
                    numTurn + 2) || checkPath(
                            adown.getX(), adown.getY(), bup.getX(),
                            bup.getY(), numTurn + 2) || checkPath(
                    aleft.getX(), aleft.getY(), bright.getX(), bright.getY(),
                    numTurn + 2) || checkPath(
                            aright.getX(), aright.getY(), bleft.getX(),
                            bleft.getY(), numTurn + 2);
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

    public ArrayList<ArrayList<Tile>> getData() {
        return data;
    }

    public void generateGameWithMode(TilePane tp, Level level) {

        tp.setPrefColumns(level.getWidth());
        tp.setPrefRows(level.getWidth());
        tp.setPrefWidth(50 * level.getWidth());
        tp.setPrefHeight(50 * level.getHeight());
        tp.setMaxSize(50 * level.getWidth(), 50 * level.getHeight());
        int i;
        int j;
        for (i = 1; i < level.getWidth() + 1; i++) {
            for (j = 1; j < level.getHeight() + 1; j++) {
                Tile aTile = data.get(i).get(j);
                //                aTile.setX(perWidth * j);
                //                aTile.setY(perHeight * i);
                File file = new File("a.jpg");
                Image img = new Image(file.toURI().toString());
                aTile.setFill(new ImagePattern(img));
                aTile.setOnMouseClicked((MouseEvent event) -> {
                    //System.out.println(aTile.getPosX());
                    //System.out.println(aTile.getPosY());
                    if (numOfSelections % 2 == 0) {
                        selectedTile = aTile;
                        numOfSelections++;
                    }
                    else if (numOfSelections % 2 == 1) {
                        boolean isPath = isTileCancelable(selectedTile, aTile);
                        if (isPath) {
                            cancelTile(selectedTile, aTile);
                        }

                    }
                });
                tp.getChildren().add(aTile);
                //System.out.println(aTile.getX());
                //System.out.println(aTile.getY());
            }
        }
    }

    private void cancelTile(Tile selectedTile, Tile aTile) {
        selectedTile.setOpacity(0);
        aTile.setOpacity(0);
        data.get(selectedTile.getPosX()).set(selectedTile.getPosX(), null);
        data.get(aTile.getPosX()).set(aTile.getPosX(), null);
    }

}
