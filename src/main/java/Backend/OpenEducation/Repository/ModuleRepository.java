package Backend.OpenEducation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Backend.OpenEducation.Model.Module;

/**
 * Repository interface for managing Module entities.
 */
@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    /**
     * Finds modules by the associated course ID.
     *
     * @param courseId ID of the course.
     * @return List of modules related to the specified course.
     */
    List<Module> findByCourseId(long courseId);

    /**
     * Finds modules by title or description containing specified keywords.
     *
     * @param keyword1 First keyword to search for in title or description.
     * @param keyword2 Second keyword to search for in title or description.
     * @return List of matching modules.
     */
    List<Module> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword1, String keyword2);

    /**
     * Finds modules by the associated course ID, ordered by sequence (ascending).
     *
     * @param courseId ID of the course.
     * @return List of modules ordered by sequence.
     */
    List<Module> findByCourseIdOrderBySequenceAsc(long courseId);

    /**
     * Finds modules by the associated course ID, ordered by duration (descending).
     *
     * @param courseId ID of the course.
     * @return List of modules ordered by duration.
     */
    List<Module> findByCourseIdOrderByDurationDesc(long courseId);

    /**
     * Finds modules by a custom native SQL query based on title.
     *
     * @param keyword Title keyword to search for.
     * @return List of modules matching the specified title.
     */
    @Query(value = "SELECT * FROM modules WHERE title LIKE %:keyword%", nativeQuery = true)
    List<Module> findModulesByTitleNative(@Param("keyword") String keyword);

    /**
     * Finds modules by the associated course ID, ordered by sequence (ascending).
     *
     * @param courseId ID of the course.
     * @return List of modules ordered by sequence.
     */
    @Query("SELECT m FROM Module m WHERE m.course.id = :courseId ORDER BY m.sequence ASC")
    List<Module> findModulesByCourseIdOrderedBySequence(@Param("courseId") long courseId);
}

