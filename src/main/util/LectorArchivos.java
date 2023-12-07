package main.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class LectorArchivos {
    private LectorArchivos() {

    }

    public static List<String> getArchivo(String nombreArchivo)  {
        URL archivoUrl = LectorArchivos.class.getResource("/"+ nombreArchivo);
        try {
            Path archivoPath = Paths.get(Objects.requireNonNull(archivoUrl).toURI());
            return Files.readAllLines(archivoPath);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("error en lectura" + e);
        }
    }
}
