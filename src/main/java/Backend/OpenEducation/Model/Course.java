package Backend.OpenEducation.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

/**
 * Represents a course in the OpenEducation system.
 * Each course has a unique ID, title, and description.
 * Students can enroll in courses, and associated materials can be provided.
 *
 * @author Sassi Hamdi
 * @version 1.0
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "course")
public class Course {

    /**
     * The unique identifier for the course.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "courseID")
    private long courseID;



    /**
     * The title of the course.
     */
    @Setter
    @Getter
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;


    /**
     * A brief description of the course.
     */
    @Setter
    @Getter
    @NotNull
    @Column(name = "description", length = 1000)
    private String description;



    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;


    /**
     * Constructs a new Course with the specified title, description, and enrolled students.
     *
     * @param title            The title of the course.
     * @param description      A brief description of the course.
     */
    public Course(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
