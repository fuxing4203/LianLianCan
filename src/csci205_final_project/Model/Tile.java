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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Iris
 */
public class Tile extends Rectangle {

    private Image img;
    private ImagePattern imgPtn;
    private boolean isCancelled = false;

    Tile(Image img) {
        this.img = img;
        this.imgPtn = new ImagePattern(this.img);
    }

    Tile() {
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

    public void cancel() {
        this.isCancelled = true;
        this.setFill(Color.BLACK);
    }

    public boolean isIsCancelled() {
        return isCancelled;
    }

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.img);
        return hash;
    }

}
