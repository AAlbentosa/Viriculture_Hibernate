package exceptions;

import java.util.Arrays;
import java.util.List;

public class LogicExceptions extends Exception {
    public static final int WRONG_ARGUMENT = 0;
    private int value;
    private List<String> message = Arrays.asList(
            "<< El argumento introducido no es válido >>"
    );
    public LogicExceptions(int value){
        this.value = value;
    }

    @Override
    public String getMessage() {
        return message.get(value);
    }
}