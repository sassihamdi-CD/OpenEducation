package Backend.OpenEducation.Services;

import java.util.List;

import Backend.OpenEducation.Model.Material;
import Backend.OpenEducation.Model.Module;
import Backend.OpenEducation.dto.CourseDTO;

/**
 * Service interface for managing courses.
 */
public interface CourseService {

    /**
     * Creates a new course.
     *
     * @param course the course to create
     * @return the created course
     */
    CourseDTO createCourse(CourseDTO courseDTO);


    /**
     * Retrieves a course by its ID.
     *
     * @param courseId the ID of the course to retrieve
     * @return the retrieved course, or null if not found
     */
    CourseDTO getCourseById(Long id);


    /**
     * Retrieves all courses.
     *
     * @return a list of all courses
     */
    List<CourseDTO> getAllCourses();


    /**
     * Updates an existing course.
     *
     * @param id the ID of the course to update
     * @param course the course with updated information
     * @return the updated course, or null if not found
     */
    CourseDTO updateCourse(Long id, CourseDTO courseDTO);


    /**
     * Deletes a course by its ID.
     *
     * @param courseId the ID of the course to delete
     */
    void deleteCourse(Long courseId);


    /**
     * Retrieves materials associated with a specific course.
     *
     * @param courseId the ID of the course
     * @return a list of materials associated with the course
     */
    List<Material> getMaterialsByCourseId(Long courseId);


    /**
     * Retrieves modules associated with a specific course.
     *
     * @param courseId the ID of the course
     * @return a list of modules associated with the course
     */
    List<Module> getModulesByCourseId(Long courseId);
}
