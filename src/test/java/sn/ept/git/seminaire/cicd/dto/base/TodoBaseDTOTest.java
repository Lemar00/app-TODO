package sn.ept.git.seminaire.cicd.dto.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.util.UUID;

public class TodoBaseDTOTest {

    @Test
    void allArgsConstructorShouldCreateInstanceWithAllParameters() {
        String title = "Todo Title";
        String description = "Todo Description";
        boolean completed = false;

        TodoBaseDTO todoBaseDTO = new TodoBaseDTO(title, description, completed);

        assertNotNull(todoBaseDTO);
        assertEquals(title, todoBaseDTO.getTitle());
        assertEquals(description, todoBaseDTO.getDescription());
        assertEquals(completed, todoBaseDTO.isCompleted());
    }

    @Test
    void dataAnnotationShouldGenerateGetterAndSetterMethods() {
        TodoBaseDTO todoBaseDTO = new TodoBaseDTO();

        todoBaseDTO.setTitle("Todo Title");
        todoBaseDTO.setDescription("Todo Description");
        todoBaseDTO.setCompleted(true);

        assertEquals("Todo Title", todoBaseDTO.getTitle());
        assertEquals("Todo Description", todoBaseDTO.getDescription());
        assertTrue(todoBaseDTO.isCompleted());
    }

    @Test
    void testGetterAndSetterForTitle() {
        TodoBaseDTO todoBaseDTO = new TodoBaseDTO();
        todoBaseDTO.setTitle("Test Title");
        assertEquals("Test Title", todoBaseDTO.getTitle());
    }

    @Test
    void testGetterAndSetterForDescription() {
        TodoBaseDTO todoBaseDTO = new TodoBaseDTO();
        todoBaseDTO.setDescription("Test Description");
        assertEquals("Test Description", todoBaseDTO.getDescription());
    }

    @Test
    void testGetterAndSetterForCompleted() {
        TodoBaseDTO todoBaseDTO = new TodoBaseDTO();
        todoBaseDTO.setCompleted(true);
        assertTrue(todoBaseDTO.isCompleted());
    }

    @Test
    void testEqualsAndHashCode() {
        TodoBaseDTO todoBaseDTO1 = new TodoBaseDTO();
        todoBaseDTO1.setTitle("Test Title");
        todoBaseDTO1.setDescription("Test Description");
        todoBaseDTO1.setCompleted(true);

        TodoBaseDTO todoBaseDTO2 = new TodoBaseDTO();
        todoBaseDTO2.setTitle("Test Title");
        todoBaseDTO2.setDescription("Test Description");
        todoBaseDTO2.setCompleted(true);

        assertTrue(todoBaseDTO1.equals(todoBaseDTO2));
        assertEquals(todoBaseDTO1.hashCode(), todoBaseDTO2.hashCode());
    }

    @Test
    void dataAnnotationShouldGenerateGetterSetterEqualsHashCodeToString() {
        TodoBaseDTO todo1 = TodoBaseDTO.builder()
                .id(UUID.randomUUID())
                .title("TestTitle")
                .description("TestDescription")
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .completed(true)
                .build();

        TodoBaseDTO todo2 = TodoBaseDTO.builder()
                .id(UUID.randomUUID())
                .title("TestTitle")
                .description("TestDescription")
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .completed(true)
                .build();

        // Test equals and hashCode
        assertEquals(todo1, todo2);
        assertEquals(todo1.hashCode(), todo2.hashCode());

        // Modify todo2 and re-test
        todo2.setTitle("DifferentTitle");
        assertNotEquals(todo1, todo2);
        assertNotEquals(todo1.hashCode(), todo2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentTitle() {
        TodoBaseDTO todoBaseDTO1 = new TodoBaseDTO();
        todoBaseDTO1.setTitle("Test Title");
        todoBaseDTO1.setDescription("Test Description");
        todoBaseDTO1.setCompleted(true);

        TodoBaseDTO todoBaseDTO2 = new TodoBaseDTO();
        todoBaseDTO2.setTitle("Different Title");
        todoBaseDTO2.setDescription("Test Description");
        todoBaseDTO2.setCompleted(true);

        assertNotEquals(todoBaseDTO1, todoBaseDTO2);
        assertNotEquals(todoBaseDTO1.hashCode(), todoBaseDTO2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentDescription() {
        TodoBaseDTO todoBaseDTO1 = new TodoBaseDTO();
        todoBaseDTO1.setTitle("Test Title");
        todoBaseDTO1.setDescription("Test Description");
        todoBaseDTO1.setCompleted(true);

        TodoBaseDTO todoBaseDTO2 = new TodoBaseDTO();
        todoBaseDTO2.setTitle("Test Title");
        todoBaseDTO2.setDescription("Different Description");
        todoBaseDTO2.setCompleted(true);

        assertNotEquals(todoBaseDTO1, todoBaseDTO2);
        assertNotEquals(todoBaseDTO1.hashCode(), todoBaseDTO2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentCompleted() {
        TodoBaseDTO todoBaseDTO1 = new TodoBaseDTO();
        todoBaseDTO1.setTitle("Test Title");
        todoBaseDTO1.setDescription("Test Description");
        todoBaseDTO1.setCompleted(true);

        TodoBaseDTO todoBaseDTO2 = new TodoBaseDTO();
        todoBaseDTO2.setTitle("Test Title");
        todoBaseDTO2.setDescription("Test Description");
        todoBaseDTO2.setCompleted(false);

        assertNotEquals(todoBaseDTO1, todoBaseDTO2);
        assertNotEquals(todoBaseDTO1.hashCode(), todoBaseDTO2.hashCode());
    }

    @Test
    void testToString() {
        TodoBaseDTO todoBaseDTO = TodoBaseDTO.builder()
                .title("Test Title")
                .description("Test Description")
                .completed(true)
                .build();

        String expectedToString = "TodoBaseDTO(title=Test Title, description=Test Description, completed=true)";
        assertEquals(expectedToString, todoBaseDTO.toString());
    }



}
