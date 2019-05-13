package tw.controllers;
import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private GameController gameController;
    private AnswerGenerator answerGenerator;
    private InputCommand inputGuess;
    private Answer answer;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public final void before() throws OutOfRangeAnswerException {
        Answer rightAnswer = new Answer();
        rightAnswer.setNumList(Arrays.asList("1","2","3","4"));
        answerGenerator = mock(AnswerGenerator.class);
        inputGuess = mock(InputCommand.class);
        when(answerGenerator.generate()).thenReturn(rightAnswer);
        gameController = new GameController(new Game(answerGenerator),new GameView());
        System.setOut(new PrintStream(outContent));
        answer = new Answer();
    }

    private String systemOut() { return outContent.toString(); }

    @Test
    public void should_print_beginMsg_when_beginGame() throws IOException {
        gameController.beginGame();
        assertThat(systemOut().startsWith("------Guess Number Game, You have 6 chances to guess!  ------")).isTrue();
    }

    @Test
    public void should_print_GuessHistory_and_fail_when_guess_numbers_are_all_wrong() throws IOException {
        answer.setNumList(Arrays.asList("5","6","7","8"));
        when(inputGuess.input()).thenReturn(answer);
        gameController.play(inputGuess);
        assertThat(systemOut().contains(
                "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "Game Status: fail")).isTrue();
        verify(inputGuess, times(6)).input();
    }

    @Test
    public void should_print_GuessHistory_and_fail_when_guess_2_right_numbers_but_1_in_wrong_position() throws IOException {
        answer.setNumList(Arrays.asList("5","3","6","4"));
        when(inputGuess.input()).thenReturn(answer);
        gameController.play(inputGuess);
        assertThat(systemOut().contains(
                "[Guess Numbers: 5 3 6 4, Guess Result: 1A1B]\n" +
                        "[Guess Numbers: 5 3 6 4, Guess Result: 1A1B]\n" +
                        "[Guess Numbers: 5 3 6 4, Guess Result: 1A1B]\n" +
                        "[Guess Numbers: 5 3 6 4, Guess Result: 1A1B]\n" +
                        "[Guess Numbers: 5 3 6 4, Guess Result: 1A1B]\n" +
                        "[Guess Numbers: 5 3 6 4, Guess Result: 1A1B]\n" +
                        "Game Status: fail")).isTrue();
        verify(inputGuess, times(6)).input();
    }

    @Test
    public void should_print_GuessHistory_and_fail_when_guess_4_right_numbers_but_4_in_wrong_position() throws IOException {
        answer.setNumList(Arrays.asList("4","3","2","1"));
        when(inputGuess.input()).thenReturn(answer);
        gameController.play(inputGuess);
        assertThat(systemOut().contains(
                "[Guess Numbers: 4 3 2 1, Guess Result: 0A4B]\n" +
                        "[Guess Numbers: 4 3 2 1, Guess Result: 0A4B]\n" +
                        "[Guess Numbers: 4 3 2 1, Guess Result: 0A4B]\n" +
                        "[Guess Numbers: 4 3 2 1, Guess Result: 0A4B]\n" +
                        "[Guess Numbers: 4 3 2 1, Guess Result: 0A4B]\n" +
                        "[Guess Numbers: 4 3 2 1, Guess Result: 0A4B]\n" +
                        "Game Status: fail")).isTrue();
        verify(inputGuess, times(6)).input();
    }

    @Test
    public void should_print_GuessHistory_and_success_when_guess_numbers_are_correct() throws IOException {
        answer.setNumList(Arrays.asList("1","2","3","4"));
        when(inputGuess.input()).thenReturn(answer);
        gameController.play(inputGuess);
        assertThat(systemOut().contains(
                "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n" +
                        "Game Status: success")).isTrue();
        verify(inputGuess, times(1)).input();
    }
}