package com.thoughtworks;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class InfoCheckerTest {
    InfoChecker checker;

    @Before
    public final void before() {
        checker = new InfoChecker();
    }

    private void setInputStream(String input) throws NoSuchFieldException, IllegalAccessException {
        Field scannerField = checker.getClass().getDeclaredField("scanner");
        scannerField.setAccessible(true);
        Scanner scannerWithMockedStream = new Scanner(new ByteArrayInputStream(input.getBytes()));
        scannerField.set(checker, scannerWithMockedStream);
    }

    @Test
    public void should_return_true_when_check_100() {
        assertEquals(true, checker.checkifNumber("100"));
    }

    @Test
    public void should_return_false_when_check_minusseven() {
        assertEquals(false, checker.checkifNumber("-7"));
    }

    @Test
    public void should_return_false_when_check_gg() {
        assertEquals(false, checker.checkifNumber("gg"));
    }
    @Test
    public void should_read_input() throws Exception {
        setInputStream("100");
        assertEquals("100", checker.read());
    }

    @Test(expected = Exception.class)
    public void should_throw_RuntimeException_when_input_less_than_0() throws Exception {
        setInputStream("-7");
        checker.read();
    }

    @Test(expected = Exception.class)
    public void should_throw_RuntimeException_when_input_non_digit() throws Exception {
        setInputStream("gg");
        checker.read();
    }

}