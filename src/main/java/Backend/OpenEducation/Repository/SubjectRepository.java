package Backend.OpenEducation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Backend.OpenEducation.Model.Subject;

/**
 * Repository interface for managing Subject entities.
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    /**
     * Finds subjects by name or description containing specified keywords.
     *
     * @param keyword1 First keyword to search for in name or description.
     * @param keyword2 Second keyword to search for in name or description.
     * @return List of matching subjects.
     */
    List<Subject> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword1, String keyword2);

    /**
     * Finds subjects by category (e.g., science, arts, technology).
     *
     * @param category Category to search for.
     * @return List of subjects matching the specified category.
     */
    List<Subject> findByCategory(String category);

    /**
     * Finds popular subjects based on custom criteria (e.g., enrollment count, ratings).
     *
     * @return List of popular subjects.
     */
    List<Subject> findPopularSubjects();

    /**
     * Finds subjects by tags (keywords) containing the specified tag.
     *
     * @param tag Tag to search for in subject tags.
     * @return List of subjects matching the specified tag.
     */
    List<Subject> findByTagsContainingIgnoreCase(String tag);
}
