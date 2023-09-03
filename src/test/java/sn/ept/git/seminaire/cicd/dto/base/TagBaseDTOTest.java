package sn.ept.git.seminaire.cicd.dto.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import java.time.Instant;


public class TagBaseDTOTest {

    @Test
    void allArgsConstructorShouldCreateInstanceWithAllParameters() {
        String name = "Tag Name";
        String description = "Tag Description";

        TagBaseDTO tagBaseDTO = new TagBaseDTO(name, description);

        assertNotNull(tagBaseDTO);
        assertEquals(name, tagBaseDTO.getName());
        assertEquals(description, tagBaseDTO.getDescription());
    }

    @Test
    void dataAnnotationShouldGenerateGetterAndSetterMethods() {
        TagBaseDTO tagBaseDTO = new TagBaseDTO();

        tagBaseDTO.setName("Tag Name");
        tagBaseDTO.setDescription("Tag Description");

        assertEquals("Tag Name", tagBaseDTO.getName());
        assertEquals("Tag Description", tagBaseDTO.getDescription());
    }

    @Test
    void testGetterAndSetterForName() {
        TagBaseDTO tagBaseDTO = new TagBaseDTO();
        tagBaseDTO.setName("Test Name");
        assertEquals("Test Name", tagBaseDTO.getName());
    }

    @Test
    void testGetterAndSetterForDescription() {
        TagBaseDTO tagBaseDTO = new TagBaseDTO();
        tagBaseDTO.setDescription("Test Description");
        assertEquals("Test Description", tagBaseDTO.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        TagBaseDTO tagBaseDTO1 = new TagBaseDTO();
        tagBaseDTO1.setName("Test Name");
        tagBaseDTO1.setDescription("Test Description");

        TagBaseDTO tagBaseDTO2 = new TagBaseDTO();
        tagBaseDTO2.setName("Test Name");
        tagBaseDTO2.setDescription("Test Description");

        assertTrue(tagBaseDTO1.equals(tagBaseDTO2));
        assertEquals(tagBaseDTO1.hashCode(), tagBaseDTO2.hashCode());
    }

    @Test
    void dataAnnotationShouldGenerateGetterSetterEqualsHashCodeToString() {
        TagBaseDTO tag1 = TagBaseDTO.builder()
                .id(UUID.randomUUID())
                .name("TagName")
                .description("TagDescription")
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        TagBaseDTO tag2 = TagBaseDTO.builder()
                .id(UUID.randomUUID())
                .name("TagName")
                .description("TagDescription")
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        // Test equals and hashCode
        assertEquals(tag1, tag2);
        assertEquals(tag1.hashCode(), tag2.hashCode());

        // Modify tag2 and re-test
        tag2.setName("DifferentName");
        assertNotEquals(tag1, tag2);
        assertNotEquals(tag1.hashCode(), tag2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentName() {
        TagBaseDTO tagBaseDTO1 = new TagBaseDTO();
        tagBaseDTO1.setName("Test Name");
        tagBaseDTO1.setDescription("Test Description");

        TagBaseDTO tagBaseDTO2 = new TagBaseDTO();
        tagBaseDTO2.setName("Different Name");
        tagBaseDTO2.setDescription("Test Description");

        assertNotEquals(tagBaseDTO1, tagBaseDTO2);
        assertNotEquals(tagBaseDTO1.hashCode(), tagBaseDTO2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentDescription() {
        TagBaseDTO tagBaseDTO1 = new TagBaseDTO();
        tagBaseDTO1.setName("Test Name");
        tagBaseDTO1.setDescription("Test Description");

        TagBaseDTO tagBaseDTO2 = new TagBaseDTO();
        tagBaseDTO2.setName("Test Name");
        tagBaseDTO2.setDescription("Different Description");

        assertNotEquals(tagBaseDTO1, tagBaseDTO2);
        assertNotEquals(tagBaseDTO1.hashCode(), tagBaseDTO2.hashCode());
    }

    @Test
    void testToString() {
        TagBaseDTO tagBaseDTO = new TagBaseDTO();
        tagBaseDTO.setName("Test Name");
        tagBaseDTO.setDescription("Test Description");

        String expected = "TagBaseDTO(name=Test Name, description=Test Description)";
        String actual = tagBaseDTO.toString();

        assertEquals(expected, actual);
    }

}
