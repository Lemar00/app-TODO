package sn.ept.git.seminaire.cicd.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import java.time.Instant;
import static org.assertj.core.api.Assertions.assertThat;

class TagTest {


    @Test
    public void testDefaultConstructor() {
        Tag tag = new Tag();


        assertThat(tag).isNotNull();
        assertNull(tag.getId());
        assertNull(tag.getName());
        assertNull(tag.getDescription());
    }


    @Test
    void testFromId() {
        // Créer un UUID pour le test
        UUID id = UUID.randomUUID();

        // Appeler la méthode fromId pour créer une instance de Tag
        Tag tag = Tag.fromId(id);

        // Vérifier que l'objet Tag a été créé avec le bon ID
        assertNotNull(tag);
        assertEquals(id, tag.getId());
        assertNull(tag.getName());
        assertNull(tag.getDescription());
    }

    @Test
    public void testDataAnnotationGeneratesGetterSetterEqualsHashCodeToString() {
        // Créez deux objets Tag identiques
        UUID id = UUID.randomUUID();
        String name = "Test Name";
        String description = "Test Description";

        Tag tag1 = Tag.builder()
                .id(id)
                .name(name)
                .description(description)
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        Tag tag2 = Tag.builder()
                .id(id)
                .name(name)
                .description(description)
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        // Test equals et hashCode
        assertThat(tag1).isEqualTo(tag2);
        assertThat(tag1.hashCode()).isEqualTo(tag2.hashCode());

        // Test toString
        assertThat(tag1.toString()).isEqualTo(tag2.toString());

        // Test des getters et setters
        UUID newId = UUID.randomUUID();
        String newName = "New Name";
        String newDescription = "New Description";

        tag1.setId(newId);
        tag1.setName(newName);
        tag1.setDescription(newDescription);

        assertThat(tag1.getId()).isEqualTo(newId);
        assertThat(tag1.getName()).isEqualTo(newName);
        assertThat(tag1.getDescription()).isEqualTo(newDescription);
    }

    @Test
    public void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String name = "Test Name";
        String description = "Test Description";
        Instant createdDate = Instant.now();
        Instant lastModifiedDate = Instant.now();
        int version = 1;
        boolean enabled = true;
        boolean deleted = false;

        Tag tag = Tag.builder()
                .id(id)
                .name(name)
                .description(description)
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .version(version)
                .enabled(enabled)
                .deleted(deleted)
                .build();

        assertNotNull(tag);

        // Vérification des valeurs après initialisation
        assertThat(tag.getId()).isEqualTo(id);
        assertThat(tag.getName()).isEqualTo(name);
        assertThat(tag.getDescription()).isEqualTo(description);
        assertThat(tag.getCreatedDate()).isEqualTo(createdDate);
        assertThat(tag.getLastModifiedDate()).isEqualTo(lastModifiedDate);
        assertThat(tag.getVersion()).isEqualTo(version);
        assertThat(tag.isEnabled()).isEqualTo(enabled);
        assertThat(tag.isDeleted()).isEqualTo(deleted);
    }

    @Test
    public void testAllArgsConstructorWithNullValues() {
        Tag tag = Tag.builder()
                .id(null)
                .name(null)
                .description(null)
                .createdDate(null)
                .lastModifiedDate(null)
                .version(0)
                .enabled(false)
                .deleted(false)
                .build();


        assertNotNull(tag.toString());
        assertThat(tag).isNotNull();
        assertNull(tag.getId());
        assertNull(tag.getName());
        assertNull(tag.getDescription());
        assertNull(tag.getCreatedDate());
        assertNull(tag.getLastModifiedDate());
        assertEquals(0, tag.getVersion());
        assertFalse(tag.isEnabled());
        assertFalse(tag.isDeleted());
    }

    @Test
    public void testCollections() {
        Tag tag = new Tag();
        Set<Todo> todos = new HashSet<>();
        Todo todo1 = new Todo();
        Todo todo2 = new Todo();

        todos.add(todo1);
        tag.setTodos(todos);

        assertThat(tag.getTodos()).containsExactlyInAnyOrder(todo1);
        assertThat(tag.getTodos()).containsExactlyInAnyOrder(todo2);

        assertEquals(todos, tag.getTodos());
    }




}
