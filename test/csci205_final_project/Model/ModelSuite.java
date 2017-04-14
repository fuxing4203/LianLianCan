/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 13, 2017
* Time: 8:01:52 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: ModelSuite
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author jj030
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({csci205_final_project.Model.LevelTest.class, csci205_final_project.Model.InLineTest.class, csci205_final_project.Model.MainModelTest.class, csci205_final_project.Model.TileTest.class, csci205_final_project.Model.ModelTest.class})
public class ModelSuite {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}
