package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.Assert.*;

public class inputCheckerTest {
    inputChecker reader;

    @Before
    public final void before(){
        reader = new inputChecker();
    }

    private void setInputStream(String input) throws NoSuchFieldException,IllegalAccessException {
        Field scannerField = reader.getClass().getDeclaredField("scanner");
        scannerField.setAccessible(true);
        Scanner scannerWithMockedStream = new Scanner(new ByteArrayInputStream(input.getBytes()));
        scannerField.set(reader, scannerWithMockedStream);
    }

    /* validate the studentInfo*/

    @Test
    public void should_return_input_when_in_right_format() throws Exception {
        String inputString = "张三, 101, 数学: 75, 语文: 95, 英语: 80, 编程: 80";
        setInputStream(inputString);
        assertEquals(inputString, reader.read(0));
    }

    //no scores return invalid
    @Test
    public void should_return_invalid_when_has_no_scores() throws Exception {
        String inputString = "张三, 101";
        setInputStream(inputString);
        assertEquals("invalid", reader.read(0));
    }

    //wrong separators return invalid
    @Test
    public void should_return_invalid_when_has_wrong_separator() throws Exception {
        String inputString = "张三, 101, 数学,75, 语文, 95, 英语, 80, 编程, 80";
        setInputStream(inputString);
        assertEquals("invalid", reader.read(0));
    }

    //score is not digit return invalid
    @Test
    public void should_return_invalid_when_score_not_digit() throws Exception {
        String inputString = "张三, 101, 数学: ss, 语文: 95, 英语: 80, 编程: 80";
        setInputStream(inputString);
        assertEquals("invalid", reader.read(0));
    }

    //id is not digit return invalid
    @Test
    public void should_return_invalid_when_id_not_digit() throws Exception {
        String inputString = "张三, gg, 数学: 75, 语文: 95, 英语: 80, 编程: 80";
        setInputStream(inputString);
        assertEquals("invalid", reader.read(0));
    }

    /* validate the student id list */
    @Test
    public void should_return_id_list_when_in_right_format() throws Exception {
        //格式： 学号, 学号,
        String inputString = "12, 11";
        setInputStream(inputString);
        assertEquals(inputString, reader.read(1));
    }

    @Test
    public void should_return_invalid_when_id_is_not_digit() throws Exception {
        //格式： 学号, 学号,
        String inputString = "aa, 11";
        setInputStream(inputString);
        assertEquals("invalid", reader.read(1));
    }

    /* validate the input command 1～3 */
    @Test
    public void should_return_the_same_when_input_digit_command() throws Exception {
        String inputString = "1";
        setInputStream(inputString);
        assertEquals("1", reader.read(2));
    }
}