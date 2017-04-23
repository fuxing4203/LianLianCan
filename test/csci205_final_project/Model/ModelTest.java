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
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
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
        Model md = new Model(EASY, "pokeman");
        ArrayList<ArrayList<Tile>> data = md.getData();
        System.out.println("cancelTileDiffName");
        data.get(2).get(5).setImgName("a");
        data.get(3).get(5).setImgName("b");
        md.setData(data);
        boolean result = md.isTileCancelable(data.get(2).get(5),
                                             data.get(3).get(5));
        assertFalse(result);

        System.out.println("cancelTileNextToEachOther");
        data.get(3).get(5).setImgName("a");
        md.setData(data);
        result = md.isTileCancelable(data.get(2).get(5),
                                     data.get(3).get(5));
        assertTrue(result);

        System.out.println("cancelTileOneApart");
        data.get(2).set(5, new Tile(5, 2, "a"));
        data.get(4).get(5).setImgName("a");
        data.get(3).set(5, new Tile(5, 3, "b"));
        data.get(2).get(5).setImgName("b");
        data.get(3).get(5).setImgName("b");
        data.get(4).get(5).setImgName("b");
        md.setData(data);
        result = md.isTileCancelable(data.get(2).get(5),
                                     data.get(4).get(5));
        assertFalse(result);
        data.get(3).set(5, null);
        md.setData(data);
        result = md.isTileCancelable(data.get(2).get(5),
                                     data.get(4).get(5));
        assertTrue(result);

        System.out.println("cancelTileTwoTurns");
        data.get(3).set(5, new Tile(5, 3, "b"));
        data.get(2).set(5, new Tile(5, 2, "a"));
        data.get(4).set(5, new Tile(5, 4, "a"));
        data.get(2).set(6, null);
        data.get(3).set(6, null);
        data.get(4).set(6, null);
        md.setData(data);
        result = md.isTileCancelable(data.get(2).get(5),
                                     data.get(4).get(5));
        assertTrue(result);

        System.out.println("cancelTileTwoTurnsZ");
        md = new Model(EASY, "pokeman");
        data = md.getData();
        data.get(2).set(5, null);
        data.get(3).set(5, null);
        data.get(4).set(5, null);
        data.get(2).get(4).setImgName("b");
        data.get(4).get(6).setImgName("b");
        md.setData(data);
        result = md.isTileCancelable(data.get(2).get(4),
                                     data.get(4).get(6));
        assertTrue(result);

        System.out.println("cancelTileThreeTurns");
        data.get(1).get(4).setImgName("b");
        data.get(5).get(6).setImgName("b");
        md.setData(data);
        result = md.isTileCancelable(data.get(1).get(4),
                                     data.get(5).get(6));
        assertFalse(result);
    }

    /**
     * Test of isTileCancelable method, of class Model.
     */
    @Test
    public void testIsTileCancelable() {
        System.out.println("isTileCancelable");
        Model md = new Model(EASY, "pokeman");
        ArrayList<ArrayList<Tile>> data = md.getData();
        data.get(1).get(4).setImgName("b");
        data.get(1).get(5).setImgName("a");
        boolean expResult = false;
        boolean result = md.isTileCancelable(data.get(1).get(4),
                                             data.get(1).get(5));
        assertEquals(expResult, result);
        data.get(1).get(5).setImgName("b");
        expResult = true;
        result = md.isTileCancelable(data.get(1).get(4), data.get(1).get(5));
        assertEquals(expResult, result);
    }

    /**
     * Test of removeTile method, of class Model.
     */
    @Test
    public void testRemoveTile() {
        System.out.println("removeTile");
        Model md = new Model(EASY, "pokeman");
        ArrayList<ArrayList<Tile>> data = md.getData();
        data.get(1).get(4).setImgName("a");
        data.get(1).get(5).setImgName("a");
        md.removeTile(data.get(1).get(4), data.get(1).get(5));
        assertEquals(data.get(1).get(4), null);
        assertEquals(data.get(1).get(5), null);
    }

}
