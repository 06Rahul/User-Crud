package com.example.UserManagement.Excepction;

    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;
    import org.springframework.http.ResponseEntity;
    import org.springframework.http.HttpStatus;

    @RestControllerAdvice
    public class GlobalExcepctionHandler {

        @ExceptionHandler(EmailAlreadyExistsExcepction.class)
        public ResponseEntity<String> handleEmailAlreadyExistsExcepction(EmailAlreadyExistsExcepction ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }

        @ExceptionHandler(UserCreationFailExcepction.class)
        public ResponseEntity<String> handleUserCreationFailExcepction(UserCreationFailExcepction ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<?>handelUserNotFoundExcepction(UserNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage() , HttpStatus.NOT_FOUND);
        }
    }