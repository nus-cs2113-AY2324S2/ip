package errorhandle;
import static constant.NormalConstant.STRING_IS_NOT_INTEGER;

public class ExceptionsHandle extends Exception {


    public int checkIfStringIsInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return STRING_IS_NOT_INTEGER;
        }
    }

    public int getStringIsNotInteger() {
        return STRING_IS_NOT_INTEGER;
    }
}
