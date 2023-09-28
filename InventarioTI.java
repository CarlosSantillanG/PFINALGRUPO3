import java.util.ArrayList;
import java.util.List;

public class InventarioTI {
    private List<ActivoTI> activosTI = new ArrayList<>();

    public void registrarActivoTI(ActivoTI activoTI) {
        activosTI.add(activoTI);
        System.out.println("Activo de TI registrado exitosamente.");
    }

    public ActivoTI buscarActivoTI(String nombre) {
        for (ActivoTI activoTI : activosTI) {
            if (activoTI.getNombre().equalsIgnoreCase(nombre)) {
                return activoTI;
            }
        }
        return null;
    }

    public List<ActivoTI> listarActivosTI() {
        return activosTI;
    }

    public double calcularValorInventario() {
        double valorTotal = 0.0;
        for (ActivoTI activoTI : activosTI) {
            valorTotal += activoTI.calcularValor();
        }
        return valorTotal;
    }

    public void actualizarInventario(String nombreActivo, int cantidad) {
        ActivoTI activo = buscarActivoTI(nombreActivo);
        if (activo != null) {
            int cantidadActual = activo.getCantidadDisponible();

            if (cantidad >= 0) {
                // Agregar la cantidad ingresada
                activo.setCantidadDisponible(cantidadActual + cantidad);
                System.out.println("Cantidad actualizada exitosamente.");
            } else {
                // Restar la cantidad (asegurándose de que no sea mayor que la cantidad actual)
                if (cantidadActual >= Math.abs(cantidad)) {
                    activo.setCantidadDisponible(cantidadActual + cantidad);
                    System.out.println("Cantidad restada exitosamente.");
                } else {
                    System.out.println("La cantidad a restar es mayor que la cantidad disponible.");
                }
            }
        } else {
            System.out.println("Activo de TI no encontrado.");
        }
    }
    public void alertasStockBajo(int umbral) {
        System.out.println("Alertas de Stock Bajo:");
        for (ActivoTI activoTI : activosTI) {
            if (activoTI.getCantidadDisponible() < umbral) {
                System.out.println("Activo de TI: " + activoTI.getNombre() + ", Cantidad Disponible: " + activoTI.getCantidadDisponible());
            }
        }
    }
    public ActivoTI crearNuevoActivo(ActivoTI prototipo, String nombre, String descripcion, int cantidad) {
        ActivoTI nuevoActivo = prototipo.clone();
        nuevoActivo.setNombre(nombre);
        nuevoActivo.setDescripcion(descripcion);
        nuevoActivo.setCantidadDisponible(cantidad);
        return nuevoActivo;
    }
}
