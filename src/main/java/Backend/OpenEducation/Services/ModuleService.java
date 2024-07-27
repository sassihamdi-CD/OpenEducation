package Backend.OpenEducation.Services;

import Backend.OpenEducation.dto.ModuleDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ModuleService {

    /**
     * Creates a new module.
     *
     * @param moduleDTO the module data transfer object
     * @return the created module as a DTO
     */
    ModuleDTO createModule(@Valid ModuleDTO moduleDTO);

    /**
     * Retrieves a module by its ID.
     *
     * @param id the ID of the module
     * @return the module as a DTO
     * @throws ModuleNotFoundException if the module is not found
     */
    ModuleDTO getModuleById(@NotNull Long id);

    /**
     * Retrieves all modules.
     *
     * @return a list of all modules as DTOs
     */
    List<ModuleDTO> getAllModules();

    /**
     * Updates an existing module.
     *
     * @param id the ID of the module to update
     * @param moduleDTO the module data transfer object with updated information
     * @return the updated module as a DTO
     * @throws ModuleNotFoundException if the module is not found
     */
    ModuleDTO updateModule(@NotNull Long id, @Valid ModuleDTO moduleDTO);

    /**
     * Deletes a module by its ID.
     *
     * @param id the ID of the module to delete
     * @throws ModuleNotFoundException if the module is not found
     */
    void deleteModule(@NotNull Long id);
}
