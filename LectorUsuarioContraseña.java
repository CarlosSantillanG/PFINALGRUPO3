import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

// Esta clase implementa el patrón Singleton
public class LectorUsuarioContraseña {
    private static LectorUsuarioContraseña instancia;

    private LectorUsuarioContraseña() {
        // Private constructor to prevent direct instantiation
    }

    public static LectorUsuarioContraseña getInstance() {
        if (instancia == null) {
            instancia = new LectorUsuarioContraseña();
        }
        return instancia;
    }

    public int leerArchivoUsuarioYContraseña(String filePath, HashMap<String, String> credenciales) {
        int resultado = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split each line into username and password
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    credenciales.put(username, password);
                } else {
                    System.out.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            resultado = -1;
            System.out.println(e.getMessage());
        }
        return resultado;
    }
}