package tw.core.generator;

import org.junit.Before;
import org.junit.Test;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    private AnswerGenerator answerGenerator;
    private RandomIntGenerator randomIntGenerator;

    @Before
    public final void before() {
        randomIntGenerator = mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_throw_OutOfRangeAnswerException_when_generateNums_contains_same_numbers() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3 1");
        answerGenerator.generate();
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_throw_OutOfRangeAnswerException_when_generateNums_contains_number_bigger_than_9() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3 100");
        answerGenerator.generate();
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_throw_OutOfRangeAnswerException_when_generateNums_contains_3_number() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3");
        answerGenerator.generate();
    }

    @Test
    public void should_return_answer_the_same_as_generateNums() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3 4");
        Answer answer = new Answer();
        answer.setNumList(Arrays.asList("1","2","3","4"));
        assertEquals(answer.toString(), answerGenerator.generate().toString());
    }
}

