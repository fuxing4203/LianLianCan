/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 13, 2017
* Time: 8:01:47 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: LevelTest
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
public class LevelTest {

    public LevelTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of values method, of class Level.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Level[] expResult = null;
        Level[] result = Level.values();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Level.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        Level expResult = null;
        Level result = Level.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class Level.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Level instance = null;
        int expResult = 0;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class Level.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Level instance = null;
        int expResult = 0;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
