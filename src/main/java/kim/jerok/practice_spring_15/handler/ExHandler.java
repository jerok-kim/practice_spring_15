package kim.jerok.practice_spring_15.handler;

import kim.jerok.practice_spring_15.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> dbError(RuntimeException e) {
        ResponseDto<?> dto = new ResponseDto<>(e.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
