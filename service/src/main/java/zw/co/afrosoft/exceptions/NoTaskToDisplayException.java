package zw.co.afrosoft.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class NoTaskToDisplayException extends RuntimeException {
    public NoTaskToDisplayException(String message) {
        super(message);
    }
}