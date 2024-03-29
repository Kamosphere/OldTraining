package tw.core.generator;

import com.google.inject.Inject;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

/**
 * Created by jxzhong on 2017/5/17.
 */
public class AnswerGenerator {
    private final RandomIntGenerator randomIntGenerator;
    private int numbersOfNeed = 4;
    @Inject
    public AnswerGenerator(RandomIntGenerator randomIntGenerator) {
        this.randomIntGenerator = randomIntGenerator;
    }

    public Answer generate() throws OutOfRangeAnswerException {
        String RandomNumStr = this.randomIntGenerator.generateNums(9, numbersOfNeed);
        Answer answer = Answer.createAnswer(RandomNumStr);

        answer.validate(numbersOfNeed);
        return answer;
    }
}
