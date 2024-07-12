package Backend.OpenEducation.Repository;

import Backend.OpenEducation.Model.Course;
import Backend.OpenEducation.Model.Material;
import Backend.OpenEducation.Model.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    // Custom query to find materials by subject ID
    @Query("SELECT m FROM Material m WHERE m.course.subject.subjectId = :subjectId")
    List<Material> findBySubjectId(@Param("subjectId") long subjectId);

    // Derived query methods
    List<Material> findByTitle(String title);
    List<Material> findByCourse(Course course);
    List<Material> findByType(MaterialType type);
}
