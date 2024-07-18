import Backend.OpenEducation.Model.Course;
import Backend.OpenEducation.Model.Subject;
import Backend.OpenEducation.Repository.SubjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SubjectRepository.
 */
@DataJpaTest
@Sql("/data-test.sql") // Load test data from a SQL file
public class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    private long validCourseId; // Example course ID

    /**
     * Sets up test data before each test case.
     */
    @BeforeEach
    public void setUp() {
        createTestData();
    }

    /**
     * Cleans up resources after each test case.
     */
    @AfterEach
    public void tearDown() {
        subjectRepository.deleteAll();
    }

    /**
     * Creates test data: a course and associated subjects.
     */
    private void createTestData() {
        validCourseId = 42L; // Example course ID

        // Create a test course
        Course testCourse = new Course();
        testCourse.setCourseId(validCourseId);
        testCourse.setName("Computer Science"); 

        // Create a subject
        Subject testSubject = new Subject();
        testSubject.setName("Mathematics");
        testSubject.setCourse(testCourse); // Associate with a course

        // Persist the test data
        subjectRepository.save(testCourse);
        subjectRepository.save(testSubject);
    }

    /**
     * Tests the custom query method findByCourseId.
     */
    @Test
    public void testFindByCourseId() {
        // Act: Call the custom query method
        List<Subject> subjects = subjectRepository.findByCourseId(validCourseId);

        // Assert: Verify the results
        assertEquals(1, subjects.size()); // Adjust based on your test data

        for (Subject subject : subjects) {
            assertEquals("Mathematics", subject.getName());
        }

        // Test with non-existent course ID
        long emptyCourseId = 99L;
        List<Subject> emptySubjects = subjectRepository.findByCourseId(emptyCourseId);
        assertTrue(emptySubjects.isEmpty(), "No subjects should be found for an empty course.");

        // Test with null course ID
        List<Subject> nullSubjects = subjectRepository.findByCourseId(null);
        assertNull(nullSubjects, "Result should be null for null course ID.");
    }
}
