package Backend.OpenEducation.Services;

import Backend.OpenEducation.dto.ModuleDTO;
import Backend.OpenEducation.Model.Module;
import Backend.OpenEducation.Repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing modules.
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    private static final Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);

    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleServiceImpl(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    /**
     * Creates a new module.
     *
     * @param moduleDTO the module data transfer object
     * @return the created module as a DTO
     */
    @Override
    @Transactional
    public ModuleDTO createModule(@Valid ModuleDTO moduleDTO) {
        logger.info("Creating module: {}", moduleDTO.getName());
        Module module = new Module();
        module.setName(moduleDTO.getName());
        module.setDescription(moduleDTO.getDescription());
        Module savedModule = moduleRepository.save(module);
        return convertToDTO(savedModule);
    }

    /**
     * Retrieves a module by its ID.
     *
     * @param id the ID of the module
     * @return the module as a DTO
     * @throws ModuleNotFoundException if the module is not found
     */
    @Override
    public ModuleDTO getModuleById(@NotNull Long id) {
        logger.info("Retrieving module with ID: {}", id);
        Optional<Module> module = moduleRepository.findById(id);
        return module.map(this::convertToDTO)
                     .orElseThrow(() -> new ModuleNotFoundException("Module not found with ID: " + id));
    }

    /**
     * Retrieves all modules.
     *
     * @return a list of all modules as DTOs
     */
    @Override
    public List<ModuleDTO> getAllModules() {
        logger.info("Retrieving all modules");
        return moduleRepository.findAll().stream()
                               .map(this::convertToDTO)
                               .collect(Collectors.toList());
    }

    /**
     * Updates an existing module.
     *
     * @param id the ID of the module to update
     * @param moduleDTO the module data transfer object with updated information
     * @return the updated module as a DTO
     * @throws ModuleNotFoundException if the module is not found
     */
    @Override
    @Transactional
    public ModuleDTO updateModule(@NotNull Long id, @Valid ModuleDTO moduleDTO) {
        logger.info("Updating module with ID: {}", id);
        if (moduleRepository.existsById(id)) {
            Module module = new Module();
            module.setId(id);
            module.setName(moduleDTO.getName());
            module.setDescription(moduleDTO.getDescription());
            Module updatedModule = moduleRepository.save(module);
            return convertToDTO(updatedModule);
        } else {
            throw new ModuleNotFoundException("Module not found with ID: " + id);
        }
    }

    /**
     * Deletes a module by its ID.
     *
     * @param id the ID of the module to delete
     * @throws ModuleNotFoundException if the module is not found
     */
    @Override
    @Transactional
    public void deleteModule(@NotNull Long id) {
        logger.info("Deleting module with ID: {}", id);
        if (moduleRepository.existsById(id)) {
            moduleRepository.deleteById(id);
        } else {
            throw new ModuleNotFoundException("Module not found with ID: " + id);
        }
    }

    /**
     * Converts a Module entity to a ModuleDTO.
     *
     * @param module the module entity
     * @return the module as a DTO
     */
    private ModuleDTO convertToDTO(Module module) {
        ModuleDTO moduleDTO = new ModuleDTO();
        moduleDTO.setId(module.getId());
        moduleDTO.setName(module.getName());
        moduleDTO.setDescription(module.getDescription());
        return moduleDTO;
    }
}
