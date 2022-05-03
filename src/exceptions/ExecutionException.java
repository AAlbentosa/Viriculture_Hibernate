package exceptions;

import java.util.Arrays;
import java.util.List;

public class ExecutionException extends Exception {
    public static final int ERROR_QUERY = 0;
    public static final int CONNECTION_ERROR = 1;
    private int value;
    private List<String> message = Arrays.asList(
            "<< Error al lanzar la consulta a la base de datos >>",
            "<< Error al realizar la conexión a la base de datos >>"
    );
    public ExecutionException(int value){
        this.value = value;
    }

    @Override
    public String getMessage() {
        return message.get(value);
    }
}