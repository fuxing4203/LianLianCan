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
    private boolean inLine = false;

    public boolean isInLine() {
        return inLine;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    InLine(int ax, int ay) {
        this.x = ax;
        this.y = ay;
    }

    public boolean checkRow(int ax, int ay, int bx, int by) {
        if (ay == by) {
            if (ax < bx) {
                for (int i = ax + 1; i < bx; i++) {
//                    System.out.println("For loop" + i);
//                    System.out.println(ax + " " + bx);
//                    System.out.println(Model.data.get(ay).get(i) == null);
                    if (Model.data.get(ay).get(i) != null) {
                        this.x = i;
                        this.y = ay;
                        System.out.println("Out of the loop");
                        return false;
                    }
                }
                return true;
            }
            else {
                for (int i = ax + 1; i < bx; i--) {
                    if (Model.data.get(ay).get(i) != null) {
                        this.x = i;
                        this.y = ay;
                        return false;
                    }
                }
                return true;
            }
        }
        else if (ax < bx) {
            System.out.println("ax < bx");
            if (Model.data.get(ay).get(ax + 1) == null) {
                System.out.println("ax < bx: " + ax);
                ax += 1;
                while ((Model.data.get(ay).get(ax) == null) && (ax < Model.data.get(
                                                                0).size())) {
                    ax += 1;
                }
            }
            this.x = ax;
            return false;
        }
        /*
        else if (ax == bx) {
            return checkCol(ax, ay, bx, by);
        }*/
        else {
            if (Model.data.get(ay).get(ax - 1) == null) {
                ax -= 1;
                while ((Model.data.get(ay).get(ax) == null) && (ax < Model.data.get(
                                                                0).size())) {
                    ax -= 1;
                }
            }
            this.x = ax;
            return false;
        }
    }

    public boolean checkCol(int ax, int ay, int bx, int by) {
        if (ax == bx) {
            if (ay < by) {
                for (int i = ay + 1; i < by; i++) {
                    if (Model.data.get(i).get(ax) != null) {
                        this.x = ax;
                        this.y = i;
                        return false;
                    }
                }
                return true;
            }
            else {
                for (int i = ay + 1; i < by; i--) {
                    if (Model.data.get(i).get(ax) != null) {
                        this.x = ax;
                        this.y = i;
                        return false;
                    }
                }
                return true;
            }
        }

        else if (ay > by) {
            if (Model.data.get(ay - 1).get(ax) == null) {
                ay -= 1;
                while ((Model.data.get(ay).get(ax) == null) && (ay < Model.data.size())) {
                    ay -= 1;
                }
            }
            this.y = ay;
            return false;
        }
        else {
            if (Model.data.get(ay + 1).get(ax) == null) {
                ay += 1;
                while ((Model.data.get(ay).get(ax) == null) && (ay < Model.data.size())) {
                    ay += 1;
                }
            }
            this.y = ay;
            return false;
        }

        //return false;
    }

}
