package player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class) // Inicialización automática de los mocks
public class PlayerTest {

    @Mock
    Dice dice;
    @Mock
    Player player;

    @Before
    public void setup() {
        player = new Player(dice, 3);
    }

    @Test
    public void lose_when_dice_number_is_too_low() {
        Mockito.when(dice.roll()).thenReturn(2);

        assertFalse(player.play());
    }

    @Test
    public void wins_when_dice_number_is_big() {
        Mockito.when(dice.roll()).thenReturn(4);

        assertTrue(player.play());
    }

}