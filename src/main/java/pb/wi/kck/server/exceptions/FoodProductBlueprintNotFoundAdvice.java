package pb.wi.kck.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pb.wi.kck.server.exceptions.FoodProductBlueprintNotFoundException;

@ControllerAdvice
public class FoodProductBlueprintNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(FoodProductBlueprintNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String foodProductBlueprintNotFoundHandler(FoodProductBlueprintNotFoundException ex) {
        return ex.getMessage();
    }
}

