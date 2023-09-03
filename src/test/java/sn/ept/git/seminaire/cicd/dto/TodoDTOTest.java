package sn.ept.git.seminaire.cicd.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TodoDTOTest {

    @Test
    void testGettersAndSetters() {
        TodoDTO todo = new TodoDTO();
        UUID id = UUID.randomUUID();
        todo.setId(id);
        todo.setTitle("Task Title");

        Set<TagDTO> tags = new HashSet<>();
        TagDTO tag1 = new TagDTO();
        tag1.setId(UUID.randomUUID());
        tag1.setName("Tag 1");
        tags.add(tag1);

        todo.setTags(tags);

        assertEquals(id, todo.getId());
        assertEquals("Task Title", todo.getTitle());
        assertEquals(tags, todo.getTags());
    }

    @Test
    void testEqualsAndHashCode() {
        TodoDTO todo1 = new TodoDTO();
        UUID id = UUID.randomUUID();
        todo1.setId(id);
        todo1.setTitle("Task Title");

        Set<TagDTO> tags1 = new HashSet<>();
        TagDTO tag1 = new TagDTO();
        tag1.setId(UUID.randomUUID());
        tag1.setName("Tag 1");
        tags1.add(tag1);

        todo1.setTags(tags1);

        TodoDTO todo2 = new TodoDTO();
        todo2.setId(id);
        todo2.setTitle("Task Title");

        Set<TagDTO> tags2 = new HashSet<>();
        TagDTO tag2 = new TagDTO();
        tag2.setId(UUID.randomUUID());
        tag2.setName("Tag 1");
        tags2.add(tag2);

        todo2.setTags(tags2);

        assertEquals(todo1, todo2);
        assertEquals(todo1.hashCode(), todo2.hashCode());
    }


}
