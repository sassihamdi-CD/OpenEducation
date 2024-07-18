package Backend.OpenEducation.dto;

import java.util.List;

import Backend.OpenEducation.Model.Material;
import Backend.OpenEducation.Model.Module;

/**
 * Data Transfer Object for Course.
 */
public class CourseDTO {

    private Long id;
    private String name;
    private String description;
    private List<Material> materials;
    private List<Module> modules;

    /**
     * Gets the ID of the course.
     *
     * @return the ID of the course
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the course.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the course.
     *
     * @return the name of the course
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the course.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the course.
     *
     * @return the description of the course
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the course.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the materials associated with the course.
     *
     * @return the list of materials
     */
    public List<Material> getMaterials() {
        return materials;
    }

    /**
     * Sets the materials associated with the course.
     *
     * @param list the list of materials to set
     */
    public void setMaterials(List<Material> list) {
        this.materials = list;
    }

    /**
     * Gets the modules associated with the course.
     *
     * @return the list of modules
     */
    public List<Module> getModules() {
        return modules;
    }

    /**
     * Sets the modules associated with the course.
     *
     * @param list the list of modules to set
     */
    public void setModules(List<Module> list) {
        this.modules = list;
    }
}
