package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StudentTest {
    private student stu = new student();
    public static final String TEST_STUDENT_INFO = "张三, 121, 数学: 75, 语文: 95, 英语: 80, 编程: 80";
    public static final String TEST_STUDENT_INFO_2 = "张三, 121, 数学: 75, 语文: 95, 英语: 80,";


    @Test
    public void Should_return_right_scores_when_has_subject() {
        stu.addScore(TEST_STUDENT_INFO);
        assertEquals(new Integer(75), stu.getScore("数学"));

    }

    @Test
    public void Should_return_zero_when_no_subject_scores() {
        stu.addScore(TEST_STUDENT_INFO_2);
        assertEquals(new Integer(0), stu.getScore("编程"));
    }
}