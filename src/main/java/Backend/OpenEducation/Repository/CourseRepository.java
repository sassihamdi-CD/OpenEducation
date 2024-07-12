package Backend.OpenEducation.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Backend.OpenEducation.Model.Course;
import Backend.OpenEducation.Model.Subject;

/**
 * Repository interface for managing Course entities.
 * 
 * @author Sassi Hamdi
 * @version v1.0.0
 */

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{


      /**
     * Finds courses by title or description containing specified keywords.
     *
     * @param keyword1 First keyword to search for in title or description.
     * @param keyword2 Second keyword to search for in title or description.
     * @return List of matching courses.
     */
    List<Course> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword1, String keyword2);



     /**
     * Finds all courses sorted by start date in ascending order.
     *
     * @return List of courses sorted by start date.
     */
    List<Course> findAllByOrderByStartDateAsc();


    /**
     * Finds all courses sorted by duration in descending order.
     *
     * @return List of courses sorted by duration.
     */
    List<Course> findAllByOrderByDurationDesc(); 


    
    /**
     * Finds courses by level (e.g., beginner, intermediate, advanced).
     *
     * @param level Course level to search for.
     * @return List of courses matching the specified level.
     */
    List<Course> findByLevel(String level);


    /**
     * Finds courses with start dates between the given range.
     *
     * @param startDate Start date range (inclusive).
     * @param endDate   End date range (inclusive).
     * @return List of courses within the specified date range.
     */
    List<Course> findByStartDateBetween(LocalDate startDate, LocalDate endDate);



      /**
     * Counts the number of courses per subject.
     *
     * @return Map with subjects as keys and course counts as values.
     */
    Map<Subject, Long> countCoursesBySubject();




     /**
     * Finds courses by tags (keywords) containing the specified tag.
     *
     * @param tag Tag to search for in course tags.
     * @return List of courses matching the specified tag.
     */
    List<Course> findByTagsContainingIgnoreCase(String tag);
}
