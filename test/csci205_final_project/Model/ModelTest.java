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
        Model md = new Model(EASY);

        System.out.println("cancelTileDiffName");
        md.data.get(2).get(5).setImgName("a");
        md.data.get(3).get(5).setImgName("b");
        boolean result = md.cancelTile(md.data.get(2).get(5),
                                       md.data.get(3).get(5));
        assertFalse(result);

        System.out.println("cancelTileNextToEachOther");
        md.data.get(3).get(5).setImgName("a");
        result = md.cancelTile(md.data.get(2).get(5),
                               md.data.get(3).get(5));
        assertTrue(result);

        System.out.println("cancelTileOneApart");
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

        System.out.println("cancelTileTwoTurns");
        md.data.get(3).set(5, new Tile(5, 3, "b"));
        md.data.get(2).set(5, new Tile(5, 2, "a"));
        md.data.get(4).set(5, new Tile(5, 4, "a"));
        md.data.get(2).set(6, null);
        md.data.get(3).set(6, null);
        md.data.get(4).set(6, null);
        result = md.cancelTile(md.data.get(2).get(5),
                               md.data.get(4).get(5));
        assertTrue(result);

        System.out.println("cancelTileTwoTurnsZ");
        md = new Model(EASY);
        md.data.get(2).set(5, null);
        md.data.get(3).set(5, null);
        md.data.get(4).set(5, null);
        md.data.get(2).get(4).setImgName("b");
        md.data.get(4).get(6).setImgName("b");
        result = md.cancelTile(md.data.get(2).get(4),
                               md.data.get(4).get(6));
        assertTrue(result);

        System.out.println("cancelTileThreeTurns");
        md.data.get(1).get(4).setImgName("b");
        md.data.get(5).get(6).setImgName("b");
        result = md.cancelTile(md.data.get(1).get(4),
                               md.data.get(5).get(6));
        assertFalse(result);
    }

}
