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

    private int ax;
    private int ay;
    private int bx;
    private int by;
    private boolean inLine = false;

    InLine(int ax, int ay, int bx, int by) {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
    }

    public boolean isInLine() {
        return inLine;
    }

    public void checkLine() {
        boolean x = ax == bx;
        boolean y = ay == by;
        if (x || y) {

            if (x) {
                if (ay > by) {
                    ay -= 1;
                    while (Model.data.get(ay).getRow().get(ax).isIsCancelled() && (ay >= 0)) {
                        ay -= 1;
                        if (by == ay) {
                            this.inLine = true;
                        }
                    }
                }
                else {
                    by -= 1;
                    while (Model.data.get(by).getRow().get(bx).isIsCancelled() && (by >= 0)) {
                        by -= 1;
                        if (by == ay) {
                            this.inLine = true;
                        }
                    }
                }
            }
            else {
                if (ax > bx) {
                    ax -= 1;
                    while (Model.data.get(ay).getRow().get(ax).isIsCancelled() && (ax >= 0)) {
                        ax -= 1;
                        if (ax == bx) {
                            this.inLine = true;
                        }
                    }
                }
                else {
                    bx -= 1;
                    while (Model.data.get(by).getRow().get(bx).isIsCancelled() && (bx >= 0)) {
                        bx -= 1;
                        if (bx == ax) {
                            this.inLine = true;
                        }
                    }
                }
            }
        }
    }
}
