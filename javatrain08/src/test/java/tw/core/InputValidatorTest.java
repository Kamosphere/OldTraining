package tw.core;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tw.validator.InputValidator;
import static org.junit.Assert.*;
/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    private InputValidator inputValidator;
    private int numbersOfNeed=4;

    @Before
    public final void before() {
        inputValidator = new InputValidator();
    }

    @Test(expected = NumberFormatException.class)
    public void should_return_false_when_input_not_number() {
        assertTrue(inputValidator.validate("sdf1 2 3 4"));
    }

    @Test
    public void should_return_false_when_input_less_than_numbersOfNeed_numbers() {
        assertFalse(inputValidator.validate("1 2 3"));
    }

    @Test
    public void should_return_false_when_inputNumber_bigger_than_9() {
        assertFalse(inputValidator.validate("1 2 3 100"));
    }

    @Test
    public void should_return_false_when_inputNumber_has_same_numbers() {
        assertFalse(inputValidator.validate("1 2 3 1"));
    }

    @Test
    public void should_return_false_when_inputNumber_has_same_numbers_and_bigger_than_9() {
        assertFalse(inputValidator.validate("1 2 30 1"));
    }

    @Test
    public void should_return_false_when_inputNumber_has_more_than_numbersOfNeed() {
        assertFalse(inputValidator.validate("1 2 3 7 1"));
    }
    @Test
    public void should_return_false_when_input_right() {
        assertTrue(inputValidator.validate("1 2 3 4"));
    }
}