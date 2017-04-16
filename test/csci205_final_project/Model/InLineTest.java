/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 13, 2017
* Time: 8:01:49 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: InLineTest
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
public class InLineTest {

    public InLineTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getX method, of class InLine.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        InLine instance = null;
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class InLine.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        InLine instance = null;
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUp method, of class InLine.
     */
    @Test
    public void testCheckUp() {
        System.out.println("checkUp");
        int ax = 0;
        int ay = 0;
        int bx = 0;
        int by = 0;
        InLine instance = null;
        boolean expResult = false;
        boolean result = instance.checkUp(ax, ay, bx, by);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkDown method, of class InLine.
     */
    @Test
    public void testCheckDown() {
        System.out.println("checkDown");
        int ax = 0;
        int ay = 0;
        int bx = 0;
        int by = 0;
        InLine instance = null;
        boolean expResult = false;
        boolean result = instance.checkDown(ax, ay, bx, by);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRight method, of class InLine.
     */
    @Test
    public void testCheckRight() {
        System.out.println("checkRight");
        int ax = 0;
        int ay = 0;
        int bx = 0;
        int by = 0;
        InLine instance = null;
        boolean expResult = false;
        boolean result = instance.checkRight(ax, ay, bx, by);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkLeft method, of class InLine.
     */
    @Test
    public void testCheckLeft() {
        System.out.println("checkLeft");
        int ax = 0;
        int ay = 0;
        int bx = 0;
        int by = 0;
        InLine instance = null;
        boolean expResult = false;
        boolean result = instance.checkLeft(ax, ay, bx, by);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
