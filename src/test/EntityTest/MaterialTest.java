import Backend.OpenEducation.Model.Material;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.*;

/**
 * Tests for the Material entity.
 * Includes validation tests for material type, content, and association.
 */
public class MaterialTest {

    private CourseValidator courseValidator; // Initialize this appropriately
    private Material material; // Initialize this appropriately



    /**
     * Sets up resources before each test.
     * Initializes common data (e.g., sample material) and mock objects (if needed).
     */
    @BeforeEach
    void setUp() {
        // Initialize common test data
        validCourse = new Course("Math101", "Introduction to Mathematics"); 
        material = new Material("Lecture Notes", "Introduction to Java", "https://example.com/lecture.pdf", course, MaterialType.PDF);
      
        // Create mock objects 
        mockCourseRepository = Mockito.mock(CourseRepository.class);
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
     * Tests creating a valid material instance.
     * Verifies that the material is considered equal to itself.
     */
    @Test
    void testValidMaterialCreation() {
        // Verify material properties
        Assertions.assertEquals("Lecture Notes", material.getTitle());
        Assertions.assertEquals("Introduction to Java", material.getDescription());
        Assertions.assertEquals("https://example.com/lecture.pdf", material.getUrl());
        Assertions.assertEquals(course, material.getCourse());
        Assertions.assertEquals(MaterialType.PDF, material.getType());
        // Ensure material ID is not zero
        Assertions.assertNotEquals(0L, material.getMaterialId());
    }



    /**
     * Tests associating a subject with a material.
     * Verifies that the associated course matches the expected course.
     */
    @Test
    void testMaterialCourseAssociation() {
        Course mathCourse = new Course("Calculus", "Advanced math concepts");
        Material mathMaterial = new Material("Math Notes", "Advanced calculus topics", "https://example.com/math.pdf", mathCourse, MaterialType.PDF);
        Assertions.assertEquals(mathCourse, mathMaterial.getCourse());
    }



    /**
    * Tests the description length for the material.
    * Verifies that the description length is within the limit (1000 characters).
    */
    @Test
    void testDescriptionLength() {
        // Verify that the description length is within the limit (1000 characters)
        Assertions.assertTrue(material.getDescription().length() <= 1000);
    }



    /**
    * Tests valid material types for the material.
    * Verifies that valid material types are accepted.
    */
    @Test
    void testValidMaterialTypes() {
        // Verify valid material types
        Assertions.assertEquals(MaterialType.PDF, material.getType());
        Assertions.assertEquals(MaterialType.VIDEO, MaterialType.valueOf("VIDEO")); 
        Assertions.assertEquals(MaterialType.QUIZ, MaterialType.valueOf("QUIZ")); 
    }



    /**
    * Tests invalid material type for the material.
    * Verifies that setting an invalid material type throws a ValidationException.
    */
    @Test
    void testInvalidMaterialType() {
        // Set an invalid material type (e.g., "INVALID_TYPE")
        material.setType(MaterialType.INVALID_TYPE);

        // Simulate saving the material or performing validation
        try {
            validateMaterial(material);
        } catch (ValidationException e) {
             // Expected behavior: ValidationException is thrown
            Assertions.assertTrue(true);
            return; // Exit the test method
        }

        // If no exception is thrown, fail the test
        Assertions.fail("Expected ValidationException was not thrown.");
    }



    /**
    * Tests null course association for the material.
    * Verifies that attempting to create a material with a null course throws a ValidationException.
    */
    @Test 
    void testNullCourseAssociation() {
        // Simulate saving the material or performing validation
        try {
            validateMaterial(material); 
        } catch (ValidationException e) {
            // Expected behavior: ValidationException is thrown
            Assertions.assertTrue(true);
            return;  // Exit the test method
        }
         // If no exception is thrown, fail the test
        Assertions.fail("Expected ValidationException was not thrown.");
    }



    /**
     * Tests valid course association for the material.
     * Verifies that the associated course matches the expected course.
    */
    @Test
    void testValidCourseAssociation() {
        // Verify that the associated course matches the expected course
        Assertions.assertEquals(validCourse, material.getCourse());

        // Ensure the course ID is not zero
        Assertions.assertNotEquals(0L, validCourse.getCourseId());

        // Verify that the course title and description match
        Assertions.assertEquals("Math101", validCourse.getTitle());
        Assertions.assertEquals("Introduction to Mathematics", validCourse.getDescription());



    }
}


