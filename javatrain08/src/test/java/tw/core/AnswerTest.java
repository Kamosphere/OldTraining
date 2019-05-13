package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;

import java.util.Arrays;
import static org.junit.Assert.*;
/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
    private Answer answer;
    private Answer rightAnswer = new Answer();
    private int numbersOfNeed=4;


    @Before
    public final void before() {
        answer = new Answer();
        rightAnswer.setNumList(Arrays.asList("1","2","3","4"));
    }

    @Test
    public void should_createAnswer_return_right_answer_when_input_string() {
        Answer result = new Answer();
        result.setNumList(Arrays.asList("1","2","3","4"));
        assertEquals(result.toString(), answer.createAnswer("1 2 3 4").toString());
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_validate_throw_OutOfRangeAnswerException_when_answer_contains_same_numbers() throws OutOfRangeAnswerException {
        answer.setNumList(Arrays.asList("1", "2", "3","1"));
        answer.validate(numbersOfNeed);
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_validate_throw_OutOfRangeAnswerException_when_answer_contains_number_bigger_than_9() throws OutOfRangeAnswerException {
        answer.setNumList(Arrays.asList("1", "2", "3","100"));
        answer.validate(numbersOfNeed);
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_validate_throw_OutOfRangeAnswerException_when_answer_contains_less_than_numbersOfNeed() throws OutOfRangeAnswerException {
        answer.setNumList(Arrays.asList("1", "2", "3"));
        answer.validate(numbersOfNeed);
    }

    @Test
    public void should_check_return_record_in_0_0_when_inputAnswer_all_wrong() {
        int[] result = new int[]{0,0};
        answer.setNumList(Arrays.asList("5","6","7","8"));
        assertArrayEquals(result, rightAnswer.check(answer).getValue());
    }

    @Test
    public void should_check_return_record_in_4_0_when_inputAnswer_all_right() {
        int[] result = new int[]{4,0};
        answer.setNumList(Arrays.asList("1","2","3","4"));
        assertArrayEquals(result, rightAnswer.check(answer).getValue());
    }

    @Test
    public void should_check_return_record_in_1_1_when_inputAnswer_with_2_right_numbers_but_1_in_wrong_positions() {
        int[] result = new int[]{1,1};
        answer.setNumList(Arrays.asList("5","3","6","4"));
        assertArrayEquals(result, rightAnswer.check(answer).getValue());
    }

    @Test
    public void should_check_return_record_in_0_4_when_inputAnswer_with_4_right_numbers_but_4_in_wrong_positions() {
        int[] result = new int[]{0,4};
        answer.setNumList(Arrays.asList("4","3","2","1"));
        assertArrayEquals(result, rightAnswer.check(answer).getValue());
    }
}