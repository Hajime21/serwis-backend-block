package pb.wi.kck.server;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductBlueprintNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ProductBlueprintNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productBlueprintNotFoundHandler(ProductBlueprintNotFoundException ex) {
        return ex.getMessage();
    }
}

