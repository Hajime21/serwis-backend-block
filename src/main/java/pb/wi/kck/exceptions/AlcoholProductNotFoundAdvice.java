package pb.wi.kck.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AlcoholProductNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(pb.wi.kck.exceptions.AlcoholProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundHandler(AlcoholProductNotFoundException ex) {
        return ex.getMessage();
    }
}

