package sn.ept.git.seminaire.cicd.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sn.ept.git.seminaire.cicd.models.BaseEntity;


import java.util.*;
import java.util.stream.Collectors;


public class UtilsTest {

    private Set<BaseEntity> baseEntities;

    @BeforeEach
    void setUp() {
        // Créer un ensemble de BaseEntity simulées pour les tests
        baseEntities = new HashSet<>();
        baseEntities.add(createBaseEntity(UUID.randomUUID()));
        baseEntities.add(createBaseEntity(UUID.randomUUID()));
    }

    @Test
    void containsShouldReturnTrueIfIdExists() {
        UUID id = baseEntities.iterator().next().getId();
        assertTrue(Utils.contains(baseEntities, id));
    }

    @Test
    void containsShouldReturnFalseIfIdDoesNotExist() {
        UUID id = UUID.randomUUID();
        assertFalse(Utils.contains(baseEntities, id));
    }

    @Test
    void findAbsentIdsShouldReturnEmptySetIfAllIdsExist() {
        Set<UUID> ids = baseEntities.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());

        Set<UUID> absentIds = Utils.findAbsentIds(baseEntities, ids);

        assertTrue(absentIds.isEmpty());
    }

    @Test
    void findAbsentIdsShouldReturnMissingIds() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Set<UUID> ids = new HashSet<>(Arrays.asList(id1, id2));

        Set<UUID> absentIds = Utils.findAbsentIds(baseEntities, ids);

        assertTrue(absentIds.contains(id1));
        assertTrue(absentIds.contains(id2));
    }


    @Test
    public void createByteArrayShouldCreateByteArrayWithSpecifiedSizeAndData() {
        // Taille du tableau
        int size = 8;
        // Données binaires (par exemple, "01010101")
        String data = "01010101";
        // Appeler la méthode pour créer le tableau de bytes
        byte[] byteArray = TestUtil.createByteArray(size, data);

        // Vérifier que la taille du tableau correspond à la taille spécifiée
        assertEquals(size, byteArray.length);

        // Vérifier que les éléments du tableau correspondent aux données binaires
        for (int i = 0; i < size; i++) {
            assertEquals(Byte.parseByte(data, 2), byteArray[i]);
        }
    }


    @Test
    public void testFindAbsentIdsWithList() {
        // Créez une liste de BaseEntity
        List<BaseEntity> entityList = new ArrayList<>();
        BaseEntity entity1 = new BaseEntity();
        entity1.setId(UUID.randomUUID());
        BaseEntity entity2 = new BaseEntity();
        entity2.setId(UUID.randomUUID());
        entityList.add(entity1);

        // Créez un ensemble d'UUID simulé
        Set<UUID> ids = new HashSet<>();
        UUID id1 = entity1.getId();
        UUID id3 = UUID.randomUUID(); // Cet ID n'est pas présent dans la liste simulée
        ids.add(id1);
        ids.add(id3);

        // Appelez la méthode et vérifiez le résultat
        Set<UUID> absentIds = Utils.findAbsentIds(entityList, ids);
        assertEquals(1, absentIds.size()); // Un seul ID doit être absent
        assertEquals(id3, absentIds.iterator().next()); // L'ID absent doit être id3
    }

    // Utilitaire pour créer une BaseEntity simulée
    private BaseEntity createBaseEntity(UUID id) {
        BaseEntity entity = mock(BaseEntity.class);
        when(entity.getId()).thenReturn(id);
        return entity;
    }
}
