package Backend.OpenEducation.Services;

import java.util.List;

import Backend.OpenEducation.Model.Material;

public interface MaterialService {

    List<Material> findByCourseId(Long courseId);

}
