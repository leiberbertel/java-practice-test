package challeges;

import org.junit.Test;

import static org.junit.Assert.*;

public class FizzbuzzTest {

    @Test
    public void return_Fizz_when_num_is_divisible_by_3() {
        assertEquals("Fizz", Fizzbuzz.fizzBuzz(3));
        assertEquals("Fizz", Fizzbuzz.fizzBuzz(6));
    }

    @Test
    public void return_Buzz_when_num_is_divisible_by_5() {
        assertEquals("Buzz", Fizzbuzz.fizzBuzz(5));
        assertEquals("Buzz", Fizzbuzz.fizzBuzz(10));
    }

    @Test
    public void return_FizzBuzz_when_num_is_divisible_by_3_and_5() {
        assertEquals("FizzBuzz", Fizzbuzz.fizzBuzz(15));
        assertEquals("FizzBuzz", Fizzbuzz.fizzBuzz(30));
    }

    @Test
    public void return_num_if_not_divisible_by_3_or_5() {
        assertEquals(String.valueOf(2), Fizzbuzz.fizzBuzz(2));
        assertEquals(String.valueOf(16), Fizzbuzz.fizzBuzz(16));
    }
}