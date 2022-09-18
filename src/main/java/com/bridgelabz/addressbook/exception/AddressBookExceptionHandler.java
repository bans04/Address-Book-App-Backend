package com.bridgelabz.addressbook.exception;
import com.bridgelabz.addressbook.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AddressBookExceptionHandler {
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ResponseDto> constraintViolationExceptionHandler(ConstraintViolationException ex){
        List errorList = ex.getConstraintViolations().stream().map(error -> error.getConstraintDescriptor()).collect(Collectors.toList());
        ResponseDto responseDto = new ResponseDto("exception", errorList);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
//        List<FieldError> errorList = ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
//                new FieldError((String)(fieldError.getObjectName()), fieldError.getField(), fieldError.getDefaultMessage()))
//                .collect(Collectors.toList());
        List errorList = ex.getBindingResult().getAllErrors().stream().map(objectError ->
                objectError.getDefaultMessage()).collect(Collectors.toList());
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", errorList);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ResponseDto> noSuchElementExceptionHandler(UserNotFoundException ex){
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", ex.getMessage());
        return new ResponseEntity<>(responseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserAllreadyExist.class)
    public ResponseEntity<ResponseDto> userAllreadyExistExceptionHandler(UserAllreadyExist ex){
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.ALREADY_REPORTED);
    }
}
