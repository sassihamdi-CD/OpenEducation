package Backend.OpenEducation.Services;

import Backend.OpenEducation.Model.Course;
import io.micrometer.core.instrument.config.validate.ValidationException;

public class CourseValidator {

      
    public void validateCourseDescription(Course course) {
        String description = course.getDescription();
        if (description == null || description.trim().isEmpty()) {
            throw new ValidationException("Description cannot be empty.");
        }
    }
}
