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
* File: ModelTest
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jj030
 */
public class ModelTest {

    public ModelTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of cancelTile method, of class Model.
     */
    @Test
    public void testCancelTile() {
        System.out.println("cancelTile");
        Tile a = null;
        Tile b = null;
        Model instance = null;
        boolean expResult = false;
        boolean result = instance.cancelTile(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPath method, of class Model.
     */
    @Test
    public void testCheckPath() {
        System.out.println("checkPath");
        int ax = 0;
        int ay = 0;
        int bx = 0;
        int by = 0;
        int numTurn = 0;
        Model instance = null;
        boolean expResult = false;
        boolean result = instance.checkPath(ax, ay, bx, by, numTurn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shuffle method, of class Model.
     */
    @Test
    public void testShuffle() {
        System.out.println("shuffle");
        Model instance = null;
        boolean expResult = false;
        boolean result = instance.shuffle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
