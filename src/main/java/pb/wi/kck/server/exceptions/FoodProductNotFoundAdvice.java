package pb.wi.kck.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FoodProductNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(FoodProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String foodProductNotFoundHandler(FoodProductNotFoundException ex) {
        return ex.getMessage();
    }
}

