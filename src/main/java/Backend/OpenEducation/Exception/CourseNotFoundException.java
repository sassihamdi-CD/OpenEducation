package Backend.OpenEducation.Exception;

/**
 * Custom exception thrown when a course is not found.
 */
public class CourseNotFoundException extends RuntimeException {

    /**
     * Constructs a new CourseNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public CourseNotFoundException(String message) {
        super(message);
    }
}
