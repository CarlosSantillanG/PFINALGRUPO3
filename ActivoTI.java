abstract class ActivoTI implements Cloneable {
    private String nombre;
    private String descripcion;
    private int cantidadDisponible;

    private String numSerie;


    public ActivoTI(String nombre, String descripcion, int cantidadDisponible, String numSerie) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadDisponible = cantidadDisponible;
        this.numSerie = numSerie;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public abstract double calcularValor();

    public abstract void actualizarStock(int cantidad);

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nDescripción: " + descripcion + "\nNumero de Serie: "+ numSerie
                +"\nCantidad Disponible: " + cantidadDisponible;
    }
    // Método para clonar el activo de TI
    public ActivoTI clone() {
        try {
            return (ActivoTI) super.clone();
        } catch (CloneNotSupportedException e) {
            return null; // Manejo de excepción
        }
    }

}
