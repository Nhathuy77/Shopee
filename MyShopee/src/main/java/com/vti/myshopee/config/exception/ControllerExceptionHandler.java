package com.vti.myshopee.config.exception;









import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.validation.BindException;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.ResponseStatus;

        import javax.servlet.http.HttpServletRequest;
        import java.time.Instant;

@ControllerAdvice // Đang try lại các hàm ở controller
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomException.class) // Đang catch lại các exception là AppException ở controller
    public ResponseEntity<CustomException> catchExceptionCustom(CustomException exception, HttpServletRequest request) {
        exception.setPath(request.getRequestURI()); // Cú pháp để lấy được đường dẫn api
        return ResponseEntity.status(exception.getStatus())
                .body(exception);
    }

    // Mehtod bắt lỗi validate
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomException> handleBindException(BindException e, HttpServletRequest request) {
        String errorMessage = "";
        if (e.getBindingResult().hasErrors()){
            for(int i=0;i< e.getBindingResult().getAllErrors().size();i++){
                errorMessage += e.getBindingResult().getAllErrors().get(i).getDefaultMessage();
                errorMessage += (i==e.getBindingResult().getAllErrors().size()-1) ? "." : ", ";
            }
        }
        CustomException appException= new CustomException(400, errorMessage);
        appException.setPath(request.getRequestURI());
        return new ResponseEntity<>(appException, HttpStatus.valueOf(appException.getStatus()));
    }

    @ExceptionHandler(Exception.class) // Đang catch lại các exception là Exception là các trường hợp còn lại
    public ResponseEntity<CustomException> catchExceptionGlobal(Exception exception, HttpServletRequest request) {
        CustomException appException = new CustomException();
        appException.setPath(request.getRequestURI());
        appException.setTimestamp(Instant.now());
        appException.setStatus(500);
        appException.setMessage(exception.getMessage());
        return ResponseEntity.status(appException.getStatus())
                .body(appException);
    }
}

