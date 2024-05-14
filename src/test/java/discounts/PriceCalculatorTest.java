package discounts;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class PriceCalculatorTest {

    @Mock
    PriceCalculator calculator;

    @Before
    public void setup() {
        calculator = new PriceCalculator();
    }

    @Test
    public void total_zero_when_there_are_prices() {
        // valorEsperado, valorActual, margenDeErrorPermitido
        assertEquals(0.0, calculator.getTotal(), 0);
    }

    @Test
    public void total_is_the_sum_of_prices() {
        calculator.addPrice(10.0);
        calculator.addPrice(15.0);

        assertEquals(25.0, calculator.getTotal(), 0);
    }

    @Test
    public void apply_discount_to_prices() {
        calculator.addPrice(100.0);
        calculator.addPrice(50.0);
        calculator.addPrice(50.0);

        calculator.setDiscount(25);

        assertEquals(150.0, calculator.getTotal(), 0);
    }
}