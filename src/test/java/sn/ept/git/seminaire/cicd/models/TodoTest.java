package sn.ept.git.seminaire.cicd.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class TodoTest {


    @Test
    public void testDataAnnotationGeneratesGetterSetterEqualsHashCodeToString() {
        Todo todo1 = Todo.builder()
                .id(UUID.randomUUID())
                .title("Test Title")
                .description("Test Description")
                .completed(true)
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        Todo todo2 = Todo.builder()
                .id(UUID.randomUUID())
                .title("Test Title")
                .description("Test Description")
                .completed(true)
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        // Test equals and hashCode
        assertThat(todo1).isEqualTo(todo2);
        assertThat(todo1.hashCode()).isEqualTo(todo2.hashCode());

        // Test toString
        assertThat(todo1.toString()).isEqualTo(todo2.toString());
    }

    @Test
    public void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String title = "Test Title";
        String description = "Test Description";
        boolean completed = true;
        Instant createdDate = Instant.now();
        Instant lastModifiedDate = Instant.now();
        int version = 1;
        boolean enabled = true;
        boolean deleted = false;

        Todo todo = Todo.builder()
                .id(id)
                .title(title)
                .description(description)
                .completed(completed)
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .version(version)
                .enabled(enabled)
                .deleted(deleted)
                .build();

        assertThat(todo.getId()).isEqualTo(id);
        assertThat(todo.getTitle()).isEqualTo(title);
        assertThat(todo.getDescription()).isEqualTo(description);
        assertThat(todo.isCompleted()).isEqualTo(completed);
        assertThat(todo.getCreatedDate()).isEqualTo(createdDate);
        assertThat(todo.getLastModifiedDate()).isEqualTo(lastModifiedDate);
        assertThat(todo.getVersion()).isEqualTo(version);
        assertThat(todo.isEnabled()).isEqualTo(enabled);
        assertThat(todo.isDeleted()).isEqualTo(deleted);
    }

    @Test
    void testUpdateWith() {
        Todo originalTodo = Todo.builder()
                .id(UUID.randomUUID())
                .title("Original Title")
                .description("Original Description")
                .completed(false)
                .build();

        Todo updatedTodo = Todo.builder()
                .title("Updated Title")
                .description("Updated Description")
                .completed(true)
                .build();

        // Appelez la méthode updateWith pour mettre à jour l'objet originalTodo
        Todo resultTodo = originalTodo.updateWith(updatedTodo);

        // Vérifiez que les champs de resultTodo sont correctement mis à jour
        assertNotNull(resultTodo);
        assertEquals(originalTodo.getId(), resultTodo.getId());
        assertEquals(updatedTodo.getTitle(), resultTodo.getTitle());
        assertEquals(updatedTodo.getDescription(), resultTodo.getDescription());
        assertEquals(originalTodo.isCompleted(), resultTodo.isCompleted());
    }
}
