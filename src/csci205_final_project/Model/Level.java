/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 10, 2017
* Time: 1:44:34 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: Level
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

/**
 *
 * @author Iris
 */
public enum Level {
    EASY(6, 6),
    MEDIUM(8, 8),
    HARD(10, 10);

    Level(int height, int width) {
        this.height = height;
        this.width = width;
    }
    private int height;
    private int width;

    public int getWidth() {
        return this.height;
    }

    public int getHeight() {
        return this.width;
    }
}
