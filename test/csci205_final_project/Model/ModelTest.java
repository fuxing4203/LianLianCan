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

import static csci205_final_project.Model.Level.EASY;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
        Model md = new Model(EASY);
        md.data.get(2).get(5).setImgName("a");
        md.data.get(3).get(5).setImgName("b");
//        md.data.get(2).set(3, null);
//        md.data.get(3).set(3, null);
//        md.data.get(4).set(3, null);
//        md.data.get(4).set(4, null);
//        md.data.get(4).set(5, null);
//        md.data.get(4).set(6, null);
        boolean result = md.cancelTile(md.data.get(2).get(5),
                                       md.data.get(3).get(5));
        assertFalse(result);

        md.data.get(3).get(5).setImgName("a");
        result = md.cancelTile(md.data.get(2).get(5),
                               md.data.get(3).get(5));
        assertTrue(result);

        md.data.get(2).set(5, new Tile(5, 2, "a"));
        md.data.get(4).get(5).setImgName("a");
        md.data.get(3).set(5, new Tile(5, 3, "b"));
        md.data.get(2).get(5).setImgName("b");
        md.data.get(3).get(5).setImgName("b");
        md.data.get(4).get(5).setImgName("b");
        result = md.cancelTile(md.data.get(2).get(5),
                               md.data.get(4).get(5));
        assertFalse(result);

        md.data.get(3).set(5, null);
        result = md.cancelTile(md.data.get(2).get(5),
                               md.data.get(4).get(5));
        assertTrue(result);

        md.data.get(3).set(5, new Tile(5, 3, "b"));
        md.data.get(2).set(5, new Tile(5, 2, "a"));
        md.data.get(4).set(5, new Tile(5, 4, "a"));
        md.data.get(2).set(6, null);
        md.data.get(3).set(6, null);
        md.data.get(4).set(6, null);
        result = md.cancelTile(md.data.get(2).get(5),
                               md.data.get(4).get(5));
        assertTrue(result);

        md.data.get(2).set(5, new Tile(5, 2, "a"));
        md.data.get(4).set(5, new Tile(5, 4, "a"));
        md.data.get(4).get(7).setImgName("a");
        result = md.cancelTile(md.data.get(2).get(5),
                               md.data.get(4).get(7));
        assertTrue(result);

        md.data.get(2).set(5, new Tile(5, 2, "a"));
        md.data.get(4).set(5, new Tile(7, 4, "a"));
        md.data.get(5).get(6).setImgName("a");
        result = md.cancelTile(md.data.get(2).get(5),
                               md.data.get(5).get(6));
        assertTrue(result);
    }

}
