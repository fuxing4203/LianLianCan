/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 10, 2017
* Time: 4:42:07 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: InLine
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

/**
 *
 * @author Iris
 */
public class InLine {

    private int x;
    private int y;

    /**
     * Updated position of ax
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Updated position of ay
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    InLine(int ax, int ay) {
        this.x = ax;
        this.y = ay;
    }

    InLine() {

    }

    /**
     * Go as far up as possible, return true if two tiles are in the same column
     * and a is under b
     *
     * @param ax - posX for a
     * @param ay - posY for a
     * @param bx - posX for b
     * @param by - posY for b
     * @return boolean
     */
    public boolean checkUp(int ax, int ay, int bx, int by) {

        if (ax == bx && ay < by) {
            for (int i = ay; i < by - 1; i++) {
                if (Model.data.get(i + 1).get(ax) != null) {
                    this.x = ax;
                    this.y = i;
                    return false;
                }
            }
            return true;
        }
        else if (ay < Model.data.size() - 1) {
            while ((ay < Model.data.size() - 1) && (Model.data.get(ay + 1).get(
                                                    ax) == null)) {
                ay += 1;
            }

            this.y = ay;
            return false;
        }
        return false;
    }

    /**
     * Go as far down as possible, return true if two tiles are in the same
     * column and a is above b
     *
     * @param ax - posX for a
     * @param ay - posY for a
     * @param bx - posX for b
     * @param by - posY for b
     * @return boolean
     */
    public boolean checkDown(int ax, int ay, int bx, int by) {
        if (ax == bx && ay > by) {
            for (int i = ay; i > by + 1; i--) {
                if (Model.data.get(i - 1).get(ax) != null) {
                    this.x = ax;
                    this.y = i;
                    return false;
                }
            }
            return true;
        }
        else if (ay > 0) {
            while ((ay > 0) && (Model.data.get(ay - 1).get(ax) == null)) {
                ay -= 1;
            }
            this.y = ay;
            return false;
        }
        return false;
    }

    /**
     * Go as far right as possible, return true if two tiles are in the same
     * row, a is at the left of b
     *
     * @param ax - posX of a
     * @param ay - posY of a
     * @param bx - posX of b
     * @param by - posY of b
     * @return boolean
     */
    public boolean checkRight(int ax, int ay, int bx, int by) {

        if (ay == by && ax < bx) {
            for (int i = ax; i < bx - 1; i++) {
//                    System.out.println("For loop" + i);
//                    System.out.println(ax + " " + bx);
//                    System.out.println(Model.data.get(ay).get(i) == null);
                if (Model.data.get(ay).get(i + 1) != null) {
                    this.x = i;
                    this.y = ay;
//                        System.out.println("Out of the loop");
                    return false;
                }
            }
            return true;
        }
        else if (ax < Model.data.get(0).size() - 1) {
//                System.out.println("Should go here");
            while ((ax < Model.data.get(0).size() - 1) && (Model.data.get(ay).get(
                                                           ax + 1) == null)) {
                ax += 1;
            }
            this.x = ax;
            return false;
        }
        return false;
    }

    /**
     * Go as far left as possible, return true if two tiles are in the same row
     * and a is to the right of b
     *
     * @param ax - posX of a
     * @param ay - posY of a
     * @param bx - posX of b
     * @param by - posY of b
     * @return boolean
     */
    public boolean checkLeft(int ax, int ay, int bx, int by) {
        if (ay == by && ax > bx) {
            for (int i = ax; i > bx + 1; i--) {
                if (Model.data.get(ay).get(i - 1) != null) {
                    this.x = i;
                    this.y = ay;
                    return false;
                }
            }
            return true;
        }
        else if (ax > 0) {
            while ((ax > 0) && (Model.data.get(ay).get(ax - 1) == null)) {
                ax -= 1;
            }
            this.x = ax;
            return false;
        }
        return false;
    }
}
