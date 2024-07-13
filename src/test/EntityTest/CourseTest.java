import Backend.OpenEducation.Model.Course;
import Backend.OpenEducation.Model.Subject;
import Backend.OpenEducation.Repository.CourseRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.*;

/**
 * Tests for the Course entity.
 * Includes validation tests for course name and description.
 * Also tests subject association.
 */
public class CourseTest {

    private CourseValidator courseValidator; // Initialize this appropriately

    /**
     * Sets up resources before each test.
     * Initializes mock objects, common test data, and configuration.
     */
    @BeforeEach
    void setUp() {
        // Create mock objects 
        // Initialize common test data 
        // Set up configuration or environment variables

         mockCourseRepository = Mockito.mock(CourseRepository.class);
         Course course1 = new Course("Java Basics", "Learn fundamental concepts");
         System.setProperty("my.property", "value");
    }

    /**
     * Cleans up resources after each test.
     * Ensures proper cleanup of any data created during the test.
     */
    @AfterEach
    void tearDown() {
        // Clean up resources after each test
        
        courseRepository.deleteAll();
    }

    /**
     * Tests creating a valid course instance.
     * Verifies that the course is considered equal to itself.
     */
    @Test
    void testValidCourseCreation() {
        Course course = new Course("Introduction to Java", "Spring Boot Basics");
        Assertions.assertEquals(course, course);
    }

    /**
     * Tests course name validation for an empty name.
     * Expects a ValidationException to be thrown.
     */
    @Test
    void testCourseName() {
        Course emptyCourse = new Course("", "Description");
        Assertions.assertThrows(ValidationException.class, () -> {
            courseValidator.validate(emptyCourse);
        });
    }

    /**
     * Tests course name validation with special characters.
     * Expects a ValidationException to be thrown.
     */
    @Test
    void testCourseNameWithSpecialCharacters() {
        Course specialCourse = new Course("Intro@Java", "Description");
        Assertions.assertThrows(ValidationException.class, () -> {
            courseValidator.validate(specialCourse);
        });
    }

    /**
     * Tests course name validation for a long title.
     * Expects a ValidationException to be thrown.
     */
    @Test
    void testLongCourseName() {
        String longTitle = "backend engineer are the best, of all time. i love what im really doing as backend engineer.";
        Course longCourse = new Course(longTitle, "Description");
        Assertions.assertThrows(ValidationException.class, () -> {
            courseValidator.validate(longCourse);
        });
    }

    /**
     * Tests validating a valid course description.
     * Expects no exception to be thrown.
     */
    @Test
    void testValidCourseDescription() {
        Course validCourse = new Course("Java Basics", "Learn fundamental concepts");
        Assertions.assertDoesNotThrow(() -> {
            courseValidator.validateCourseDescription(validCourse);
        });
    }

    /**
     * Tests course description validation for a long description.
     * Expects a ValidationException to be thrown.
     */
    @Test
    void testLongCourseDescription() {
        String longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac libero. ...";
        Course longDesCourse = new Course("Advanced Topics", longDescription);
        Assertions.assertThrows(ValidationException.class, () -> {
            courseValidator.validate(longDesCourse);
        });
    }

    /**
     * Tests associating a subject with a course.
     * Verifies that the subject association is correctly set.
     */
    @Test
    void testCourseSubjectAssociation() {
        Subject mathSubject = new Subject("Mathematics");
        Course mathCourse = new Course("Calculus", "Advanced math concepts");

        mathCourse.setSubject(mathSubject);
        Assertions.assertEquals(mathSubject, mathCourse.getSubject());
    }
}
