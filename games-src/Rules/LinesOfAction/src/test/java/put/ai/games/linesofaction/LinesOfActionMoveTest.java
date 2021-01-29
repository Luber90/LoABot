/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package put.ai.games.linesofaction;

import org.junit.Test;
import static org.junit.Assert.*;
import put.ai.games.game.Player;

public class LinesOfActionMoveTest {

    @Test
    public void test1a() {
        LinesOfActionMove m = new LinesOfActionMove(Player.Color.EMPTY, 1, 2, 1, 4);
        assertEquals(2, m.length());
    }


    @Test
    public void test1b() {
        LinesOfActionMove m = new LinesOfActionMove(Player.Color.EMPTY, 1, 4, 1, 2);
        assertEquals(2, m.length());
    }


    @Test
    public void test2a() {
        LinesOfActionMove m = new LinesOfActionMove(Player.Color.EMPTY, 2, 1, 4, 1);
        assertEquals(2, m.length());
    }


    @Test
    public void test2b() {
        LinesOfActionMove m = new LinesOfActionMove(Player.Color.EMPTY, 4, 1, 2, 1);
        assertEquals(2, m.length());
    }


    @Test
    public void test3a() {
        LinesOfActionMove m = new LinesOfActionMove(Player.Color.EMPTY, 2, 1, 4, 3);
        assertEquals(2, m.length());
    }


    @Test
    public void test3b() {
        LinesOfActionMove m = new LinesOfActionMove(Player.Color.EMPTY, 4, 3, 2, 1);
        assertEquals(2, m.length());
    }
}
