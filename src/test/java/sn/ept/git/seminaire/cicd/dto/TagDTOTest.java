package sn.ept.git.seminaire.cicd.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.UUID;

public class TagDTOTest {

    @Test
    void testGettersAndSetters() {
        TagDTO tag = new TagDTO();
        UUID id = UUID.randomUUID();
        tag.setId(id);
        tag.setName("TagName");

        assertEquals(id, tag.getId());
        assertEquals("TagName", tag.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        TagDTO tag1 = new TagDTO();
        UUID id = UUID.randomUUID();
        tag1.setId(id);
        tag1.setName("TagName");

        TagDTO tag2 = new TagDTO();
        tag2.setId(id);
        tag2.setName("TagName");

        assertEquals(tag1, tag2);
        assertEquals(tag1.hashCode(), tag2.hashCode());
    }


}
