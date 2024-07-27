package Backend.OpenEducation.Services;

import Backend.OpenEducation.dto.ModuleDTO;
import Backend.OpenEducation.Model.Module;
import Backend.OpenEducation.Repository.ModuleRepository;
import Backend.OpenEducation.Exception.ModuleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ModuleServiceImplTest {

    @Mock
    private ModuleRepository moduleRepository;

    @InjectMocks
    private ModuleServiceImpl moduleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the creation of a module.
     */
    @Test
    public void testCreateModule() {
        Module module = new Module();
        module.setName("Test Module");
        module.setDescription("Test Description");

        when(moduleRepository.save(any(Module.class))).thenReturn(module);

        ModuleDTO moduleDTO = new ModuleDTO();
        moduleDTO.setName("Test Module");
        moduleDTO.setDescription("Test Description");

        ModuleDTO createdModule = moduleService.createModule(moduleDTO);

        assertNotNull(createdModule);
        assertEquals("Test Module", createdModule.getName());
        assertEquals("Test Description", createdModule.getDescription());
    }

    /**
     * Tests retrieving a module by its ID.
     */
    @Test
    public void testGetModuleById() {
        Module module = new Module();
        module.setId(1L);
        module.setName("Test Module");
        module.setDescription("Test Description");

        when(moduleRepository.findById(anyLong())).thenReturn(Optional.of(module));

        ModuleDTO moduleDTO = moduleService.getModuleById(1L);

        assertNotNull(moduleDTO);
        assertEquals("Test Module", moduleDTO.getName());
        assertEquals("Test Description", moduleDTO.getDescription());
    }

    /**
     * Tests the scenario where a module is not found by its ID.
     */
    @Test
    public void testGetModuleByIdNotFound() {
        when(moduleRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ModuleNotFoundException.class, () -> moduleService.getModuleById(1L));
    }

    /**
     * Tests retrieving all modules.
     */
    @Test
    public void testGetAllModules() {
        Module module1 = new Module();
        module1.setName("Module 1");
        module1.setDescription("Description 1");

        Module module2 = new Module();
        module2.setName("Module 2");
        module2.setDescription("Description 2");

        when(moduleRepository.findAll()).thenReturn(List.of(module1, module2));

        List<ModuleDTO> modules = moduleService.getAllModules();

        assertNotNull(modules);
        assertEquals(2, modules.size());
        assertEquals("Module 1", modules.get(0).getName());
        assertEquals("Module 2", modules.get(1).getName());
    }

    /**
     * Tests updating a module.
     */
    @Test
    public void testUpdateModule() {
        Module module = new Module();
        module.setId(1L);
        module.setName("Updated Module");
        module.setDescription("Updated Description");

        when(moduleRepository.existsById(anyLong())).thenReturn(true);
        when(moduleRepository.save(any(Module.class))).thenReturn(module);

        ModuleDTO moduleDTO = new ModuleDTO();
        moduleDTO.setName("Updated Module");
        moduleDTO.setDescription("Updated Description");

        ModuleDTO updatedModule = moduleService.updateModule(1L, moduleDTO);

        assertNotNull(updatedModule);
        assertEquals("Updated Module", updatedModule.getName());
        assertEquals("Updated Description", updatedModule.getDescription());
    }

    /**
     * Tests the scenario where a module to be updated is not found.
     */
    @Test
    public void testUpdateModuleNotFound() {
        when(moduleRepository.existsById(anyLong())).thenReturn(false);

        ModuleDTO moduleDTO = new ModuleDTO();
        moduleDTO.setName("Updated Module");
        moduleDTO.setDescription("Updated Description");

        assertThrows(ModuleNotFoundException.class, () -> moduleService.updateModule(1L, moduleDTO));
    }

    /**
     * Tests deleting a module.
     */
    @Test
    public void testDeleteModule() {
        when(moduleRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(moduleRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> moduleService.deleteModule(1L));
        verify(moduleRepository, times(1)).deleteById(1L);
    }

    /**
     * Tests the scenario where a module to be deleted is not found.
     */
    @Test
    public void testDeleteModuleNotFound() {
        when(moduleRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ModuleNotFoundException.class, () -> moduleService.deleteModule(1L));
    }
}
