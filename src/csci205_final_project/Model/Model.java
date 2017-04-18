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
    private final int turn = 2; // maximum number of turns for the inner tiles to be cancelled
    /**
     *
     */
    private ArrayList<ArrayList<Tile>> data; // stores the tiles
    private int totalSize = 0; // records the total number of tiles
    private int shuffleChance = 2; // records the number of chances for shuffle
    private String theme;

    public Model(Level level, String theme) {
        this.level = level;
        this.theme = theme;
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
            System.out.println("Null tile");
            return false;
        }
        else if (!a.isEqualTo(b)) {
            System.out.println("Not Equal!");
            return false;
        }
        else if ((data.get(a.getPosY()).get(a.getPosX()) == null) || (data.get(
                                                                      b.getPosY()).get(
                                                                      b.getPosX()) == null)) {
            System.out.println(
                    "Null Tile" + a.getPosY() + " " + a.getPosX() + " " + b.getPosY() + " " + b.getPosX());
            System.out.println(data);
            System.out.println(this.totalSize);
            int nulPic = 0;
            for (int i = 0; i < this.data.size(); i++) {
                for (int j = 0; j < this.data.get(0).size(); j++) {
                    if (this.data.get(i).get(j) != null) {
                        nulPic += 1;
                    }
                }
            }
            System.out.println(nulPic);
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
                System.out.println(true);
                return true;
            }
            System.out.println(false);
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

    public void removeTile(Tile selectedTile, Tile aTile) {
        data.get(selectedTile.getPosY()).set(selectedTile.getPosX(), null);
        data.get(aTile.getPosY()).set(aTile.getPosX(), null);
    }

    public Level getLevel() {
        return level;
    }

    public ArrayList<String> imgNameProducer(String theme) {
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

    public int getTurn() {
        return turn;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public int getShuffleChance() {
        return shuffleChance;
    }

    public String getTheme() {
        return theme;
    }

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

    @Override
    public String toString() {
        return String.format("-- Theme: %5s -- Level: %s-- ", this.getTheme(),
                             this.level.toString());

    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setData(ArrayList<ArrayList<Tile>> data) {
        this.data = data;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public void setShuffleChance(int shuffleChance) {
        this.shuffleChance = shuffleChance;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
