import Backend.OpenEducation.Model.Course;
import Backend.OpenEducation.Model.Material;
import Backend.OpenEducation.Model.MaterialType;
import Backend.OpenEducation.Repository.MaterialRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the MaterialRepository.
 */
@DataJpaTest
@Sql("/data-test.sql") // Load test data from a SQL file
public class MaterialRepositoryTest {

    @Autowired
    private MaterialRepository materialRepository;

    private long validSubjectId; // Example subject ID

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
        materialRepository.deleteAll();
    }

    /**
     * Creates test data: a subject and associated materials.
     */
    private void createTestData() {
        validSubjectId = 42L; // Example subject ID

        // Create a test subject
        Subject testSubject = new Subject();
        testSubject.setSubjectId(validSubjectId);
        testSubject.setName("Mathematics"); // Set other properties as needed

        // Create a lecture material
        Material lectureMaterial = new Material();
        lectureMaterial.setTitle("Introduction to Calculus");
        lectureMaterial.setType(MaterialType.LectureNotes);
        lectureMaterial.setCourse(testCourse); // Associate with a course

        // Persist the test data
        materialRepository.save(testSubject);
        materialRepository.save(lectureMaterial);
    }

    /**
     * Tests the custom query method findBySubjectId.
     */
    @Test
    public void testFindBySubjectId() {
        // Act: Call the custom query method
        List<Material> materials = materialRepository.findBySubjectId(validSubjectId);

        // Assert: Verify the results
        assertEquals(2, materials.size()); // Adjust based on your test data

        for (Material material : materials) {
            assertEquals("Introduction to Calculus", material.getTitle());
            assertEquals(MaterialType.LectureNotes, material.getType());
        }

        // Test with non-existent subject ID
        long emptySubjectId = 99L;
        List<Material> emptyMaterials = materialRepository.findBySubjectId(emptySubjectId);
        assertTrue(emptyMaterials.isEmpty(), "No materials should be found for an empty subject.");

        // Test with null subject ID
        List<Material> nullMaterials = materialRepository.findBySubjectId(null);
        assertNull(nullMaterials, "Result should be null for null subject ID.");
    }
}
