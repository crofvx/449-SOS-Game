import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class SOSTest {
    private gameSOS game;


    @BeforeEach
    public void setUp() {
        game = new gameSOS(8);
    }

    @Test
    public void setDetect() {
        game.makeCell(0,0,'S');
        game.makeCell(0,1,'O');
        game.makeCell(1,1,'S');
        assertFalse(game.sosCheck(0,0));
    }

    @Test
    public void testLogicGame () {
        game.setMode (gameSOS.Gamestage.LOGIC);
        game.makeCell(0,0,'S');
        game.makeCell(0,1,'0');
        game.makeCell(0,2,'S');
        game.sosCheck(0,0);
        assertEquals("blue", game.getWinner());
    }

    @Test
    public void testOverallGame(){
        game.setMode(gameSOS.Gamestage.OVERALL);
        game.makeCell(0,0,'S');
        game.makeCell(0,1,'O');
        game.makeCell(0,2,'S');
        game.makeCell(1,0,'S');
        game.makeCell(1,1,'O');
        game.makeCell(1,2,'S');
        game.sosCheck(0,0);
        game.sosCheck(1,0);
        assertEquals("blue", game.getWinner());
    }

    System. out. println("Tests completed");

}
