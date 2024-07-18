package Backend.OpenEducation.Services;

import Backend.OpenEducation.dto.CourseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import Backend.OpenEducation.Model.Course;
import Backend.OpenEducation.Model.Material;
import Backend.OpenEducation.Model.Module;
import Backend.OpenEducation.Repository.CourseRepository;
import Backend.OpenEducation.Exception.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing courses.
 */
@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ModuleService moduleService;   
    

    /**
     * Creates a new course.
     *
     * @param courseDTO the course data transfer object
     * @return the created course as a DTO
     */
    @Override
    @Transactional
    public CourseDTO createCourse(@Valid CourseDTO courseDTO) {
        logger.info("Creating course: {}", courseDTO.getName());
        Course course = new Course();
        course.setTitle(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param id the ID of the course
     * @return the course as a DTO
     * @throws CourseNotFoundException if the course is not found
     */
    @Override
    @Cacheable(value = "courses", key = "#id")
    public CourseDTO getCourseById(@NotNull Long id) {
        logger.info("Retrieving course with ID: {}", id);
        Optional<Course> course = courseRepository.findById(id);
        return course.map(this::convertToDTO)
                     .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + id));
    }

    /**
     * Retrieves all courses.
     *
     * @return a list of all courses as DTOs
     */
    @Override
    @Cacheable(value = "courses")
    public List<CourseDTO> getAllCourses() {
        logger.info("Retrieving all courses");
        return courseRepository.findAll().stream()
                               .map(this::convertToDTO)
                               .collect(Collectors.toList());
    }

    /**
     * Updates an existing course.
     *
     * @param id the ID of the course to update
     * @param courseDTO the course data transfer object with updated information
     * @return the updated course as a DTO
     * @throws CourseNotFoundException if the course is not found
     */
    @Override
    @Transactional
    public CourseDTO updateCourse(@NotNull Long id, @Valid CourseDTO courseDTO) {
        logger.info("Updating course with ID: {}", id);
        if (courseRepository.existsById(id)) {
            Course course = new Course();
            course.setId(id);
            course.setTitle(courseDTO.getName());
            course.setDescription(courseDTO.getDescription());
            Course updatedCourse = courseRepository.save(course);
            return convertToDTO(updatedCourse);
        } else {
            throw new CourseNotFoundException("Course not found with ID: " + id);
        }
    }

    /**
     * Deletes a course by its ID.
     *
     * @param id the ID of the course to delete
     * @throws CourseNotFoundException if the course is not found
     */
    @Override
    @Transactional
    public void deleteCourse(@NotNull Long id) {
        logger.info("Deleting course with ID: {}", id);
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new CourseNotFoundException("Course not found with ID: " + id);
        }
    }

    /**
     * Retrieves materials associated with a specific course.
     *
     * @param courseId the ID of the course
     * @return a list of materials associated with the course
     */
    @Override
    public List<Material> getMaterialsByCourseId(@NotNull Long courseId) {
        logger.info("Retrieving materials for course ID: {}", courseId);
        return materialService.findByCourseId(courseId);
    }

    /**
     * Retrieves modules associated with a specific course.
     *
     * @param courseId the ID of the course
     * @return a list of modules associated with the course
     */
    @Override
    public List<Module> getModulesByCourseId(@NotNull Long courseId) {
        logger.info("Retrieving modules for course ID: {}", courseId);
        return moduleService.findByCourseId(courseId);
    }

    /**
     * Converts a Course entity to a CourseDTO.
     *
     * @param course the course entity
     * @return the course as a DTO
     */
    private CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getTitle());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setMaterials(materialService.findByCourseId(course.getId()));
        courseDTO.setModules(moduleService.findByCourseId(course.getId()));
        return courseDTO;
    }
}
