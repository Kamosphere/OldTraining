package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class LibraryTest {
    public static final String TEST_STUDENT_INFO = "张三, 101, 数学: 75, 语文: 95, 英语: 80, 编程: 80";
    public static final String TEST_STUDENT_INFO2 = "李四, 102, 数学: 85, 语文: 80, 英语: 70, 编程: 90";
    inputChecker reader;
    Library library;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        library = new Library();
        reader = new inputChecker();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void should_print_main_message() throws Exception {
        reader = mock(inputChecker.class);
        library = new Library(reader);
        when(reader.read(2)).thenReturn("3");
        library.mainMessage();
        assertThat(systemOut()).isEqualTo("1. 添加学生\n2. 生成成绩单\n3. 退出\n请输入你的选择（1～3）：");
    }

    private String systemOut() { return outContent.toString(); }

    @Test
    public void should_return_false_when_input3() throws Exception {
        reader = mock(inputChecker.class);
        library = mock(Library.class);
        when(reader.read(2)).thenReturn("3");
        assertFalse(library.mainMessage());
    }

    @Test
    public void should_return_false_when_input_out_of3() throws Exception {
        reader = mock(inputChecker.class);
        library = mock(Library.class);
        when(reader.read(2)).thenReturn("55");
        assertFalse(library.mainMessage());
    }

    @Test
    public void should_return_false_when_input_less_than0() throws Exception {
        reader = mock(inputChecker.class);
        library = mock(Library.class);
        when(reader.read(2)).thenReturn("-1");
        assertFalse(library.mainMessage());
    }

    @Test
    public void should_show_msg_when_add_student() throws Exception {
        reader = mock(inputChecker.class);
        library = new Library(reader);
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.mainMessage();
        assertThat(systemOut().contains("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n")).isTrue();
    }

    @Test
    public void should_return_success_when_add_in_right_format() throws Exception {
        reader = mock(inputChecker.class);
        library = new Library(reader);
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.addStudent();
        assertThat(systemOut().endsWith("学生张三的成绩被添加\n")).isTrue();
    }

    @Test
    public void should_generate_empty_report_when_input_no_id() throws Exception {
        reader = mock(inputChecker.class);
        library = new Library(reader);
        when(reader.read(2)).thenReturn("2");
        when(reader.read(1)).thenReturn("101");
        library.mainMessage();
        assertThat(systemOut().endsWith("成绩单\n"
                + "姓名|平均分|总分\n" +
                "========================\n" +
                "========================\n" +
                "全班总分平均数：0.0\n" +
                "全班总分中位数：0.0\n")).isTrue();
    }

    @Test
    public void should_generate_report_when_input_id() throws Exception {
        reader = mock(inputChecker.class);
        library = new Library(reader);
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.mainMessage();
        when(reader.read(2)).thenReturn("2");
        when(reader.read(1)).thenReturn("101");
        library.mainMessage();
        assertThat(systemOut().endsWith("成绩单\n"
                + "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75|95|80|80|82.5|330\n" +
                "========================\n" +
                "全班总分平均数：330.0\n" +
                "全班总分中位数：330.0\n")).isTrue();
    }

    @Test
    public void should_generate_report_when_input_id_not_exist() throws Exception {
        reader = mock(inputChecker.class);
        library = new Library(reader);
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.mainMessage();
        when(reader.read(2)).thenReturn("2");
        when(reader.read(1)).thenReturn("101, 102");
        library.mainMessage();
        assertThat(systemOut().endsWith("成绩单\n"
                + "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75|95|80|80|82.5|330\n" +
                "========================\n" +
                "全班总分平均数：330.0\n" +
                "全班总分中位数：330.0\n")).isTrue();
    }

    @Test
    public void should_generate_right_report_when_input_id_more_than_once() throws Exception {
        reader = mock(inputChecker.class);
        library = new Library(reader);
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.mainMessage();
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO2);
        library.mainMessage();
        when(reader.read(2)).thenReturn("2");
        when(reader.read(1)).thenReturn("101, 102");
        library.mainMessage();
        assertThat(systemOut().endsWith("成绩单\n"
                + "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75|95|80|80|82.5|330\n" +
                "李四|85|80|70|90|81.25|325\n" +
                "========================\n" +
                "全班总分平均数：327.5\n" +
                "全班总分中位数：327.5\n")).isTrue();
    }

    @Test
    public void should_update_report_when_input_same_id_info() throws Exception {
        reader = mock(inputChecker.class);
        library = new Library(reader);
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.mainMessage();
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn("张三, 101, 数学: 75, 语文: 95, 英语: 80, 编程: 70");
        library.mainMessage();
        when(reader.read(2)).thenReturn("2");
        when(reader.read(1)).thenReturn("101");
        library.mainMessage();
        assertThat(systemOut().endsWith("成绩单\n"
                + "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75|95|80|70|80.0|320\n" +
                "========================\n" +
                "全班总分平均数：320.0\n" +
                "全班总分中位数：320.0\n")).isTrue();
    }

}
