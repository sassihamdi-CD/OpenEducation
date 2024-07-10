package Backend.OpenEducation.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


/**
 * Represents educational materials associated with a course in the OpenEducation system.
 * Each material has a unique ID, title, description, and URL.
 * Materials can be linked to specific courses.
 *
 * @author Sassi Hamdi
 * @version 1.0
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Table(name = "material")
public class Material {


    /**
     * The unique identifier for the material.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "materialID")
    private long materialId;


    /**
     * The title of the material.
     */
    @NotNull(message = "Title cannot be null")
    @Getter
    @Column(name = "title")
    private final String title;


    /**
     * A brief description of the material.
     */

    @Getter
    @Column(name = "description", length = 1000)
    private String description;


    /**
     * Enum: VIDEO, PDF, QUIZ, etc.
     */
    @Getter
    @Column(name = "type")
    private MaterialType type;


    /**
     * The URL where the material can be accessed.
     */
    @Getter
    @Setter
    @Column(name = "url")
    private String url;


    /**
     * The course to which this material is associated.
     */
    @ManyToOne
    @JoinColumn(name = "courseID")
    private Course course;


    /**
     * Constructs a new Material with the specified title, description, URL, and associated course.
     *
     * @param title       The title of the material.
     * @param description A brief description of the material.
     * @param url         The URL where the material can be accessed.
     * @param course      The course to which this material is associated.
     */
    public Material(String title, String description, String url, Course course, MaterialType type) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.course = course;
        this.type = type ;
    }
}
