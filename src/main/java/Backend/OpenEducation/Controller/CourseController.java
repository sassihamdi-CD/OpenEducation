package Backend.OpenEducation.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import Backend.OpenEducation.Exception.CourseNotFoundException;
import Backend.OpenEducation.Services.CourseService;
import Backend.OpenEducation.dto.CourseDTO;
import jakarta.validation.Valid;

/**
 * REST controller for managing courses.
 * Provides endpoints for creating and retrieving courses.
 */
@RestController
@RequestMapping("/api/v1/courses")
@Validated
public class CourseController {

    // Logger for logging important information and errors
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    // Service layer for handling course-related operations
    private final CourseService courseService;

    /**
     * Constructs a new CourseController with the specified CourseService.
     *
     * @param courseService the course service
     */
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Creates a new course.
     * 
     * @param courseDTO the course data transfer object containing course details
     * @return the created course as a DTO with HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody @Valid CourseDTO courseDTO) {
        logger.info("Creating a new course: {}", courseDTO);
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    /**
     * Retrieves a course by its ID.
     * 
     * @param id the ID of the course to retrieve
     * @return the course as a DTO with HTTP status 200 (OK) if found
     * @throws CourseNotFoundException if the course is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        logger.info("Fetching course with ID: {}", id);
        CourseDTO courseDTO = courseService.getCourseById(id);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }
}