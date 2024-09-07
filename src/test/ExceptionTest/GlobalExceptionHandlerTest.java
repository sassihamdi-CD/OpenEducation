package Backend.OpenEducation.Exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for GlobalExceptionHandler.
 */
public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    /**
     * Test handling of CourseNotFoundException.
     */
    @Test
    public void testHandleCourseNotFoundException() {
        // Given
        CourseNotFoundException exception = new CourseNotFoundException("Course not found");

        // When
        ResponseEntity<String> response = globalExceptionHandler.handleCourseNotFoundException(exception);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Course not found", response.getBody());
    }
}
