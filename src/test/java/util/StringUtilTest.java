package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void repeat_string_once() {
        assertEquals("hola", StringUtil.repeat("hola", 1));
    }

    @Test
    public void repeat_string_multiple_times() {
        assertEquals("holaholahola", StringUtil.repeat("hola", 3));
    }

    @Test
    public void repeat_string_zero_times() {
        assertEquals("", StringUtil.repeat("hola", 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void repeat_string_negative_times() {
        StringUtil.repeat("hola", -1);
    }

    @Test
    public void should_return_true_if_string_is_null() {
        assertTrue(StringUtil.isEmpty(null));
    }

    @Test
    public void should_return_true_if_string_is_empty() {
        assertTrue(StringUtil.isEmpty(""));
    }

    @Test
    public void should_return_true_if_string_is_only_spaces() {
        assertTrue(StringUtil.isEmpty("   "));
    }

    @Test
    public void should_return_false_if_string_is_not_empty() {
        assertFalse(StringUtil.isEmpty("not empty"));
    }
}