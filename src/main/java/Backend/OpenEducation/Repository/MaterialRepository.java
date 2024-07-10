package Backend.OpenEducation.Repository;


import Backend.OpenEducation.Model.Course;
import Backend.OpenEducation.Model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    @Query(value = "SELECT m FROM Material WHERE m.course.subject.subjectId = :subjectId")
    List<Material> findBySubjectId(long subjectId);

    List<Material> findByTitle(String title);

}