package Backend.OpenEducation.Controllers;

import Backend.OpenEducation.Services.CourseService;
import Backend.OpenEducation.dto.CourseDTO;
import Backend.OpenEducation.Exception.CourseNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for the CourseController class.
 */
public class CourseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    /**
     * Tests the creation of a course.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testCreateCourse() throws Exception {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Test Course");
        courseDTO.setDescription("Test Description");

        when(courseService.createCourse(any(CourseDTO.class))).thenReturn(courseDTO);

        mockMvc.perform(post("/api/v1/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Course\",\"description\":\"Test Description\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Course"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    /**
     * Tests retrieving a course by its ID.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testGetCourseById() throws Exception {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Test Course");
        courseDTO.setDescription("Test Description");

        when(courseService.getCourseById(anyLong())).thenReturn(courseDTO);

        mockMvc.perform(get("/api/v1/courses/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Course"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    /**
     * Tests the scenario where a course is not found by its ID.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testGetCourseByIdNotFound() throws Exception {
        when(courseService.getCourseById(anyLong())).thenThrow(new CourseNotFoundException("Course not found"));

        mockMvc.perform(get("/api/v1/courses/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Tests retrieving all courses.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testGetAllCourses() throws Exception {
        CourseDTO course1 = new CourseDTO();
        course1.setName("Course 1");
        course1.setDescription("Description 1");

        CourseDTO course2 = new CourseDTO();
        course2.setName("Course 2");
        course2.setDescription("Description 2");

        when(courseService.getAllCourses()).thenReturn(List.of(course1, course2));

        mockMvc.perform(get("/api/v1/courses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Course 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[1].name").value("Course 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"));
    }

    /**
     * Tests updating a course.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testUpdateCourse() throws Exception {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Updated Course");
        courseDTO.setDescription("Updated Description");

        when(courseService.updateCourse(anyLong(), any(CourseDTO.class))).thenReturn(courseDTO);

        mockMvc.perform(put("/api/v1/courses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Course\",\"description\":\"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Course"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    /**
     * Tests deleting a course.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testDeleteCourse() throws Exception {
        mockMvc.perform(delete("/api/v1/courses/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
