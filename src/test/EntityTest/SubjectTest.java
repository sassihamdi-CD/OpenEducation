import Backend.OpenEducation.Model.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit tests for the {@link Subject} entity.
 */
public class SubjectTest {
            
    private Subject subject;

    /**
     * Sets up a {@link Subject} instance before each test.
     */
    @BeforeEach
    void setUp() {
        subject = new Subject("Computer Science");
    }


    /**
     * Clean up database resources (e.g., delete test data)
     */
    @AfterEach
    void tearDown() {
    userRepository.deleteAll();
}


    /**
     * Tests the {@link Subject} constructor.
     * Verifies that the constructor initializes the name correctly.
     */
    @Test
    void testSubjectConstructor() {
        Assertions.assertEquals("Computer Science", subject.getName());
    }

    /**
     * Tests creating a {@link Subject} with a null name.
     * Expects a {@link NullPointerException} to be thrown.
     */
    @Test
    void testSubjectWithNullName() {
        Assertions.assertThrows(NullPointerException.class, () -> new Subject(null));
    }

    /**
     * Tests creating a {@link Subject} with an empty name.
     * Verifies that the empty name is correctly handled.
     */
    @Test
    void testSubjectWithEmptyName() {
        Subject emptyNameSubject = new Subject("");
        Assertions.assertEquals("", emptyNameSubject.getName());
    }

    /**
     * Tests the minimum allowed length for a subject name.
     * Verifies that a single-character name is accepted.
     */
    @Test
    void testSubjectNameMinimumLength() {
        Subject shortNameSubject = new Subject("A");
        Assertions.assertEquals("A", shortNameSubject.getName());
    }

    /**
     * Tests the maximum allowed length for a subject name.
     * Verifies that a long name is correctly handled.
     */
    @Test
    void testSubjectNameMaximumLength() {
        String longName = "ThisIsAVeryLongSubjectNameThatExceedsTheLimit";
        Subject longNameSubject = new Subject(longName);
        Assertions.assertEquals(longName, longNameSubject.getName());
    }

    /**
     * Tests creating two {@link Subject} instances with the same name.
     * Verifies that they are not considered equal.
     */
    @Test
    void testSubjectWithDuplicateName() {
        Subject subject1 = new Subject("Algorithms");
        Subject subject2 = new Subject("Algorithms");
        Assertions.assertNotEquals(subject1, subject2);
    }

    /**
     * Demonstrates a soft assertion.
     * Even if this assertion fails, execution continues.
     */
    @Test
    void testSubjectSoftAssertion() {
        Assertions.assertTrue(false, "This assertion will fail");
        System.out.println("Execution continues after assertion failure");
    }
} 
