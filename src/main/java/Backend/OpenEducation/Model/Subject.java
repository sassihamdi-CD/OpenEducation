package Backend.OpenEducation.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.SpringVersion;

import java.util.List;

/**
 * Represents a subject or topic within the educational platform.
 * Each subject can be associated with multiple courses.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subjectId")
    private long subjectId;

    /**
     * Name of the subject (e.g., "Java Programming," "Data Science").
     */
    @Getter
    @NotNull
    @Column(name = "subjectName", nullable = false)
    private String subjectName;


    /**
     * Description of the subject.
     */
    @Getter
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;


    /**
     * List of associated courses related to this subject.
     */
    @OneToMany(mappedBy = "Subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> associatedCourses;

    /**
     * Constructor for creating a subject.
     *
     * @param subjectName        Name of the subject
     * @param description        Description of the subject
     * @param associatedCourses  List of associated courses
     */
    public Subject(String subjectName, String description, List<Course> associatedCourses) {
        this.subjectName = subjectName;
        this.description = description;
        this.associatedCourses = associatedCourses;
    }
}

