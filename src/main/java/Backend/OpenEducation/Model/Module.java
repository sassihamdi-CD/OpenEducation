package Backend.OpenEducation.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Represents a module or lesson within a course.
 * Each module contains content, materials, and an associated course.
 *
 * @author Sassi Hamdi
 * @version 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "moduleId")
    private long moduleId;


    /**
     * Title of the module.
     * The title must not be null
     */
    @Getter
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;


    /**
     * Detailed content of the module.
     */
    @Getter
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;


    /**
     * URL to an associated video (if any).
     */
    @Getter
    @Column(name = "video_url", columnDefinition = "VIDEO")
    private String videoUrl;


    /**
     * Order of the module within the course.
     */
    @Getter
    @Column(name = "module_order")
    private int order;


    /**
     * Course to which this module belongs (many-to-one relationship).
     */
    @ManyToOne
    @JoinColumn(name = "courseID")
    private Course course; // Many modules belong to one course



    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Material> associatedMaterials; // One module/lesson can have multiple materials

    /**
     * Constructor for creating a module.
     *
     * @param title               Title of the module
     * @param content             Detailed content or description
     * @param order               Order of the module within the course
     * @param course              Course to which this module belongs
     * @param associatedMaterials List of materials associated with this module
     * @param videoUrl            URL to an associated video (if any)
     */
    public Module(String title, String content, int order, Course course, List<Material> associatedMaterials, String videoUrl) {
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
        this.order = order;
        this.course = course;
        this.associatedMaterials = associatedMaterials;
    }
}
