package sn.ept.git.seminaire.cicd.dto.base;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class BaseDTOTest {

    @Test
    void toStringShouldReturnNonEmptyString() {
        BaseDTO baseDTO = BaseDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        String toStringResult = baseDTO.toString();

        assertNotNull(toStringResult);
        assertFalse(toStringResult.isEmpty());
    }

    @Test
    void allArgsConstructorStaticNameShouldCreateInstance() {
        UUID id = UUID.randomUUID();
        Instant createdDate = Instant.now();
        Instant lastModifiedDate = Instant.now();
        int version = 1;
        boolean enabled = true;
        boolean deleted = false;

        BaseDTO baseDTO = BaseDTO.of(id, createdDate, lastModifiedDate, version, enabled, deleted);

        assertNotNull(baseDTO);
        assertEquals(id, baseDTO.getId());
        assertEquals(createdDate, baseDTO.getCreatedDate());
        assertEquals(lastModifiedDate, baseDTO.getLastModifiedDate());
        assertEquals(version, baseDTO.getVersion());
        assertEquals(enabled, baseDTO.isEnabled());
        assertEquals(deleted, baseDTO.isDeleted());
    }

    @Test
    void equalsAndHashCodeShouldWork() {
        BaseDTO baseDTO1 = BaseDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        BaseDTO baseDTO2 = BaseDTO.builder()
                .id(baseDTO1.getId())
                .createdDate(baseDTO1.getCreatedDate())
                .lastModifiedDate(baseDTO1.getLastModifiedDate())
                .version(baseDTO1.getVersion())
                .enabled(baseDTO1.isEnabled())
                .deleted(baseDTO1.isDeleted())
                .build();

        assertEquals(baseDTO1, baseDTO2);
        assertEquals(baseDTO1.hashCode(), baseDTO2.hashCode());
    }
}
