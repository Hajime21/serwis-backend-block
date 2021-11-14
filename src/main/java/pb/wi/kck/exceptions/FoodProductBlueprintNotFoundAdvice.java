package pb.wi.kck.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FoodProductBlueprintNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(FoodProductBlueprintNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String foodProductBlueprintNotFoundHandler(FoodProductBlueprintNotFoundException ex) {
        return ex.getMessage();
    }
}

