package zw.co.afrosoft.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DivisionByZero extends Exception{
    public DivisionByZero(String message){
        super(message);
    }
}
