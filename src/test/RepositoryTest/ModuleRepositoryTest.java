import Backend.OpenEducation.Model.Course;
import Backend.OpenEducation.Model.Module;
import Backend.OpenEducation.Repository.ModuleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ModuleRepository.
 */
@DataJpaTest
@Sql("/data-test.sql") // Load test data from a SQL file
public class ModuleRepositoryTest {

    @Autowired
    private ModuleRepository moduleRepository;

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
        moduleRepository.deleteAll();
    }

    /**
     * Creates test data: a course and associated modules.
     */
    private void createTestData() {
        validCourseId = 42L; // Example course ID

        // Create a test course
        Course testCourse = new Course();
        testCourse.setCourseId(validCourseId);
        testCourse.setName("Computer Science"); // Set other properties as needed

        // Create a module
        Module testModule = new Module();
        testModule.setTitle("Introduction to Programming");
        testModule.setCourse(testCourse); // Associate with a course

        // Persist the test data
        moduleRepository.save(testCourse);
        moduleRepository.save(testModule);
    }

    /**
     * Tests the custom query method findByCourseId.
     */
    @Test
    public void testFindByCourseId() {
        // Act: Call the custom query method
        List<Module> modules = moduleRepository.findByCourseId(validCourseId);

        // Assert: Verify the results
        assertEquals(1, modules.size()); // Adjust based on your test data

        for (Module module : modules) {
            assertEquals("Introduction to Programming", module.getTitle());
            // Add more property assertions as needed
        }

        // Test with non-existent course ID
        long emptyCourseId = 99L;
        List<Module> emptyModules = moduleRepository.findByCourseId(emptyCourseId);
        assertTrue(emptyModules.isEmpty(), "No modules should be found for an empty course.");

        // Test with null course ID
        List<Module> nullModules = moduleRepository.findByCourseId(null);
        assertNull(nullModules, "Result should be null for null course ID.");
    }
}
