package tw.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.*;
import static java.lang.Integer.parseInt;

/**
 * Created by jxzhong on 2017/5/18.
 */
public class InputValidator {
    private int numbersOfNeed=4;
    private static int invalidnum=65535;

    public Boolean validate(String numStr) {
        List<String> numList = numStrToList(numStr);
        Boolean isValidate = validateDigitsCount(numList, numbersOfNeed);
        return isValidate && validateSingleDigit(numList, numbersOfNeed);
    }

    private boolean validateSingleDigit(List<String> numList, int numCount) {
        return numList.stream()
                    .map(num -> getNumbers(num))
                    .distinct()
                    .filter(num -> (num < 10)).count() == numCount;
    }

    private int getNumbers (String num) throws NumberFormatException{
        return Integer.parseInt(num);
    }

    private Boolean validateDigitsCount(List<String> numList, int numCount) {
        return numList.size() == numCount;
    }

    private List<String> numStrToList(String numStr) {
        return Arrays.stream(numStr.split(" "))
                .collect(Collectors.toList());
    }
}
