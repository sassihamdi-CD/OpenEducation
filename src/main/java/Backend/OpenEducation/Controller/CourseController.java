package Backend.OpenEducation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend.OpenEducation.Exception.CourseNotFoundException;
import Backend.OpenEducation.Services.CourseService;
import Backend.OpenEducation.dto.CourseDTO;
import jakarta.validation.Valid;

/**
 * REST controller for managing courses.
 */
@RestController
@RequestMapping("/api/v1/courses")
@Validated
public class CourseController {

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
     * @param courseDTO the course data transfer object
     * @return the created course as a DTO
     */
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody @Valid CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param id the ID of the course
     * @return the course as a DTO
     * @throws CourseNotFoundException if the course is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        try {
            CourseDTO courseDTO = courseService.getCourseById(id);
            return new ResponseEntity<>(courseDTO, HttpStatus.OK);
        } catch (CourseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
