/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 10, 2017
* Time: 3:05:53 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: Tile
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

import java.io.Serializable;

/**
 *
 * @author Iris
 */
public class Tile implements Serializable {

    private int x; // Relative pos x of the tile
    private int y; // Relative pos y of the tile
    private String imgName; // String representation of the content of the tile

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tile(int x, int y, String imgName) {

        this.x = x;
        this.y = y;
        this.imgName = imgName;
    }

    /**
     * Set the path of image
     *
     * @param imgName
     */
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    /**
     * Compare if two tiles are equal based on imgName
     *
     * @param obj
     * @return boolean
     */
    public boolean isEqualTo(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != Tile.class) {
            return false;
        }
        final Tile other = (Tile) obj;
        if (other.getImgName().equals(this.imgName)) {
            return true;
        }
        return false;
    }

    /**
     * Set the new posX for the tile
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the new posY for the tile
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter for PosX
     *
     * @return x
     */
    public int getPosX() {
        return x;
    }

    /**
     * Getter for posY
     *
     * @return y
     */
    public int getPosY() {
        return y;
    }

    /**
     * Getter for imgName
     *
     * @return imgName - String
     */
    public String getImgName() {
        return imgName;
    }

}
