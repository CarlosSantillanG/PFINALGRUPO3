public class ActivoTITangible extends ActivoTI {
    private String numeroSerie;
    private int cantidadDisponible;

    public ActivoTITangible(String nombre, String descripcion, int cantidadDisponible, String numeroSerie) {
        super(nombre, descripcion, cantidadDisponible, numeroSerie);
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    @Override
    public double calcularValor() {
        return 0; // No se manejan precios en activos fijos de TI
    }

    @Override
    public void actualizarStock(int cantidad) {
        cantidadDisponible += cantidad;
    }
}

