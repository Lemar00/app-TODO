package sn.ept.git.seminaire.cicd.demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MyFileReaderTest {

    private MyFileReader fileReader;

    @BeforeEach
    void setUp() {
        fileReader = new MyFileReader();
    }

    @Test
    void testReadFile() throws IOException {
        // Créer un fichier de test avec du contenu
        String filePath = "test.txt";
        List<String> expectedLines = Arrays.asList("Ligne 1", "Ligne 2", "Ligne 3");
        Files.write(Paths.get(filePath), expectedLines);

        // Appeler la méthode read pour lire le fichier
        List<String> actualLines = fileReader.read(filePath);

        // Assurer que les lignes lues correspondent aux lignes attendues
        assertEquals(expectedLines, actualLines);

        // Supprimer le fichier de test après le test
        Files.delete(Paths.get(filePath));
    }

    @Test
    void testReadNonExistentFile() {
        // Essayer de lire un fichier qui n'existe pas
        String filePath = "non_existent_file.txt";

        // Utiliser une assertion pour vérifier qu'une IOException est levée
        assertThrows(IOException.class, () -> fileReader.read(filePath));
    }
}
