import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Backend.OpenEducation.Repository.CourseRepository;
import jakarta.persistence.EntityManager;


/**
 * Unit tests for the {@link CourseRepository} class.
 * These tests focus on verifying the behavior of custom query methods.
 */
@RunWith(SpringJunit4ClassRunner.class)
@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;
    
    /**
     * Configuration class for setting up mock beans.
     */
    @Configuration
    @EnableJpaRepositories(basePackages = "Backend.OpenEducation.Repository")
    static class InnerCourseRepositoryTest {
    
        @Bean
        public EntityManager entityManager(){
            return Mockito.mock(EntityManager.class);
        }

        @Bean
        public SubjectRepository subjectRepository() {
            return Mockito.mock(SubjectRepository.class);
        }
    }


       /**
     * Tests the custom query method {@link CourseRepository#findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase}.
     * Verifies that courses containing specified keywords in their title or description are correctly retrieved.
     */
    @Test
    public void testFindByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(){
            // Arrange: Create test data
            Course course1 = new Course("Java Basics", "Learn the fundamentals of Java programming");
            Course course2 = new Course("Spring Boot", "Building web applications with Spring Boot");
            Course course3 = new Course("Advanced Algorithms", "Master complex algorithms");
    
            courseRepository.saveAll(List.of(course1, course2, course3));
    
            // Act: Perform the search
            List<Course> foundCourses = courseRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase("Java", "Spring");
    
            // Assert: Verify the results
            assertEquals(2, foundCourses.size());

             // Validate titles and descriptions
            assertTrue(foundCourses.stream().anyMatch(course -> course.getTitle().contains("Java")));
            assertTrue(foundCourses.stream().anyMatch(course -> course.getDescription().contains("Spring")));

            
    }


     /**
     * Tests the custom query method {@link CourseRepository#findByLevel}.
     * Verifies that courses with a specific level are correctly retrieved.
     */
    @Test
    public void testFindByLevel() {
        // Arrange: Create test data with different levels
        Course beginnerCourse = new Course("Beginner Course", "Intro to programming");
        Course advancedCourse = new Course("Advanced Course", "Deep dive into algorithms");

        courseRepository.saveAll(List.of(beginnerCourse, advancedCourse));

        // Act: Search for courses with level "Advanced"
        List<Course> advancedCourses = courseRepository.findByLevel("Advanced");

        // Assert: Verify the results
        assertEquals(1, advancedCourses.size());
        assertTrue(advancedCourses.get(0).getTitle().contains("Advanced"));
    }


    /**
     * Tests the custom query method {@link CourseRepository#findByStartDateBetween}.
     * Verifies that courses within a specified date range are correctly retrieved.
     */
    @Test
    public void testFindByStartDateBetween() {
        // Arrange: Create test data with different start dates
        LocalDate startDate1 = LocalDate.of(2024, 7, 1);
        LocalDate startDate2 = LocalDate.of(2024, 8, 1);

        Course course1 = new Course("Course 1", "Starts in July", startDate1);
        Course course2 = new Course("Course 2", "Starts in August", startDate2);

        courseRepository.saveAll(List.of(course1, course2));

        // Act: Search for courses between July and August
        List<Course> coursesInRange = courseRepository.findByStartDateBetween(startDate1, startDate2);

        // Assert: Verify the results
        assertEquals(2, coursesInRange.size());
    }



      /**
     * Tests the custom query method {@link CourseRepository#findByTagsContainingIgnoreCase}.
     * Verifies that courses containing specific tags are correctly retrieved.
     */
    @Test
    public void testFindByTagsContainingIgnoreCase() {
        // Arrange: Create test data with different tags
        Course courseWithTags = new Course("Tagged Course", "Java programming", List.of("Java", "Spring")); 

        courseRepository.save(courseWithTags);

        // Act: Search for courses with the tag "Spring"
        List<Course> springCourses = courseRepository.findByTagsContainingIgnoreCase("Spring");

        // Assert: Verify the results
        assertEquals(1, springCourses.size());
    }

    

    /**
    * Tests the custom query method {@link CourseRepository#findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase}.
    * Verifies that no courses are found when searching with empty title and non-empty description,
     as well as with non-empty title and empty description.
    */
    @Test
    public void testEmptySearchForTitleOrDescription() {

        // Act: Perform the search with empty title and non-empty description
        List<Course> foundCourses1 = courseRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase("", "Spring");

        // Act: Perform the search with non-empty title and empty description
        List<Course> foundCourses2 = courseRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase("Java", "");

        // Assert: Verify that both searches return no courses
        assertEquals(0, foundCourses1.size());
        assertEquals(0, foundCourses2.size());
    }



     /**
     * Tests the custom query method {@link CourseRepository#findByLevel}.
     * Verifies that courses with a specific level are correctly retrieved.
     */
    @Test
    public void testNonExistentLevel() {
        // Arrange: Create test data with different levels
        Course beginnerCourse = new Course("Beginner Course", "Intro to programming");
        Course advancedCourse = new Course("Advanced Course", "Deep dive into algorithms");

        courseRepository.saveAll(List.of(beginnerCourse, advancedCourse));

        // Act: Search for courses with a non-existent level
        List<Course> nonexistentLevelCourses = courseRepository.findByLevel("Intermediate");

        // Assert: Verify that no courses match the level
        assertEquals(0, nonexistentLevelCourses.size());
    }


    /**
     * Tests the custom query method {@link CourseRepository#findByStartDateBetween}.
     * Verifies that courses within a specified date range are correctly retrieved.
     */
    @Test
    public void testEdgeCaseForStartDateBetween() {
        // Arrange: Create test data with different start dates
        LocalDate startDate1 = LocalDate.of(2024, 7, 1);
        LocalDate startDate2 = LocalDate.of(2024, 8, 1);

        Course course1 = new Course("Course 1", "Starts in July", startDate1);
        Course course2 = new Course("Course 2", "Starts in August", startDate2);
        Course course3 = new Course("Course 3", "Starts just before July", startDate1.minusDays(1));
        Course course4 = new Course("Course 4", "Starts just after August", startDate2.plusDays(1));

        courseRepository.saveAll(List.of(course1, course2, course3, course4));

        // Act: Search for courses between July and August (inclusive)
        List<Course> coursesInRange = courseRepository.findByStartDateBetween(startDate1, startDate2);

        // Assert: Verify that all relevant courses are included
        assertEquals(4, coursesInRange.size());
    }



    /**
     * Tests the custom query method {@link CourseRepository#findByTagsContainingIgnoreCase}.
     * Verifies that courses containing specific tags are correctly retrieved.
     */
    @Test
    public void testMultipleTagsInSearch() {
        // Arrange: Create test data with a course having multiple tags
        Course courseWithTags = new Course("Tagged Course", "Java programming", List.of("Java", "Spring"));

        // Create another course without the "Spring" tag
        Course courseWithoutSpringTag = new Course("No Spring Course", "Other programming topics", List.of("Java"));

        courseRepository.saveAll(List.of(courseWithTags, courseWithoutSpringTag));

        // Act: Search for courses with the tag "Spring"
        List<Course> springCourses = courseRepository.findByTagsContainingIgnoreCase("Spring");

        // Assert: Verify that the course with multiple tags is included
        assertEquals(1, springCourses.size());
        assertEquals("Tagged Course", springCourses.get(0).getTitle());

        // Assert: Verify that the course without the "Spring" tag is not included
        assertFalse(springCourses.contains(courseWithoutSpringTag));
    }
}
