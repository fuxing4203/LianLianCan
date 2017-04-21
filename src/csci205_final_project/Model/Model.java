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
import java.util.Random;

/**
 *
 * @author Iris
 */
public class Model implements Serializable {

    private Level level; // determines the dimension of the board
    private ArrayList<ArrayList<Tile>> data; // stores the tiles
    private int totalSize = 0; // records the total number of tiles
    private int shuffleChance = 2; // records the number of chances for shuffle
    private String theme; // theme of the images
    private int hintChance = 2;

    /**
     *
     * @param level
     * @param theme
     */
    public Model(Level level, String theme) {
        this.level = level;
        this.theme = theme;
        ArrayList<String> imgSeq = imgNameProducer(theme);
        data = new ArrayList();
        for (int i = 0; i < this.level.getHeight() + 2; i++) {
            ArrayList<Tile> row = new ArrayList();
            for (int j = 0; j < this.level.getWidth() + 2; j++) {
                if (j == 0 || j == this.level.getWidth() + 1 || i == 0 || i == this.level.getHeight() + 1) {
                    row.add(null);
                }
                else {
                    Tile tile = new Tile(j, i, imgSeq.get(this.totalSize));
                    row.add(tile);
                    this.totalSize += 1;
                }
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
    public boolean isTileCancelable(Tile a, Tile b) {
        if (a == null || b == null) {
            return false;
        }
        else if (!a.isEqualTo(b)) {
            return false;
        }
        else if ((data.get(a.getPosY()).get(a.getPosX()) == null) || (data.get(
                                                                      b.getPosY()).get(
                                                                      b.getPosX()) == null)) {
            return false;
        }
        else {
            if (checkPath(a.getPosX(), a.getPosY(), b.getPosX(), b.getPosY())) {
                return true;
            }
            return false;
        }
    }

    /**
     * Remove Tiles
     *
     * @param selectedTile - first tile
     * @param aTile - second tile
     */
    public void removeTile(Tile selectedTile, Tile aTile) {
        data.get(selectedTile.getPosY()).set(selectedTile.getPosX(), null);
        data.get(aTile.getPosY()).set(aTile.getPosX(), null);
        this.totalSize -= 2;
    }

    /**
     * Check path for the inner tiles to see if they can be connected with in
     * the number of turns. Return true if that's possible.
     *
     * @param ax - posX for a
     * @param ay - posY for a
     * @param bx - posX for b
     * @param by - posY for b
     * @return boolean
     */
    public boolean checkPath(int ax, int ay, int bx, int by) {
        if ((ax == bx) && (ay == by)) {
            // If two tiles are at the same position, return false
            return false;
        }
        else {
            return checkHorizontal(ax, ay, bx, by) || checkVertical(ax, ay, bx,
                                                                    by) || checkOneTurn(
                            ax, ay, bx, by) || checkTwoTurn(ax, ay, bx, by);
        }
    }

    /**
     * Check if two tiles are able to connect directly horizontal. Return true
     * if able to, false otherwise.
     *
     * @param ax - posX for a
     * @param ay - posY for a
     * @param bx - posX for b
     * @param by - posY for b
     * @return
     */
    public boolean checkHorizontal(int ax, int ay, int bx, int by) {
        if (ay != by) {
            return false;
        }
        else {
            int x0 = Math.min(ax, bx);
            int x1 = Math.max(ax, bx);
            for (int i = x0; i < x1 - 1; i++) {
                if (this.data.get(ay).get(i + 1) != null) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Check if two tiles are able to connect directly vertical. Return true if
     * able to, false otherwise.
     *
     * @param ax - posX for a
     * @param ay - posY for a
     * @param bx - posX for b
     * @param by - posY for b
     * @return
     */
    public boolean checkVertical(int ax, int ay, int bx, int by) {
        if (ax != bx) {
            return false;
        }
        else {
            int y0 = Math.min(ay, by);
            int y1 = Math.max(ay, by);
            for (int i = y0; i < y1 - 1; i++) {
                if (this.data.get(i + 1).get(ax) != null) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Check if two tiles are able to connect via one turn. Return true if able
     * to, false otherwise.
     *
     * @param ax - posX for a
     * @param ay - posY for a
     * @param bx - posX for b
     * @param by - posY for b
     * @return
     */
    public boolean checkOneTurn(int ax, int ay, int bx, int by) {
        boolean path1 = checkVertical(ax, ay, ax, by) && checkHorizontal(ax, by,
                                                                         bx, by) && data.get(
                        by).get(ax) == null;
        boolean path2 = checkVertical(bx, ay, bx, by) && checkHorizontal(
                ax, ay, bx, ay) && data.get(ay).get(bx) == null;
        return path1 || path2;
    }

    /**
     * Check if two tiles are able to connect via two turns. Return true if able
     * to, false otherwise.
     *
     * @param ax - posX for a
     * @param ay - posY for a
     * @param bx - posX for b
     * @param by - posY for b
     * @return
     */
    public boolean checkTwoTurn(int ax, int ay, int bx, int by) {
        boolean pathH = false;
        boolean pathV = false;
        for (int i = 0; i < data.size(); i++) {
            pathH = pathH || (checkOneTurn(ax, i, bx, by) && checkVertical(ax, i,
                                                                           ax,
                                                                           ay) && data.get(
                              i).get(ax) == null);
            pathV = pathV || (checkOneTurn(i, ay, bx, by) && checkHorizontal(i,
                                                                             ay,
                                                                             ax,
                                                                             ay) && data.get(
                              ay).get(i) == null);
        }
        return pathH || pathV;
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

    public void hint() {

    }

    /**
     * Getter for tiles
     *
     * @return data
     */
    public ArrayList<ArrayList<Tile>> getData() {
        return data;
    }

    /**
     * Setter for data. Only used during testing
     *
     * @param data
     */
    public void setData(ArrayList<ArrayList<Tile>> data) {
        this.data = data;
    }

    /**
     * Getter for Level
     *
     * @return level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Getter for totalSize of the tiles remaining
     *
     * @return totalSize
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * Getter for shuffle chance remaining
     *
     * @return shuffleChance
     */
    public int getShuffleChance() {
        return shuffleChance;
    }

    /**
     * Getter for current theme
     *
     * @return theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Representation of the model
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("-- Theme: %5s -- Level: %s-- ", this.getTheme(),
                             this.level.toString());

    }

    /**
     * Setter for shuffleChance
     *
     * @param shuffleChance
     */
    public void setShuffleChance(int shuffleChance) {
        this.shuffleChance = shuffleChance;
    }

    /**
     * Generates an ArrayList of path of images
     *
     * @param theme - the folder that stores the pictures
     * @return sResult
     */
    private ArrayList<String> imgNameProducer(String theme) {
        ArrayList<String> sResult = new ArrayList<String>();
        ArrayList<Integer> iResult = new ArrayList<Integer>();
        int width = this.level.getWidth();
        int height = this.level.getHeight();
        int numTiles = width * height;
        int numImgs = numTiles / 4;
        Random rnd = new Random();
        int n;
        for (int i = 0; i < numTiles; i++) {
            while (true) {
                n = rnd.nextInt(numImgs);
                if (numContained(iResult, n) < 4) {
                    iResult.add(n);
                    sResult.add(theme + "/" + n + ".jpg");
                    break;
                }
            }
        }
        return sResult;
    }

    /**
     * Helper function for imgNameProducer
     */
    private int numContained(ArrayList<Integer> input, int x) {
        int i;
        int result = 0;
        for (i = 0; i < input.size(); i++) {
            if (input.get(i) == x) {
                result += 1;
            }
        }
        return result;
    }

}
