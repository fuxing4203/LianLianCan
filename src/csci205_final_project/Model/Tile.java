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

import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Iris
 */
public class Tile extends Rectangle {

    private Image img;
    private ImagePattern imgPtn;
    private int x;
    private int y;
    private String imgName;

    Tile(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.imgPtn = new ImagePattern(this.img);
    }

    Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Tile(int x, int y, String imgName) {
        this.x = x;
        this.y = y;
        this.imgName = imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public void setImg(Image img) {
        this.img = img;
        updateImg();
    }

    public Image getImg() {
        return img;
    }

    public void updateImg() {
        this.imgPtn = new ImagePattern(this.img);
        this.setFill(this.imgPtn);
    }

    /*
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != Tile.class) {
            return false;
        }
        final Tile other = (Tile) obj;
        if (other.getImg() == this.img) {
            return true;
        }
        return false;
    }
     */
    @Override
    public boolean equals(Object obj) {
        //System.out.println("Equals in Tile");
        if (obj == null) {
            //System.out.println("NULL!");
            return false;
        }
        if (obj.getClass() != Tile.class) {
            //System.out.println("Class Not Equal");
            return false;
        }
        final Tile other = (Tile) obj;
        if (other.getImgName().equals(this.imgName)) {
            return true;
        }
        //System.out.println("Name not equal!");
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.imgName);
        return hash;
    }

    public int getPosX() {
        return x;
    }

    public int getPosY() {
        return y;
    }

    public String getImgName() {
        return imgName;
    }

}
