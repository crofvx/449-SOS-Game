import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class gameSOSTest {

    private gameSOS game;

    @Before
    public void setUp() {
        game = new gameSOS(8);
    }

    @Test
    public void testSOSDetectionHorizontal() {
        game.makeCell(0, 2, 'S');
        game.makeCell(0, 1, 'O');
        game.makeCell(0, 0, 'S');
        assertTrue("SOS should be detected horizontally", game.sosCheck(0, 0));
    }

    @Test
    public void testTurnSwitch() {
        assertEquals("Initial turn should be Blue", 'b', game.getCurrentPlayer());
        game.flipP();
        assertEquals("Turn should switch to Red", 'r', game.getCurrentPlayer());
        game.flipP();
        assertEquals("Turn should switch back to Blue", 'b', game.getCurrentPlayer());
    }
}