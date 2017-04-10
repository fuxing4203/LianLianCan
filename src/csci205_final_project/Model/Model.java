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

    private Level level;
    private final int turn = 2;
    protected static ArrayList<Row> data;
    private int totalSize;
    private int shuffleChance = 2;

    Model(Level level) {
        this.level = level;
        this.totalSize = this.level.getHeight() * this.level.getWidth();
        data = new ArrayList();
        for (int i = 0; i < this.level.getHeight(); i++) {
            data.add(new Row(i, this.level.getWidth()));
        }
    }

    public boolean shuffle() {
        if (this.totalSize > 0 && this.shuffleChance > 0) {
            for (int i = 0; i < data.size(); i++) {
                shuffleRow(data.get(i));
            }
            Collections.shuffle(data);
            this.shuffleChance -= 1;
            return true;
        }
        return false;
    }

    public void shuffleRow(Row row) {
        Collections.shuffle(row.getRow());
    }

    public boolean cancelTile(int ax, int ay, int bx, int by) {
        if (data.get(ay).getRow().get(ax) != data.get(by).getRow().get(
                bx)) {
            return false;
        }
        else if (data.get(ay).getRow().get(ax).isIsCancelled() || data.get(
                by).getRow().get(bx).isIsCancelled()) {
            return false;
        }
        else {
            if (checkPath(ax, ay, bx, by)) {
                data.get(ay).getRow().get(ax).cancel();
                data.get(by).getRow().get(bx).cancel();
                this.totalSize -= 2;
                return true;
            }
            return false;
        }
    }

    public boolean checkPath(int ax, int ay, int bx, int by) {
        int numTurn = 0;
        boolean result = false;
        InLine inLine = new InLine(ax, ay, bx, by);
        if ((ax == bx) && (ay == by)) {
            return false;
        }
        else {
            while ((numTurn <= this.turn) && (!result)) {
                inLine.checkLine();
                result = result || inLine.isInLine();
                numTurn += 1;
            }
            return result;
        }
    }

}
