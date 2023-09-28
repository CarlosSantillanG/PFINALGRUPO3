import java.util.HashMap;
import java.util.Scanner;

public class SistemaInventarioTI {
    private static HashMap<String, String> credenciales = new HashMap<>();
    private static Usuario usuarioActual = null;

    public static void main(String[] args) {
        // Las líneas siguientes se reemplazaron por el patrón Singleton:
//        credenciales.put("Admin", "123");
//        credenciales.put("AsistenteInventario", "Inventario321");

        String filePath = "src\\Usuarios.txt"; // Replace with the actual path to your file
        LectorUsuarioContraseña reader = LectorUsuarioContraseña.getInstance();
        int resultado = reader.leerArchivoUsuarioYContraseña(filePath, credenciales);
        if (resultado == -1) {
            System.out.println("No se pudo encontrar el archivo de Usuarios. No puede continuar");
            return;
        }

        InventarioTI inventarioTI = new InventarioTI();
        Scanner scanner = new Scanner(System.in);

        // Define un prototipo de activo de TI
        ActivoTI prototipo = new ActivoTITangible("Prototipo", "Descripción del Prototipo", 10, "12345");

        // Utiliza el prototipo para crear nuevos activos
        ActivoTI nuevoActivo = inventarioTI.crearNuevoActivo(prototipo, "Nuevo Activo", "Descripción del Nuevo Activo", 5);
        inventarioTI.registrarActivoTI(nuevoActivo);
        while (true) {
            if (usuarioActual == null) {
                System.out.println("BIENVENIDO");
                System.out.println("CLÍNICA INTERNACIONAL");
                System.out.println("***Sistema de Ingreso***");
                System.out.print("Nombre de Usuario: ");
                String nombreUsuario = scanner.nextLine();
                System.out.print("Contraseña: ");
                String contrasena = scanner.nextLine();

                if (autenticar(nombreUsuario, contrasena)) {
                    System.out.println("Inicio de sesión exitoso.");
                } else {
                    System.out.println("Credenciales inválidas. Intentalo nuevamente");
                    continue;
                }
            }
            while (true) {
                System.out.println("\nMenú:");
                System.out.println("1. Registrar Activo de TI");
                System.out.println("2. Buscar Activo de TI");
                System.out.println("3. Listar Activos de TI");
                System.out.println("4. Calcular Valor del Inventario de TI");
                System.out.println("5. Actualizar Inventario de TI");
                System.out.println("6. Alertas de Stock Bajo");
                System.out.println("7. Salir");
                System.out.print("Elija una opción: ");

                int opcion;
                try {
                    opcion = Integer.parseInt(scanner.nextLine()); // Intentamos leer una entrada numérica
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Por favor, ingresa un número.");
                    continue;
                }
                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del Activo de TI: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Descripción: ");
                        String descripcion = scanner.nextLine();
                        System.out.print("Número de Serie: ");
                        String numeroSerie = scanner.nextLine();
                        int cantidad;
                        try {
                            System.out.print("Cantidad Inicial: ");
                            cantidad = Integer.parseInt(scanner.nextLine()); // Intentamos convertir la entrada a un entero

                        } catch (NumberFormatException e) {
                            System.out.println("Error: Ingresaste un formato de número no válido para la cantidad inicial.");
                            break;
                        }
                            ActivoTI activoTI = new ActivoTITangible(nombre, descripcion, cantidad, numeroSerie);
                            inventarioTI.registrarActivoTI(activoTI);
                            break;
                    case 2:
                        System.out.print("Ingrese el nombre del Activo de TI a buscar: ");
                        nombre = scanner.nextLine();
                        ActivoTI activoEncontrado = inventarioTI.buscarActivoTI(nombre);
                        if (activoEncontrado != null) {
                            System.out.println(activoEncontrado);
                        } else {
                            System.out.println("Activo de TI no encontrado.");
                        }
                        break;
                    case 3:
                        System.out.println("Inventario de TI:");
                        for (ActivoTI a : inventarioTI.listarActivosTI()) {
                            System.out.println(a);
                            System.out.println("---------------");
                        }
                        break;
                    case 4:
                        double valorInventario = inventarioTI.calcularValorInventario();
                        System.out.println("Valor Total del Inventario de TI: $" + valorInventario);
                        break;
                    case 5:
                        System.out.print("Ingrese el nombre del Activo de TI a actualizar: ");
                        nombre = scanner.nextLine();
                        try {
                            System.out.print("Cantidad a agregar (negativa para restar): ");
                            cantidad = Integer.parseInt(scanner.nextLine()); // Intentamos convertir la entrada a un entero
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Ingresaste un formato de número no válido para la cantidad.");
                            break; // Salimos del caso 5 si ocurre una excepción
                        }
                        inventarioTI.actualizarInventario(nombre, cantidad);
                        break;

                    case 6:
                        int umbral;
                        try {
                            System.out.print("Ingrese el umbral para las alertas de stock bajo: ");
                            umbral = Integer.parseInt(scanner.nextLine()); // Intentamos convertir la entrada a un entero
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Ingresaste un formato de número no válido para el umbral.");
                            break; // Salimos del caso 6 si ocurre una excepción
                        }
                        inventarioTI.alertasStockBajo(umbral);
                        break;
                    case 7:
                        scanner.close();
                        System.out.println("¡Gracias por usar el sistema de inventario de TI!");
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");

                }
            }
        }
    }

    public static boolean autenticar(String nombreUsuario, String contrasena) {
        String contrasenaGuardada = credenciales.get(nombreUsuario);
        if (contrasenaGuardada != null && contrasenaGuardada.equals(contrasena)) {
            usuarioActual = new Usuario(nombreUsuario, contrasena);
            return true;
        }
        return false;
    }
}