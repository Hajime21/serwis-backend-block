package pb.wi.kck.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReceiptNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ReceiptNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String receiptNotFoundHandler(ReceiptNotFoundException ex) {
        return ex.getMessage();
    }
}

