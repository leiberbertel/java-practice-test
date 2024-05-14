package challeges;

import org.junit.Test;

import static org.junit.Assert.*;

public class RomanNumeralsTest {

    @Test
    public void return_I_when_num_is_1() {
        assertEquals("I", RomanNumerals.arabicToRoman(1));
    }

    @Test
    public void return_II_when_num_is_2() {
        assertEquals("II", RomanNumerals.arabicToRoman(2));
    }

    @Test
    public void return_III_when_num_is_3() {
        assertEquals("III", RomanNumerals.arabicToRoman(3));
    }

    @Test
    public void return_V_when_num_is_5() {
        assertEquals("V", RomanNumerals.arabicToRoman(5));
    }

    @Test
    public void return_VI_when_num_is_6() {
        assertEquals("VI", RomanNumerals.arabicToRoman(6));
    }

    @Test
    public void return_VII_when_num_is_7() {
        assertEquals("VII", RomanNumerals.arabicToRoman(7));
    }

    @Test
    public void return_X_when_num_is_10() {
        assertEquals("X", RomanNumerals.arabicToRoman(10));
    }

    @Test
    public void return_VI_when_num_is_11() {
        assertEquals("XI", RomanNumerals.arabicToRoman(11));
    }

    @Test
    public void return_XV_when_num_is_15() {
        assertEquals("XV", RomanNumerals.arabicToRoman(15));
    }

    @Test
    public void return_L_when_num_is_50() {
        assertEquals("L", RomanNumerals.arabicToRoman(50));
    }

    @Test
    public void return_LI_when_num_is_51() {
        assertEquals("LI", RomanNumerals.arabicToRoman(51));
    }

    @Test
    public void return_LV_when_num_is_55() {
        assertEquals("LV", RomanNumerals.arabicToRoman(55));
    }

    @Test
    public void return_LVI_when_num_is_56() {
        assertEquals("LVI", RomanNumerals.arabicToRoman(56));
    }

    @Test
    public void return_LX_when_num_is_60() {
        assertEquals("LX", RomanNumerals.arabicToRoman(60));
    }

}