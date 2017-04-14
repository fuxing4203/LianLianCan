/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 13, 2017
* Time: 8:01:50 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: TileTest
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

import javafx.scene.image.Image;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jj030
 */
public class TileTest {

    public TileTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setImgName method, of class Tile.
     */
    @Test
    public void testSetImgName() {
        System.out.println("setImgName");
        String imgName = "";
        Tile instance = null;
        instance.setImgName(imgName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setImg method, of class Tile.
     */
    @Test
    public void testSetImg() {
        System.out.println("setImg");
        Image img = null;
        Tile instance = null;
        instance.setImg(img);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateImg method, of class Tile.
     */
    @Test
    public void testUpdateImg() {
        System.out.println("updateImg");
        Tile instance = null;
        instance.updateImg();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Tile.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Tile instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setX method, of class Tile.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        int x = 0;
        Tile instance = null;
        instance.setX(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setY method, of class Tile.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        int y = 0;
        Tile instance = null;
        instance.setY(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Tile.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Tile instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosX method, of class Tile.
     */
    @Test
    public void testGetPosX() {
        System.out.println("getPosX");
        Tile instance = null;
        int expResult = 0;
        int result = instance.getPosX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosY method, of class Tile.
     */
    @Test
    public void testGetPosY() {
        System.out.println("getPosY");
        Tile instance = null;
        int expResult = 0;
        int result = instance.getPosY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImgName method, of class Tile.
     */
    @Test
    public void testGetImgName() {
        System.out.println("getImgName");
        Tile instance = null;
        String expResult = "";
        String result = instance.getImgName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
