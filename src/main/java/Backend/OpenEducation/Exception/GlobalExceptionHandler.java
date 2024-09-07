package Backend.OpenEducation.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * Handles exceptions thrown by any controller.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles CourseNotFoundException.
     * 
     * @param ex the exception thrown when a course is not found
     * @return a response entity with the error message and HTTP status 404 (Not Found)
     */
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
